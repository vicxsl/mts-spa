package com.qisen.mts.common.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.sys.AccountDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

import net.rubyeye.xmemcached.MemcachedClient;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private MemcachedClient memcachedClient;
	
	@Override
	public BaseResponse save(BaseRequest<Account> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Account account = req.getBody();
		account.setEid(req.getEid());
		account.setSid(req.getSid());
		Integer result = 1;
		if (account.getId() != null && account.getId() > 0) { // update
			int count = accountDao.check(account.getAccount(), account.getId());
			if (count == 0) {
				accountDao.update(account);
				Account acc = accountDao.findAccount(req.getEid(),account.getId());
				memcachedClient.delete(ConfigConsts.SESSION_USER + acc.getAuthToken());
				accountDao.update4Login(acc.getId(), null, null);
				resp.notify4Metadata(req.getEid(), req.getSid());
			}
		} else { // create
			// default rights
			// check no
			int count = accountDao.check(account.getAccount(), 0);
			if (count == 0)
				result = accountDao.create(account);
			else
				resp.setCode(MsgCode.ACCOUNT_EXIST);
		}
		if (null == result || result == 0)
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public BaseResponse status(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		accountDao.status(req.getEid(), req.getBody());
		JSONArray ids = req.getBody().getJSONArray("ids");
		for(int i = 0;i < ids.size();i++){
			Account acc = accountDao.findAccount(req.getEid(),ids.getInteger(i));
			memcachedClient.delete(ConfigConsts.SESSION_USER + acc.getAuthToken());
			accountDao.update4Login(acc.getId(), null, null);
		}
//			accountDao.update4Login(null, null, scopeJson.toJSONString());
		resp.notify4Metadata(req.getEid(), req.getSid());
		return resp;
	}

	@Override
	public PageResponse<List<Account>> list(PageRequest<JSONObject> req) {
		int count = accountDao.count(req);
		PageResponse<List<Account>> resp = new PageResponse<>(req.getPageNum(), req.getPageSize(), count);
		if (count > 0) {
			List<Account> accounts  = accountDao.list(req);
			resp.setBody(accounts);
		}
		return resp;
	}

	@Override
	public BaseResponse rights(BaseRequest<Account> req) throws Exception  {
		BaseResponse resp = new BaseResponse();
		Account account = req.getBody();
		if(account.getRights() !=null &&account.getRights().toString().equals("{}")){
			account.setRights(null);
		}
		Integer count =accountDao.rights(req.getEid(), account);
		if (count != null && count > 0){
			Account acc = accountDao.findAccount(req.getEid(),account.getId());
			memcachedClient.delete(ConfigConsts.SESSION_USER + acc.getAuthToken());
			accountDao.update4Login(acc.getId(), null, null);
			resp.notify4Metadata(req.getEid(), req.getSid());
		}else{
			resp.setCode(MsgCode.COMMON_UPDATE_FAILED);
		}
		return resp;
	}
}
