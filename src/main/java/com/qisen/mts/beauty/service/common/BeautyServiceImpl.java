package com.qisen.mts.beauty.service.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.dao.rule.RuleEmpAchiDao;
import com.qisen.mts.beauty.model.SessionUser;
import com.qisen.mts.beauty.model.entity.rule.RuleEmpAchi;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.dao.base.CompanyDao;
import com.qisen.mts.common.dao.base.EmpDao;
import com.qisen.mts.common.dao.base.EmpGradeDao;
import com.qisen.mts.common.dao.base.ItemDao;
import com.qisen.mts.common.dao.base.ItemPackDao;
import com.qisen.mts.common.dao.base.ItemPackDetailDao;
import com.qisen.mts.common.dao.base.ItemPriceDao;
import com.qisen.mts.common.dao.base.RuleItemCardDiscDao;
import com.qisen.mts.common.dao.base.RulePresentCardTypeDao;
import com.qisen.mts.common.dao.base.TypeDao;
import com.qisen.mts.common.dao.sys.AccountDao;
import com.qisen.mts.common.dao.sys.DepartmentDao;
import com.qisen.mts.common.dao.sys.EnterpriseDao;
import com.qisen.mts.common.dao.sys.GtypeDao;
import com.qisen.mts.common.dao.sys.ShopDao;
import com.qisen.mts.common.dao.sys.ShopSettingDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.entity.BaseEntity;
import com.qisen.mts.common.model.entity.base.Company;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.entity.base.EmpGrade;
import com.qisen.mts.common.model.entity.base.Item;
import com.qisen.mts.common.model.entity.base.ItemPack;
import com.qisen.mts.common.model.entity.base.ItemPackDetail;
import com.qisen.mts.common.model.entity.base.ItemPrice;
import com.qisen.mts.common.model.entity.base.RuleItemCardDisc;
import com.qisen.mts.common.model.entity.base.RulePresentCardType;
import com.qisen.mts.common.model.entity.base.Type;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.common.model.entity.sys.Department;
import com.qisen.mts.common.model.entity.sys.Enterprise;
import com.qisen.mts.common.model.entity.sys.Gtype;
import com.qisen.mts.common.model.entity.sys.Shop;
import com.qisen.mts.common.model.entity.sys.ShopSetting;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.util.CollectionUtil;
import com.qisen.mts.gateway.ExceptionWithCode;

import net.rubyeye.xmemcached.MemcachedClient;

@Service
public class BeautyServiceImpl implements BeautyService {

	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private GtypeDao gtypeDao;
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private EmpGradeDao empGradeDao;
	@Autowired
	private EmpDao empDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ItemPackDao itemPackDao;
	@Autowired
	private ItemPackDetailDao itemPackDetailDao;
	@Autowired
	private ItemPriceDao itemPriceDao;
	@Autowired
	private RuleItemCardDiscDao itemCardDiscDao;
	@Autowired
	private RulePresentCardTypeDao presentCardTypeDao;
	@Autowired
	private RuleEmpAchiDao empAchiDao;
	@Autowired
	private ShopSettingDao shopSettingDao;

	@Override
	public CommObjResponse<JSONObject> login(BeautyRequest<Account> req) throws Exception {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		Account account = accountDao.find4Login(req.getBody().getAccount());
		if (account == null)
			throw new ExceptionWithCode(MsgCode.ACCOUNT_NOT_EXIST);
		if (!account.getPwd().equals(req.getBody().getPwd().trim()))
			throw new ExceptionWithCode(MsgCode.ACCOUNT_PWD_NOT_MATCHED);
		if (!account.getStatus().equals(1))
			throw new ExceptionWithCode(MsgCode.ACCOUNT_FORBIDDEN);

		JSONObject authScope = account.getAuthScope();
		if (authScope == null)
			authScope = new JSONObject();
		authScope.put(req.getScope(), System.currentTimeMillis());
		account.setAuthScope(authScope);

		String token = account.getAuthToken();
		String oldToken = null;
		if (StringUtils.isBlank(token) || authScope.keySet().size() == 1) {
			oldToken = token;
			token = UUID.randomUUID().toString();
			account.setAuthToken(token);
		}

		Integer count = accountDao.update4Login(account.getId(), token, authScope.toJSONString());
		if (count == null || !count.equals(1))
			throw new ExceptionWithCode(MsgCode.ACCOUNT_UPDATE_TOKEN_ERROR);

		SessionUser sessionUser = this.buildSessionUser(account);
		JSONObject sessionUserJ = sessionUser.toJSON();
		sessionUserJ.remove("tbuser");
		sessionUserJ.remove("metadata");
		resp.setBody(sessionUserJ);
		
		String sessionUserKey = ConfigConsts.SESSION_USER + token;
		String userInfoJstr = sessionUser.toString();
		if (!memcachedClient.add(sessionUserKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, userInfoJstr)) {
			memcachedClient.replace(sessionUserKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, userInfoJstr);
		}
		if (StringUtils.isNoneBlank(oldToken))
			memcachedClient.delete(ConfigConsts.SESSION_USER + oldToken);

		return resp;
	}

