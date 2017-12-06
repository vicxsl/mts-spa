package com.qisen.mts.beauty.service.busi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.dao.busi.DailyDao;
import com.qisen.mts.beauty.model.entity.busi.Daily;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Service
public class DailyServiceImpl implements DailyService {

	@Autowired
	private DailyDao dailyDao;

	@Override
	public BaseResponse create(BeautyRequest<Daily> req) {
		BaseResponse response = new BaseResponse();
		Daily daily = req.getBody();
		daily.setEid(req.getEid());
		daily.setSid(req.getSid());
		Integer rcount = dailyDao.create(daily);
		if (rcount ==0) {
			response.setResult(ResultCode.FAILED);
			response.setCode(MsgCode.COMMON_NO_EXIST);
		}
		return response;
	}

	@Override
	public BaseResponse update(BeautyRequest<JSONObject> req) {
		BaseResponse response = new BaseResponse();
		int count = dailyDao.update(req.getEid(),req.getSid(),req.getBody());
		if (count == 0) {
			response.setResult(ResultCode.FAILED);
			response.setCode(MsgCode.COMMON_UPDATE_FAILED);
		}
		return response;
	}

	@Override
	public PageResponse<JSONObject> list(PageRequest<JSONObject> req) {
		PageResponse<JSONObject> resp = new PageResponse<JSONObject>();
		
		int count = dailyDao.count(req);
		if (count > 0) {
			double balance = 0;//余额
			double inBalance = 0;//收入
			double outBalance = 0;//支出
			double totalIncome = 0;//收入合计
			double totalExpenditure = 0;//支出合计
			
			List<Daily> dailys  = dailyDao.list(req);
			PageRequest<JSONObject> reqAll =req;
			reqAll.setEndIndex(0);//查询区间内总的收入、支出，不需要endIndex
			List<Daily> dailyAll =dailyDao.sumMoney(reqAll);
			List<Daily> sumList = new ArrayList<Daily>();//之前
			if(req.getPageNum() > 1){
				req.setEndIndex(req.getStartIndex() - 1);//前一页的最后一条记录
				sumList  = dailyDao.sumMoney(req);//之前所有页面的收入合计、支出合计
			}
			/*
			 * 求出之前页面的收入、支出
			 */
			for(Daily sumMoney : sumList){
				 if (sumMoney.getType().equals("1"))
		            {
					 	inBalance = sumMoney.getMoney();//收入
		            }
		            else
		            {
		            	outBalance = -sumMoney.getMoney();//支出
		            }
			}
			/*
			 * 求出当前查询区间收入合计、支出合计
			 */
			for(Daily total : dailyAll){
				 if (total.getType().equals("1"))
		            {
					 	totalIncome = total.getMoney();//收入
		            }
		            else
		            {
		            	totalExpenditure  = -total.getMoney();//支出
		            }
			}
			/*
			 * 求出当前页面每条记录的余额
			 */
			
			for(Daily daily : dailys){
				 if (daily.getType().equals("1"))
		            {
					 	balance += daily.getMoney();
		            }
		            else
		            {
		            	balance += -daily.getMoney();
		            }
				 daily.setBalance(balance+inBalance+outBalance);//当前页面的余额+之前页面的收入-之前页面的支出
			}
			JSONObject json = new JSONObject();
			json.put("DailyList", dailys);
			json.put("TotalIncome", totalIncome);
			json.put("TotalExpenditure", totalExpenditure);
			resp.setBody(json);
			resp.setPageSize(req.getPageSize());
			resp.setPageNum(req.getPageNum());
			resp.setCount(count);
		}
		return resp;
	}

}
