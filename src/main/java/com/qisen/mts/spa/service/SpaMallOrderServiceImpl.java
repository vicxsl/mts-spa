package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsShopCarDao;
import com.qisen.mts.spa.dao.MemberAddressDao;
import com.qisen.mts.spa.dao.SpaInoutDepotDetailDao;
import com.qisen.mts.spa.dao.SpaMallOrderDao;
import com.qisen.mts.spa.model.entity.MemberAddress;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;
import com.qisen.mts.spa.model.entity.SpaMallOrder;
import com.qisen.mts.spa.model.request.SpaRequest;

@Service
public class SpaMallOrderServiceImpl implements SpaMallOrderService{
	
	@Autowired
	private SpaMallOrderDao spaMallOrderDao;
	
	@Autowired
	private SpaInoutDepotDetailDao inoutDepotDetailDao;
	
	@Autowired
	private MemberAddressDao memberAddressDao;
	
	@Autowired
	private GoodsShopCarDao goodsShopCarDao;

	@Override
	public CommObjResponse<SpaMallOrder> save(SpaRequest<SpaMallOrder> req) {
		CommObjResponse<SpaMallOrder> resp = new CommObjResponse<SpaMallOrder>();
		SpaMallOrder body = req.getBody();
		if(!CollectionUtils.isEmpty(body.getGoodsList())){
			int eid = req.getEid();
			String appid = req.getAppid();
			String openid = req.getToken();
					spaMallOrderDao.create(body);//插入订单表

					int orderId = body.getId();
			List<SpaInoutDepotDetail> details = body.getGoodsList();
			for(SpaInoutDepotDetail spaInoutDepotDetail:details){
				spaInoutDepotDetail.setOrderId(orderId);
				spaInoutDepotDetail.setTotalMoney(spaInoutDepotDetail.getNum() * spaInoutDepotDetail.getPreferencePrice());
			}
			//执行明细表的插入或修改操作
			inoutDepotDetailDao.saveList(details);
			goodsShopCarDao.deleteByOrder(body);
			MemberAddress address = body.getMemberAddress();
			address.setAppid(appid);
			address.setEid(eid);
			address.setOpenid(openid);
			address.setOrderId(orderId);
			memberAddressDao.create(address);
			resp.setBody(body);
		}
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaMallOrder>> list(SpaRequest<SpaMallOrder> req) {
		SpaMallOrder body = req.getBody();
		CommObjResponse<List<SpaMallOrder>> response = new CommObjResponse<List<SpaMallOrder>>();
		response.setBody(spaMallOrderDao.list(body));
		return response;
	}

}