	@Override
	public BaseResponse logout(BeautyRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Account account = accountDao.findByToken(req.getToken());
		String sessionUserKey = ConfigConsts.SESSION_USER + req.getToken();
		Boolean result = false;
		if (account != null) {
			JSONObject authScope = account.getAuthScope();
			if (authScope.containsKey(req.getScope()))
				authScope.remove(req.getScope());
			// 判断是否有其它scope登录
			if (authScope.isEmpty()) { // 没有则清除登录状态
				Integer count = accountDao.update4Login(account.getId(), null, null);
				if (count != null && count.equals(1))
					result = memcachedClient.delete(sessionUserKey);
			} else { // 有则更新scope
				SessionUser sessionUser = JSON.parseObject(account.toString(), SessionUser.class);
				sessionUser.setAuthScope(authScope);
				Integer count = accountDao.update4Login(account.getId(), req.getToken(), authScope.toJSONString());
				if (count != null && count.equals(1))
					result = memcachedClient.replace(sessionUserKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, sessionUser.toString());
			}
		} else
			result = memcachedClient.delete(sessionUserKey);
		if (!result)
			throw new ExceptionWithCode(MsgCode.ACCOUNT_UPDATE_TOKEN_ERROR);
		return resp;
	}

	@Override
	public SessionUser buildSessionUser(BaseEntity entity) throws Exception {
		SessionUser sessionUser = JSON.toJavaObject(entity.toJSON(), SessionUser.class);
		Integer eid = sessionUser.getEid();
		Integer sid = sessionUser.getSid();

		Enterprise enterprise = enterpriseDao.find(eid);
		sessionUser.setMeta("tbuser", enterprise.getTbuser());

		JSONObject paramBody = new JSONObject();
		// 员工业绩提成规则
		paramBody.put("type", null);
		paramBody.put("metadata", 1);
		paramBody.put("status", new Integer[] { 1 });
		List<RuleEmpAchi> empAchiRules = empAchiDao.list(eid, sid, paramBody);
		sessionUser.setMeta("empAchiRules", empAchiRules);

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
		basicTypes.put("dailyTypes", dailyTypes);
		basicTypes.put("payTypes", payTypes);
		sessionUser.setMeta("basicTypes", basicTypes);

		// 员工级别类型
		paramBody.clear();
		paramBody.put("gtype", null);
		paramBody.put("status", 1);
		List<EmpGrade> empGrades = empGradeDao.list(eid, null, paramBody);
		sessionUser.setMeta("empGrades", empGrades);
		
		// 参数配置
		paramBody.clear();
		List<ShopSetting> configs = shopSettingDao.list(eid, sid, paramBody);
		sessionUser.setMeta("configs", configs);

		return sessionUser;
	}

