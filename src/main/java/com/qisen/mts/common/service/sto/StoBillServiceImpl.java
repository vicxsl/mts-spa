package com.qisen.mts.common.service.sto;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.dao.sto.StoBillDao;
import com.qisen.mts.common.dao.sto.StoBillDetailDao;
import com.qisen.mts.common.dao.sto.StoItemDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.entity.sto.BillDetail;
import com.qisen.mts.common.model.entity.sto.BillWithDetails;
import com.qisen.mts.common.model.entity.sto.Item;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.BulkChangeStatusRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.StoBillListRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.model.response.StoBillListResponse;
import com.qisen.mts.gateway.ExceptionWithCode;

@Service
public class StoBillServiceImpl implements StoBillService {
	
	private static final Logger logger  = LoggerFactory.getLogger(StoBillServiceImpl.class);

	@Autowired
	private StoBillDao stoBillDao;
	@Autowired
	private StoBillDetailDao stoBillDetailDao;
	@Autowired
	private StoItemDao stoItemDao;

	@Override
	public BaseResponse create(BaseRequest<BillWithDetails> request) {
		logger.debug(request.toString());
		request.getBody().setEid(request.getEid());
		request.getBody().setSid(request.getSid());
		BaseResponse response = new BaseResponse();
		// 首先插入STOBILL记录
		BillWithDetails bd = request.getBody();
		this.stoBillDao.create(bd);
		// 再插入STOBILLDETAIL记录
		this.stoBillDao.createDetails(bd);
		return response;
	}

	@Override
	public BaseResponse list(PageRequest<StoBillListRequest> request) {
		PageResponse<StoBillListResponse> resp = new PageResponse<>();
		StoBillListResponse listResp = this.stoBillDao.listCounts(request);
		resp.setCount(listResp.getTotalCount());
		resp.setPageSize(request.getPageSize());
		listResp.setItems(this.stoBillDao.stoBillList(request));
		resp.setBody(listResp);
		return resp;
	}

