package com.qisen.mts.myk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.EmpDao;
import com.qisen.mts.common.dao.base.EmpGradeDao;
import com.qisen.mts.common.dao.base.ItemDao;
import com.qisen.mts.common.dao.base.TypeDao;
import com.qisen.mts.common.dao.mem.MemberDao;
import com.qisen.mts.common.dao.sys.EnterpriseDao;
import com.qisen.mts.common.dao.sys.ShopDao;
import com.qisen.mts.common.dao.sys.ShopSettingDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.entity.BaseEntity;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.entity.base.EmpGrade;
import com.qisen.mts.common.model.entity.base.Item;
import com.qisen.mts.common.model.entity.base.Type;
import com.qisen.mts.common.model.entity.mem.Member;
import com.qisen.mts.common.model.entity.sys.Enterprise;
import com.qisen.mts.common.model.entity.sys.Shop;
import com.qisen.mts.common.model.entity.sys.ShopSetting;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.gateway.ExceptionWithCode;
import com.qisen.mts.myk.model.SessionMember;
import com.qisen.mts.myk.model.request.MykRequest;

import net.rubyeye.xmemcached.MemcachedClient;

@Service
public class MykServiceImpl implements MykService {

	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private EmpGradeDao empGradeDao;
	@Autowired
	private EmpDao empDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ShopSettingDao shopSettingDao;
	
	@Override
	public CommObjResponse<SessionMember> login(MykRequest<JSONObject> req) throws Exception {
		CommObjResponse<SessionMember> resp = new CommObjResponse<>();
		JSONObject body = req.getBody();
		String mobile = body.getString("mobile");
		String loginPwd = body.getString("pwd").trim();
		List<Member> members = memberDao.find4Login(mobile,loginPwd);
		Member member = null;
		if(members.size() == 0){
			throw new ExceptionWithCode(MsgCode.ACCOUNT_PWD_NOT_MATCHED);
		}else{
			 member = members.get(0);
		}
		if (!member.getStatus().equals(1)){
			throw new ExceptionWithCode(MsgCode.ACCOUNT_FORBIDDEN);
		}
		String token = member.getAuthToken();
		String oldToken = null;
		if (StringUtils.isBlank(token)) {
			oldToken = token;
			token = UUID.randomUUID().toString();
			member.setAuthToken(token);
		}
		
		Integer count = memberDao.update4Login(member.getId(), token);
		if (count == null || !count.equals(1))
			throw new ExceptionWithCode(MsgCode.ACCOUNT_UPDATE_TOKEN_ERROR);
		
		SessionMember sessionMember = member.toJSON().toJavaObject(SessionMember.class);
		resp.setBody(sessionMember);
		
		sessionMember = buildSessionMember(sessionMember);
		String sessionMemberKey = ConfigConsts.SESSION_MEMBER + token;
		String memberInfoJstr = sessionMember.toString();
		if (!memcachedClient.add(sessionMemberKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, memberInfoJstr)) {
			memcachedClient.replace(sessionMemberKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, memberInfoJstr);
		}
		if (StringUtils.isNoneBlank(oldToken))
			memcachedClient.delete(ConfigConsts.SESSION_MEMBER + oldToken);
		
		return resp;
	}

	@Override
	public CommObjResponse<JSONObject> metadata(MykRequest<JSONObject> req) throws Exception {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		
		// SessionMember
		SessionMember sessionMember = req.getSessionMember();
		sessionMember.setSid(req.getSid());
		sessionMember = this.buildSessionMember(sessionMember);
		resp.setBody(sessionMember.getMeta());
		String sessionMemberKey = ConfigConsts.SESSION_MEMBER + req.getToken();
		String memberInfoJstr = sessionMember.toString();
		if (!memcachedClient.add(sessionMemberKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, memberInfoJstr)) {
			memcachedClient.replace(sessionMemberKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, memberInfoJstr);
		}
		return resp;
	}
	
