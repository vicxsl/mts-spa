package com.qisen.mts.beauty.controller.inte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.PhotoRequest;
import com.qisen.mts.common.model.request.UploadParam;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.service.inte.IPhotoService;

@RequestMapping("/beauty/photo")
@Controller
public class PhotoController{
	@Autowired
	IPhotoService photoService; 
	
	/**
	 * 获取公共图库分类	
	 */
	@RequestMapping("/getPhotoLibraryCategories")
	@ResponseBody
	public BaseResponse getPhotoLibraryCategories(){
		return this.photoService.getLibraryList();
	}
	
	/**
	 *  获取客户空间分类
	 */
	@RequestMapping("/getUserStorageCategories")
	@ResponseBody
	public BaseResponse getUserStorageCategories(@RequestBody BeautyRequest<Object> request){
		return this.photoService.getUserStorageCategories(request);
	}
	
	/**
	 *  添加客户空间分类
	 */
	@RequestMapping("/addUserStorageCategory")
	@ResponseBody
	public BaseResponse addUserStorageCategory(@RequestBody BeautyRequest<PhotoRequest> request){
		return this.photoService.addUserStorageCategory(request);
	}
	
	/**
	 *  删除客户空间分类
	 */
	@RequestMapping("/deleteUserStorageCategory")
	@ResponseBody
	public BaseResponse deleteUserStorageCategory(@RequestBody BeautyRequest<PhotoRequest> request){
		return this.photoService.deleteUserStorageCategory(request);
	}
	
	/**
	 *  修改客户空间分类
	 */
	@RequestMapping("/modifyUserStorageCategory")
	@ResponseBody
	public BaseResponse modifyUserStorageCategory(@RequestBody BeautyRequest<PhotoRequest> request){
		return this.photoService.updateUserStorageCategory(request);
	}
	
	/** 
	 * 删除客户空间图片
	 */
	@RequestMapping("/deleteFile")
	@ResponseBody
	public BaseResponse deleteFile(@RequestBody BeautyRequest<PhotoRequest> request){
		return this.photoService.deleteFile(request);
	}
	
	/** 
	 * 得到某个小类下面的图片
	 */
	@RequestMapping("/getFiles")
	@ResponseBody
	public BaseResponse getFiles(@RequestBody PageRequest<PhotoRequest> request){
		return this.photoService.getFiles(request);
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public BaseResponse processUpload(MultipartFile file, UploadParam param)throws Exception{
		return this.photoService.processUpload(file, param);
	}
	
	/**
	 * 上传BASE64文件
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadBase64")
	@ResponseBody
	public BaseResponse processUploadBase64(UploadParam param)throws Exception{
		return this.photoService.processUploadBase64(param);
	}
	
}
