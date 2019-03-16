package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaImg;

public interface SpaImgDao {
	/**
	 * 批量删除
	 * @param 
	 * @return
	 */
	public void deleteList(SpaImg spa);
	
	/**
	 * 批量新增
	 * @param 
	 * @return
	 */
	public void saveList(List<SpaImg> list);
	
	public List<SpaImg> spaImgList(SpaImg spa);
}