	@Override
	public CommObjResponse<JSONObject> metadata(BeautyRequest<JSONObject> req) throws Exception {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject respBody = new JSONObject();
		JSONObject enterpriseMeta = new JSONObject();
		JSONObject shopMeta = new JSONObject();

		JSONObject body = req.getBody();
		String types = body.getString("types");
		if (StringUtils.isNotBlank(types)) {
			Integer eid = req.getEid();
			Integer sid = req.getSid();

			// shopMeta
			if (types.contains("shop")) {
				JSONObject paramBody = new JSONObject();

				// 项目、商品定价
				List<ItemPrice> servicesPrice = new ArrayList<>();
				List<ItemPrice> productsPrice = new ArrayList<>();
				List<ItemPrice> itemPrices = itemPriceDao.list(sid, 0);
				for (ItemPrice itemPrice : itemPrices) {
					switch (itemPrice.getType()) {
					case 1: // 项目
						servicesPrice.add(itemPrice);
						break;
					case 2: // 商品
						productsPrice.add(itemPrice);
						break;
					}
				}
				shopMeta.put("servicesPrice", servicesPrice);
				shopMeta.put("productsPrice", productsPrice);

				// 卡级折扣会员价
				List<RuleItemCardDisc> cardDiscRules = itemCardDiscDao.list(eid, sid, null);
				shopMeta.put("cardDiscRules", cardDiscRules);

				// 充值赠送规则
				List<RulePresentCardType> presentRules = presentCardTypeDao.list(eid, sid, null);
				shopMeta.put("presentRules", presentRules);

				// 员工
				List<Emp> employees = empDao.list4MetaData(sid);
				shopMeta.put("employees", employees);

				// 业绩、提成规则
				paramBody.clear();
				paramBody.put("type", null);
				paramBody.put("status", new Integer[] { 1 });
				paramBody.put("metadata", 1);
				List<RuleEmpAchi> empAchis = empAchiDao.list(eid, sid, paramBody);
				shopMeta.put("empAchiRules", empAchis);

				// 自定义配置
				paramBody.clear();
				List<ShopSetting> settings = shopSettingDao.list(eid, sid, paramBody);
				shopMeta.put("configs", settings);

				respBody.put("shopMeta", shopMeta);
			}

			// enterpriseMeta
			if (types.contains("enterprise")) {
				// self
				Enterprise enterprise = enterpriseDao.find(eid);
				enterpriseMeta.put("enterpriseInfo", enterprise);

				// 门店
				List<Shop> stores = shopDao.list4MetaData(eid);
				enterpriseMeta.put("stores", stores);

				// 企业部门
				List<Department> departments = departmentDao.list(eid);
				enterpriseMeta.put("departments", departments);

				// 企业级别类型
				List<Gtype> gtypes = gtypeDao.list(eid);
				enterpriseMeta.put("gtypes", gtypes);

				JSONObject paramBody = new JSONObject();
				
				// 产品公司
				List<Company> companys = companyDao.list(eid,null,paramBody);
				enterpriseMeta.put("companys", companys);
				
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
				basicTypes.put("dailyTypes", dailyTypes);
				basicTypes.put("payTypes", payTypes);
				enterpriseMeta.put("basicTypes", basicTypes);

				// 员工级别类型
				paramBody.clear();
				paramBody.put("gtype", null);
				paramBody.put("status", 1);
				List<EmpGrade> empGrades = empGradeDao.list(eid, null, paramBody);
				enterpriseMeta.put("empGrades", empGrades);

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
				enterpriseMeta.put("services", services);
				enterpriseMeta.put("products", products);
				enterpriseMeta.put("cardTypes", cardTypes);

				// 项目套餐
				paramBody.clear();
				paramBody.put("keyword", null);
				paramBody.put("status", new Integer[] { 1, 2 });
				List<ItemPack> itemPackList = itemPackDao.list(eid, null, paramBody, null, null);
				JSONArray packIds = CollectionUtil.toJSONArray(itemPackList, false, "id");
				List<ItemPackDetail> itemPackDetailList = itemPackDetailDao.findByPackIds(eid, packIds);
				List<JSONObject> itemPacks = new ArrayList<>();
				for (ItemPack itemPack : itemPackList) {
					JSONObject itemPackJ = itemPack.toJSON();
					List<ItemPackDetail> details = new ArrayList<>();
					for (ItemPackDetail itemPackDetail : itemPackDetailList) {
						if (itemPack.getId().equals(itemPackDetail.getPackId()))
							details.add(itemPackDetail);
					}
					itemPackJ.put("details", details);
					itemPacks.add(itemPackJ);
				}
				enterpriseMeta.put("itemPacks", itemPacks);

				// 卡级折扣会员价
				List<RuleItemCardDisc> cardDiscRules = itemCardDiscDao.list(eid, null, null);
				enterpriseMeta.put("cardDiscRules", cardDiscRules);

				// 充值赠送规则
				List<RulePresentCardType> presentRules = presentCardTypeDao.list(eid, null, null);
				enterpriseMeta.put("presentRules", presentRules);

				// 角色
				// enterpriseMeta.put("role", JSON.parseArray("[{\"id\": 1, \"name\": \"老板\"},{\"id\": 2, \"name\": \"管理员\"},{\"id\": 3, \"name\": \"操作员\"}]"));

				// 自定义配置
				paramBody.clear();
				List<ShopSetting> settings = shopSettingDao.list(eid, null, paramBody);
				enterpriseMeta.put("configs", settings);

				respBody.put("enterpriseMeta", enterpriseMeta);
			}
			resp.setBody(respBody);
			
			// SessionUser
			SessionUser sessionUser = req.getSessionUser();
			sessionUser.setSid(sid);
			sessionUser = this.buildSessionUser(sessionUser);
			String sessionUserKey = ConfigConsts.SESSION_USER + req.getToken();
			String userInfoJstr = sessionUser.toString();
			if (!memcachedClient.add(sessionUserKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, userInfoJstr)) {
				memcachedClient.replace(sessionUserKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, userInfoJstr);
			}
		} else
			resp.setResult(ResultCode.INVALID_PARAMETERS);
		return resp;
	}

}
