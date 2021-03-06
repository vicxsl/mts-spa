package com.qisen.mts.spa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.dao.GoodsDao;
import com.qisen.mts.spa.dao.SpaImgDao;
import com.qisen.mts.spa.model.entity.SpaGoods;
import com.qisen.mts.spa.model.entity.SpaImg;
import com.qisen.mts.spa.model.request.SpaRequest;
@Service
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private SpaImgDao spaImgDao;
	@Value("#{configProperties['ImgCos']}")
	private String ImgCos;
	/**
	 * 新增spa商品
	 */
	@Override
	public PageResponse<List<SpaGoods>> save(SpaRequest<SpaGoods> req) {
		PageResponse<List<SpaGoods>> resp = new PageResponse<List<SpaGoods>>();
		SpaGoods spaGoods = req.getBody();
		SpaGoods queryGoods = new SpaGoods();
		queryGoods.setEid(spaGoods.getEid());
		queryGoods.setAppid(spaGoods.getAppid());
		goodsDao.saveOrUpdate(spaGoods);
		List<SpaImg> imgs = spaGoods.getGoodsImgs();
		imgs.addAll(spaGoods.getGoodsDetailImgs());
		
		SpaImg spaImg = new SpaImg();
		spaImg.setAppid(spaGoods.getAppid());
		spaImg.setGoodsId(spaGoods.getId());
		spaImgDao.deleteList(spaImg);
		if(null != imgs && imgs.size() > 0 ){
			for(SpaImg img : imgs){
				String imgurl = img.getImgUrl();
				if(imgurl.indexOf(ImgCos) != -1){
					imgurl = imgurl.replace(ImgCos, "");
					img.setImgUrl(imgurl);
				}
				img.setGoodsId(spaGoods.getId());
			}
			spaImgDao.saveList(imgs);
		}
		List<SpaGoods>  goodsList = goodsDao.list(queryGoods,0,20);
		for(SpaGoods goods: goodsList){
			List<SpaImg> goodsImgs = goods.getGoodsImgs();
			List<SpaImg> goodsDetailImgs = goods.getGoodsDetailImgs();
			if(goodsImgs != null && goodsImgs.size() > 0){
				for(SpaImg img : goodsImgs){
					img.setImgUrl(ImgCos+img.getImgUrl());
				}
				goods.setImgUrl(goods.getGoodsImgs().get(0).getImgUrl());
			}
			if(goodsDetailImgs != null && goodsDetailImgs.size() > 0){
				for(SpaImg img : goodsDetailImgs){
					img.setImgUrl(ImgCos+img.getImgUrl());
				}
			}
		}
		resp.setBody(goodsList);
		return resp;
	}

	/**
	 * 删除spa商品
	 */
	@Override
	public CommObjResponse<List<SpaGoods>> delete(SpaRequest<List<SpaGoods>> req) {
		CommObjResponse<List<SpaGoods>> resp = new CommObjResponse<List<SpaGoods>>();
		List<SpaGoods> delList = req.getBody();
		SpaGoods query = new SpaGoods();
		query.setEid(req.getEid());
		query.setAppid(req.getAppid());
		int count = goodsDao.delete(delList);
		if (count == 0 ) {
			resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
		}else{
			List<SpaGoods>  spaList = goodsDao.list(query,0,20);
			resp.setBody(spaList);
		}
		return resp;
	}

	/**
	 * 查询spa商品
	 */
	@Override
	public PageResponse<List<SpaGoods>> list(PageRequest<SpaGoods> req) {
		PageResponse<List<SpaGoods>> resp = new PageResponse<List<SpaGoods>>();
		SpaGoods spaGoods = req.getBody();
		List<SpaGoods>  spaList = goodsDao.list(spaGoods,req.getStartIndex(),req.getPageSize());
		for(SpaGoods goods: spaList){
			List<SpaImg> goodsImgs = goods.getGoodsImgs();
			List<SpaImg> goodsDetailImgs = goods.getGoodsDetailImgs();
			if(goodsImgs != null && goodsImgs.size() > 0){
				for(SpaImg img : goodsImgs){
					img.setImgUrl(ImgCos+img.getImgUrl());
				}
				goods.setImgUrl(goods.getGoodsImgs().get(0).getImgUrl());
			}
			if(goodsDetailImgs != null && goodsDetailImgs.size() > 0){
				for(SpaImg img : goodsDetailImgs){
					img.setImgUrl(ImgCos+img.getImgUrl());
				}
			}
		}
		resp.setBody(spaList);
		return resp;
	}
	
	/**
	 * 查询商品图片集合
	 */
	@Override
	public CommObjResponse<List<SpaImg>> goodsImgList(SpaRequest<SpaImg> req) {
		CommObjResponse<List<SpaImg>> resp = new CommObjResponse<List<SpaImg>>();
		SpaImg spa = req.getBody();
		List<SpaImg>  spaList = goodsDao.goodsImgList(spa);
		resp.setBody(spaList);
		return resp;
	}
	@Override
	public void updateGoodsNum(List<SpaGoods> goodsList, String inoutdepottype) {
		List<SpaGoods> oldGoodsList = new ArrayList<SpaGoods>();
		Map<String,Integer> goodsNumMap = new HashMap<String,Integer>();
		for(SpaGoods spaGoods : goodsList){
			SpaGoods goods = goodsDao.getGoodsByPara(spaGoods);
			oldGoodsList.add(goods);
			goodsNumMap.put(spaGoods.getId()+"-"+spaGoods.getEid()+spaGoods.getAppid(), spaGoods.getNum());
		}
		Integer num = 0;
		for(SpaGoods spaGoods :oldGoodsList){
			if(inoutdepottype.equals("2")){
				num = spaGoods.getNum()+goodsNumMap.get(spaGoods.getId()+"-"+spaGoods.getEid()+spaGoods.getAppid());
			}else{
				num = spaGoods.getNum()-goodsNumMap.get(spaGoods.getId()+"-"+spaGoods.getEid()+spaGoods.getAppid());
			}
			spaGoods.setNum(num);
			goodsDao.update(spaGoods);
		}
		
	}

	@Override
	public CommObjResponse<JSONObject> details(SpaRequest<SpaGoods> req) {
		CommObjResponse<JSONObject> resp=new CommObjResponse<JSONObject> ();
		SpaGoods good = req.getBody();
		SpaImg spa=new SpaImg();
		spa.setGoodsId(good.getId());
		spa.setAppid(good.getAppid());
		spa.setEid(good.getEid());
		List<SpaImg>  spaList = goodsDao.goodsImgList(spa);
		SpaGoods goods=goodsDao.details(good);
		JSONObject resBody=new JSONObject();
		resBody.put("goodsImgList", spaList);
		resBody.put("goods", goods);
		resp.setBody(resBody);
		return resp;
	}
	
}
