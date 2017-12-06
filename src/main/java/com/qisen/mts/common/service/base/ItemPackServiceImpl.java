package com.qisen.mts.common.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.ItemPackDao;
import com.qisen.mts.common.dao.base.ItemPackDetailDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.base.ItemPack;
import com.qisen.mts.common.model.entity.base.ItemPackDetail;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Service
public class ItemPackServiceImpl implements ItemPackService {
	@Autowired
	private ItemPackDao itemPackDao;

	@Autowired
	private ItemPackDetailDao itemPackDetailDao;

	@Override
	public PageResponse<List<JSONObject>> list(PageRequest<JSONObject> req) {
		PageResponse<List<JSONObject>> response = new PageResponse<List<JSONObject>>();

		Integer count = this.itemPackDao.count(req);
		List<JSONObject> itemPackList = new ArrayList<>();
		List<ItemPackDetail> detailList = null;
		if (count != null && count.intValue() > 0) {
			List<ItemPack> baseItemPackList = itemPackDao.list(req.getEid(), req.getSid(), req.getBody(),
					req.getStartIndex(), req.getEndIndex());
			detailList = itemPackDetailDao.list(req);
			for (ItemPack itemPack : baseItemPackList) {
				List<ItemPackDetail> packdetails = new ArrayList<ItemPackDetail>();
				for (ItemPackDetail itemPackDetail : detailList) {
					if (itemPackDetail.getPackId().equals(itemPack.getId())) {
						packdetails.add(itemPackDetail);
					}
				}
				JSONObject itemPackJ = itemPack.toJSON();
				itemPackJ.put("detailList", packdetails);
				itemPackList.add(itemPackJ);
			}
			response.setPageNum(req.getPageNum());
			response.setPageSize(req.getPageSize());
			response.setCount(count);
		}
		response.setBody(itemPackList);
		return response;
	}

	@Override
	public BaseResponse edit(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();

		Integer id = req.getBody().getInteger("id");
		Integer result = null;
		int rcount=0;
		if (id == null || id.equals(0)) {// 新增
			rcount=this.itemPackDao.check(req.getEid(), req.getBody().getString("no"));
			if(rcount==0){
				result = itemPackDao.create(req);
			}else{
				resp.setCode(MsgCode.ITEM_PACK_NO_REPEAT);
			}
			
		} else {
			result = itemPackDao.update(req);
		}
		if (result != null && result.intValue() > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public BaseResponse editDetail(BaseRequest<JSONObject> req) {
		BaseResponse response = new BaseResponse();
		Integer eid = req.getEid();
		Integer packid = req.getBody().getInteger("packId");
		this.itemPackDetailDao.deleteByPackid(packid, eid);
		JSONArray details = req.getBody().getJSONArray("details");
		if (details.size() > 0) {
			this.itemPackDetailDao.create(req);
		}
		response.notify4Metadata(req.getEid(), null);
		return response;
	}

	@Override
	public BaseResponse updateStatus(BaseRequest<JSONObject> req) {
		BaseResponse response = new BaseResponse();
		this.itemPackDao.updatestatus(req);
		response.notify4Metadata(req.getEid(), null);
		return response;
	}

}
