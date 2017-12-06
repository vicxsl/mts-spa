package com.qisen.mts.common.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.ItemPriceDao;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.base.ItemPrice;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;

@Service
public class ItemPriceServiceImpl implements ItemPriceService {
	@Autowired
	private ItemPriceDao itemPriceDao;

	@Override
	public BaseResponse save(BaseRequest<ItemPrice> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		ItemPrice itemPrice = req.getBody();
		itemPrice.setSid(req.getSid());

		Integer result = null;
		if (itemPrice.getId().equals(0)) {
			Integer count = itemPriceDao.check(itemPrice.getSid(), itemPrice.getType(), itemPrice.getItemNo());
			if (count.equals(0))
				result = itemPriceDao.create(itemPrice);
			else
				result = itemPriceDao.update(itemPrice);
		} else {
			result = itemPriceDao.update(itemPrice);
		}

		if (result != null && result > 0)
			resp.notify4Metadata(null, req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public BaseResponse delete(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Integer result = itemPriceDao.delete(req.getSid(), req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(null, req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

}