	@Override
	public SessionMember buildSessionMember(BaseEntity entity) throws Exception {
		SessionMember sessionMember = JSON.toJavaObject(entity.toJSON(), SessionMember.class);
		Integer eid = sessionMember.getEid();
		Integer sid = sessionMember.getSid();
		
		// shopMeta
		JSONObject paramBody = new JSONObject();
		// 员工
		List<Emp> employees = empDao.list4MetaData(sid);
		sessionMember.setMeta("employees", employees);

		// enterpriseMeta
		// self
		Enterprise enterprise = enterpriseDao.find(eid);
		sessionMember.setMeta("enterpriseInfo", enterprise);
		sessionMember.setMeta("tbuser", enterprise.getTbuser());
		
		// 门店
		List<Shop> stores = shopDao.list4MetaData(eid);
		JSONArray storesArr = new JSONArray();
		for (Shop shop : stores) {
			JSONObject storeJ = shop.toJSON();
			List<ShopSetting> settings = shopSettingDao.list(eid, shop.getId(), paramBody);
			storeJ.put("configs", settings);
			storesArr.add(storeJ);
		}
		sessionMember.setMeta("stores", storesArr);

		// 基础分类
		JSONObject basicTypes = new JSONObject();
		paramBody.clear();
		paramBody.put("type", null);
		paramBody.put("status", 1);
		List<Type> typeList = typeDao.list(eid, null, paramBody);
		List<Type> serviceTypes = new ArrayList<>();
		List<Type> productTypes = new ArrayList<>();
		List<Type> memberTypes = new ArrayList<>();
		List<Type> dailyTypes = new ArrayList<>();
		List<Type> payTypes = new ArrayList<>();
		for (Type type : typeList) {
			switch (type.getType()) {
			case 1: // 服务项目
				serviceTypes.add(type);
				break;
			case 2: // 商品
				productTypes.add(type);
				break;
			case 3: // 会员
				if (type.getSid() == null || type.getSid().equals(0) || type.getSid().equals(sid))
					memberTypes.add(type);
				break;
			case 4: // 日常支出
				if (type.getSid() == null || type.getSid().equals(0) || type.getSid().equals(sid))
					dailyTypes.add(type);
				break;
			case 5: // 日常收入
				if (type.getSid() == null || type.getSid().equals(0) || type.getSid().equals(sid))
					dailyTypes.add(type);
				break;
			case 6: // 自定义付款方式 现金类
				if (type.getSid() == null || type.getSid().equals(0) || type.getSid().equals(sid))
					payTypes.add(type);
				break;
			case 7: // 自定义付款方式 非现类
				if (type.getSid() == null || type.getSid().equals(0) || type.getSid().equals(sid))
					payTypes.add(type);
				break;
			}
		}
		basicTypes.put("serviceTypes", serviceTypes);
		basicTypes.put("productTypes", productTypes);
		basicTypes.put("memberTypes", memberTypes);
		sessionMember.setMeta("basicTypes", basicTypes);

		// 员工级别类型
		paramBody.clear();
		paramBody.put("gtype", null);
		paramBody.put("status", 1);
		List<EmpGrade> empGrades = empGradeDao.list(eid, null, paramBody);
		sessionMember.setMeta("empGrades", empGrades);

		// 项目、商品、会员卡
		paramBody.clear();
		paramBody.put("keyword", null);
		paramBody.put("typeNo", null);
		paramBody.put("type", null);
		paramBody.put("status", new Integer[] { 1, 2 });
		List<Item> itemList = itemDao.list(eid, null, paramBody, null, null);
		List<Item> services = new ArrayList<>();
		List<Item> products = new ArrayList<>();
		List<Item> cardTypes = new ArrayList<>();
		for (Item item : itemList) {
			switch (item.getType()) {
			case 1: // 项目
				services.add(item);
				break;
			case 2: // 商品
				products.add(item);
				break;
			case 3: // 会员卡
				cardTypes.add(item);
				break;
			}
		}
		sessionMember.setMeta("services", services);
		sessionMember.setMeta("cardTypes", cardTypes);

		// 自定义配置
		paramBody.clear();
		List<ShopSetting> settings = shopSettingDao.list(eid, null, paramBody);
		sessionMember.setMeta("configs", settings);
		
		return sessionMember;
	}
}
