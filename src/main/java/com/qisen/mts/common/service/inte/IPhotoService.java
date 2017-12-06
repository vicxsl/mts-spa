package com.qisen.mts.common.service.inte;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.PhotoRequest;
import com.qisen.mts.common.model.request.UploadParam;
import com.qisen.mts.common.model.response.BaseResponse;

/**
 * @author GuoJun
 * @date 2016年9月30日 下午4:04:25
 * @describe 图库的service
 */
public interface IPhotoService {
	
	/**得到图库分类列表
	 * @return
	 */
	BaseResponse getLibraryList();

	/**
	 * 获取客户空间分类
	 * @param request
	 * @return
	 */
	BaseResponse getUserStorageCategories(BaseRequest<Object> request);

	/**
	 * 添加客户空间分类
	 * @param request
	 * @throws IOException 
	 */
	BaseResponse addUserStorageCategory(BaseRequest<PhotoRequest> request);

	/**删除客户空间分类
	 * @param request
	 */
	BaseResponse deleteUserStorageCategory(BaseRequest<PhotoRequest> request);

	/**修改客户空间分类
	 * @param request
	 * @return
	 */
	BaseResponse updateUserStorageCategory(BaseRequest<PhotoRequest> request);

	/** 刪除文件
	 * @param request
	 * @return
	 */
	BaseResponse deleteFile(BaseRequest<PhotoRequest> request);

	/**
	 * 得到每个小类小面的图片
	 * @param request
	 * @return
	 */
	BaseResponse getFiles(PageRequest<PhotoRequest> request);
	
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 * @return
	 */
	BaseResponse processUpload(MultipartFile file, UploadParam param)throws Exception;

	/**
	 * 上传BASE64图片
	 * @param param
	 * @return
	 */
	BaseResponse processUploadBase64(UploadParam param)throws Exception;
}