	@Override
	public BaseResponse bulkChangeStatus(BaseRequest<BulkChangeStatusRequest> req) throws Exception {
		// 每个单据单独处理，方便计算库存
		for (Integer stoBillId : req.getBody().getIds()) {
			// 首先加载出STOBILL和STOBILLDETAIL
			BillWithDetails bill = this.stoBillDao.loadBillWithDetails(stoBillId);
			// 审核
			if (req.getBody().getStatus() == 2 && bill.getStatus() == 1) {
				// 更新STOBILL状态
				bill.setStatus(req.getBody().getStatus());
				bill.setExamId(req.getBody().getExamId());
				bill.setExamName(req.getBody().getExamName());
				this.stoBillDao.update(bill);
				// 更新库存状态
				for (BillDetail detail : bill.getItems()) {
					switch (bill.getbType()) {
					// 入库
					case 1:
						// 尝试加载对应的库存记录
						Item stoInItem = new Item();
						stoInItem.setEid(bill.getEid());
						stoInItem.setSid(bill.getSid());
						stoInItem.setItemId(detail.getItemId());
						stoInItem = this.stoItemDao.findByProduct(stoInItem);
						// 没找到就创建新的库存记录
						if (stoInItem == null) {
							stoInItem = new Item();
							stoInItem.setEid(bill.getEid());
							stoInItem.setSid(bill.getSid());
							stoInItem.setItemId(detail.getItemId());
							stoInItem.setInitNum(detail.getNum());
							stoInItem.setInNum(detail.getNum());
							stoInItem.setInMoney(detail.getMoney());
							stoInItem.setStoNum(detail.getNum());
							stoInItem.setStoMoney(detail.getMoney());
							stoInItem.setOutMoney(0.0);
							stoInItem.setOutNum(0);
							stoInItem.setSalesNum(0);
							stoInItem.setSalesMoney(0.0);
							stoInItem.setSelfBuyNum(0);
							stoInItem.setSelfBuyMoney(0.0);
							stoInItem.setUseNum(0);
							stoInItem.setUseMoney(0.0);
							stoInItem.setInPrice(detail.getInPrice());
							stoInItem.setCost(0.0);
							// 查询总部的库存记录，复制进价，成本价
//							Item stoHQItem = new Item();
//							stoHQItem.setEid(req.getEid());
//							stoHQItem.setItemId(detail.getItemId());
//							stoHQItem = this.stoItemDao.findByProduct(stoHQItem);
//							stoInItem.setInPrice(stoHQItem.getInPrice());
//							stoInItem.setCost(stoHQItem.getCost());
							// 创建门店的库存记录
							this.stoItemDao.create(stoInItem);
						} else {// 更新已有的库存记录
							stoInItem.setInNum((stoInItem.getInNum() == null ? 0 : stoInItem.getInNum()) + detail.getNum());
							stoInItem.setInMoney((stoInItem.getInMoney() == null ? 0 : stoInItem.getInMoney()) + detail.getMoney());
							stoInItem.setStoNum(stoInItem.getStoNum() + detail.getNum());
							stoInItem.setStoMoney(stoInItem.getStoMoney() + detail.getMoney());
							stoInItem.setInPrice(detail.getInPrice());
							stoInItem.setLastInStoDate(new Date());
							if(this.stoItemDao.update(stoInItem)!=1){
								throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
							}
						}
						break;
					// 出库
					case 2:
						// 尝试加载对应的库存记录
						Item stoOutItem = new Item();
						stoOutItem.setEid(bill.getEid());
						stoOutItem.setSid(bill.getSid());
						stoOutItem.setItemId(detail.getItemId());
						stoOutItem = this.stoItemDao.findByProduct(stoOutItem);
						// 没找到就抛出异常
						if (stoOutItem == null || stoOutItem.getStoNum() < detail.getNum()) {
							throw new ExceptionWithCode(MsgCode.NOT_ENOUGH_INVENTORY);
						}
						if(stoOutItem.getStoMoney() < detail.getMoney()){
							throw new ExceptionWithCode(MsgCode.NOT_ENOUGH_STOMONEY);
						}
						// 更新已有的库存记录
						stoOutItem.setOutNum((stoOutItem.getOutNum() == null ? 0 : stoOutItem.getOutNum()) + detail.getNum());
						stoOutItem.setOutMoney((stoOutItem.getOutMoney() == null ? 0 : stoOutItem.getOutMoney()) + detail.getMoney());
						stoOutItem.setStoNum(stoOutItem.getStoNum() - detail.getNum());
						stoOutItem.setStoMoney(stoOutItem.getStoMoney() - detail.getMoney());
						// 领用
						if (bill.getsType() == 7) {
							stoOutItem.setUseNum((stoOutItem.getUseNum() == null ? 0 : stoOutItem.getUseNum()) + detail.getNum());
							stoOutItem.setUseMoney((stoOutItem.getUseMoney() == null ? 0 : stoOutItem.getUseMoney()) + detail.getMoney());
						}
						// 自购
						else if (bill.getsType() == 8) {
							stoOutItem.setSelfBuyNum((stoOutItem.getSelfBuyNum() == null ? 0 : stoOutItem.getSelfBuyNum()) + detail.getNum());
							stoOutItem.setSelfBuyMoney((stoOutItem.getSelfBuyMoney() == null ? 0 : stoOutItem.getSelfBuyMoney()) + detail.getMoney());
						}
						if(this.stoItemDao.update(stoOutItem)!=1){
							throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
						}
						break;
					// 调拨
					case 3:
						// 尝试加载调拨出库门店的库存记录
						Item stoTranOutItem = new Item();
						stoTranOutItem.setEid(bill.getEid());
						stoTranOutItem.setSid(bill.getOutSid());
						stoTranOutItem.setItemId(detail.getItemId());
						stoTranOutItem = this.stoItemDao.findByProduct(stoTranOutItem);
						// 出库门店没库存就抛出异常
						if (stoTranOutItem == null || stoTranOutItem.getStoNum() < detail.getNum()) {
							throw new ExceptionWithCode(MsgCode.NOT_ENOUGH_INVENTORY);
						}
						if(stoTranOutItem.getStoMoney() < detail.getMoney()){
							throw new ExceptionWithCode(MsgCode.NOT_ENOUGH_STOMONEY);
						}
						// 尝试加载调拨入库门店的库存记录
						Item stoTranInItem = new Item();
						stoTranInItem.setEid(bill.getEid());
						stoTranInItem.setSid(bill.getInSid());
						stoTranInItem.setItemId(detail.getItemId());
						stoTranInItem = this.stoItemDao.findByProduct(stoTranInItem);

						// 更新调拨出库门店的库存记录
						stoTranOutItem.setOutNum((stoTranOutItem.getOutNum() == null ? 0 : stoTranOutItem.getOutNum()) + detail.getNum());
						stoTranOutItem.setOutMoney((stoTranOutItem.getOutMoney() == null ? 0 : stoTranOutItem.getOutMoney()) + detail.getMoney());
						stoTranOutItem.setStoNum(stoTranOutItem.getStoNum() - detail.getNum());
						stoTranOutItem.setStoMoney(stoTranOutItem.getStoMoney() - detail.getMoney());
						if(this.stoItemDao.update(stoTranOutItem)!=1){
							throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
						}
						
						// 更新调拨入库门店的库存记录
						// 没找到就创建新的库存记录
						if (stoTranInItem == null) {
							stoTranInItem = new Item();
							stoTranInItem.setEid(bill.getEid());
							stoTranInItem.setSid(bill.getInSid());
							stoTranInItem.setItemId(detail.getItemId());
							stoTranInItem.setInitNum(detail.getNum());
							stoTranInItem.setInNum(detail.getNum());
							stoTranInItem.setInMoney(detail.getMoney());
							stoTranInItem.setStoNum(detail.getNum());
							stoTranInItem.setStoMoney(detail.getMoney());
							stoTranInItem.setOutMoney(0.0);
							stoTranInItem.setOutNum(0);
							stoTranInItem.setSalesNum(0);
							stoTranInItem.setSalesMoney(0.0);
							stoTranInItem.setSelfBuyNum(0);
							stoTranInItem.setSelfBuyMoney(0.0);
							stoTranInItem.setUseNum(0);
							stoTranInItem.setUseMoney(0.0);
							stoTranInItem.setInPrice(detail.getInPrice());
							stoTranInItem.setCost(0.0);
							// 查询总部的库存记录，复制进价，成本价
//							Item stoHQItem = new Item();
//							stoHQItem.setEid(req.getEid());
//							stoHQItem.setItemId(detail.getItemId());
//							stoHQItem = this.stoItemDao.findByProduct(stoHQItem);
//							stoTranInItem.setInPrice(stoHQItem.getInPrice());
//							stoTranInItem.setCost(stoHQItem.getCost());
							// 创建门店的库存记录
							this.stoItemDao.create(stoTranInItem);
						} else {
							// 更新已有的库存记录
							stoTranInItem.setInNum((stoTranInItem.getInNum() == null ? 0 : stoTranInItem.getInNum()) + detail.getNum());
							stoTranInItem.setInMoney((stoTranInItem.getInMoney() == null ? 0 : stoTranInItem.getInMoney()) + detail.getMoney());
							stoTranInItem.setStoNum(stoTranInItem.getStoNum() + detail.getNum());
							stoTranInItem.setStoMoney(stoTranInItem.getStoMoney() + detail.getMoney());
							stoTranInItem.setInPrice(detail.getInPrice());
							stoTranInItem.setLastInStoDate(new Date());
							if(this.stoItemDao.update(stoTranInItem)!=1){
								throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
							}
						}
						break;
					}
					// 更新STOBILLDETAIL
					detail.setStatus(req.getBody().getStatus());
					if(this.stoBillDetailDao.update(detail)!=1){
						throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
					}
				}

			}
			// 撤单
			else if (req.getBody().getStatus() == 0) {
				// 未审核撤单不需要更新库存
				if (bill.getStatus() == 1) {
					// 更新STOBILL状态
					bill.setStatus(req.getBody().getStatus());
					bill.setExamId(req.getBody().getExamId());
					bill.setExamName(req.getBody().getExamName());
					this.stoBillDao.update(bill);
					// 更新STOBILLDETAIL
					for (BillDetail detail : bill.getItems()) {
						detail.setStatus(req.getBody().getStatus());
						this.stoBillDetailDao.update(detail);
					}
				}
				// 已审核撤单需要更新库存
				else if (bill.getStatus() == 2) {
					// 更新STOBILL状态
					bill.setStatus(req.getBody().getStatus());
					bill.setExamId(req.getBody().getExamId());
					bill.setExamName(req.getBody().getExamName());
					this.stoBillDao.update(bill);
					// 更新库存状态
					for (BillDetail detail : bill.getItems()) {
						switch (bill.getbType()) {
						// 入库
						case 1:
							// 尝试加载对应的库存记录
							Item stoInItem = new Item();
							stoInItem.setEid(bill.getEid());
							stoInItem.setSid(bill.getSid());
							stoInItem.setItemId(detail.getItemId());
							stoInItem = this.stoItemDao.findByProduct(stoInItem);
							// 更新已有的库存记录
							stoInItem.setInNum((stoInItem.getInNum() == null ? 0 : stoInItem.getInNum()) - detail.getNum());
							stoInItem.setInMoney((stoInItem.getInMoney() == null ? 0 : stoInItem.getInMoney()) - detail.getMoney());
							stoInItem.setStoNum(stoInItem.getStoNum() - detail.getNum());
							stoInItem.setStoMoney(stoInItem.getStoMoney() - detail.getMoney());
							if(this.stoItemDao.update(stoInItem)!=1){
								throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
							}
							break;
						// 出库
						case 2:
							// 尝试加载对应的库存记录
							Item stoOutItem = new Item();
							stoOutItem.setEid(bill.getEid());
							stoOutItem.setSid(bill.getSid());
							stoOutItem.setItemId(detail.getItemId());
							stoOutItem = this.stoItemDao.findByProduct(stoOutItem);
							// 更新已有的库存记录
							stoOutItem.setOutNum((stoOutItem.getOutNum() == null ? 0 : stoOutItem.getOutNum()) - detail.getNum());
							stoOutItem.setOutMoney((stoOutItem.getOutMoney() == null ? 0 : stoOutItem.getOutMoney()) - detail.getMoney());
							stoOutItem.setStoNum(stoOutItem.getStoNum() + detail.getNum());
							stoOutItem.setStoMoney(stoOutItem.getStoMoney() + detail.getMoney());
							// 领用
							if (bill.getsType() == 7) {
								stoOutItem.setUseNum((stoOutItem.getUseNum() == null ? 0 : stoOutItem.getUseNum()) - detail.getNum());
								stoOutItem.setUseMoney((stoOutItem.getUseMoney() == null ? 0 : stoOutItem.getUseMoney()) - detail.getMoney());
							}
							// 自购
							else if (bill.getsType() == 8) {
								stoOutItem.setSelfBuyNum((stoOutItem.getSelfBuyNum() == null ? 0 : stoOutItem.getSelfBuyNum()) - detail.getNum());
								stoOutItem.setSelfBuyMoney((stoOutItem.getSelfBuyMoney() == null ? 0 : stoOutItem.getSelfBuyMoney()) - detail.getMoney());
							}
							if(this.stoItemDao.update(stoOutItem)!=1){
								throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
							}
							break;
						// 调拨
						case 3:
							// 尝试加载调拨出库门店的库存记录
							Item stoTranOutItem = new Item();
							stoTranOutItem.setEid(bill.getEid());
							stoTranOutItem.setSid(bill.getOutSid());
							stoTranOutItem.setItemId(detail.getItemId());
							stoTranOutItem = this.stoItemDao.findByProduct(stoTranOutItem);
							// 尝试加载调拨入库门店的库存记录
							Item stoTranInItem = new Item();
							stoTranInItem.setEid(bill.getEid());
							stoTranInItem.setSid(bill.getInSid());
							stoTranInItem.setItemId(detail.getItemId());
							stoTranInItem = this.stoItemDao.findByProduct(stoTranInItem);

							// 更新调拨出库门店的库存记录
							stoTranOutItem.setOutNum((stoTranOutItem.getOutNum() == null ? 0 : stoTranOutItem.getOutNum()) - detail.getNum());
							stoTranOutItem.setOutMoney((stoTranOutItem.getOutMoney() == null ? 0 : stoTranOutItem.getOutMoney()) - detail.getMoney());
							stoTranOutItem.setStoNum(stoTranOutItem.getStoNum() + detail.getNum());
							stoTranOutItem.setStoMoney(stoTranOutItem.getStoMoney() + detail.getMoney());
							if(this.stoItemDao.update(stoTranOutItem)!=1){
								throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
							}
							// 更新调拨入库门店的库存记录
							stoTranInItem.setInNum((stoTranInItem.getInNum() == null ? 0 : stoTranInItem.getInNum()) - detail.getNum());
							stoTranInItem.setInMoney((stoTranInItem.getInMoney() == null ? 0 : stoTranInItem.getInMoney()) - detail.getMoney());
							stoTranInItem.setStoNum(stoTranInItem.getStoNum() - detail.getNum());
							stoTranInItem.setStoMoney(stoTranInItem.getStoMoney() - detail.getMoney());
							if(this.stoItemDao.update(stoTranInItem)!=1){
								throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
							}
							break;
						}
						// 更新STOBILLDETAIL
						detail.setStatus(req.getBody().getStatus());
						if(this.stoBillDetailDao.update(detail)!=1){
							throw new ExceptionWithCode(MsgCode.INVENTORY_OR_BILL_DETAIL_UPDATE_FAILED);
						}
					}
				}
			}
		}
		return new BaseResponse();
	}

}
