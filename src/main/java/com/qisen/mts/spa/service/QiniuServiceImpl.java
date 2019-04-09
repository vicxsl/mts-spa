package com.qisen.mts.spa.service;

import java.util.Map;
import java.util.UUID;

import org.apache.poi.util.SystemOutLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.util.FileUtil;
import com.qisen.mts.spa.model.entity.SpaImg;

@Service
public class QiniuServiceImpl implements QiniuService {
	private static final Logger logger = LoggerFactory.getLogger(QiniuServiceImpl.class);

	// 1 初始化用户身份信息（secretId, secretKey）。
	@Value("#{configProperties['SecretId']}")
	private String SecretId;
	// 1 初始化用户身份信息（secretId, secretKey）。
	@Value("#{configProperties['SecretKey']}")
	private String SecretKey;
	// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
	@Value("#{configProperties['bucketName']}")
	private String bucketName; 
	@Value("#{configProperties['ImgCos']}")
	private String ImgCos;
	
	// 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
	Configuration cfg = new Configuration(Zone.zone2());
	// ...其他参数参考类注释
	UploadManager uploadManager = new UploadManager(cfg);
	
	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
//	@Override
	public String getUpToken() throws Exception {
		// 密钥配置
		Auth auth = Auth.create(SecretId, SecretKey);
		return auth.uploadToken(bucketName);
	}

	@Override
	public CommObjResponse<SpaImg> saveImage(MultipartHttpServletRequest request) throws Exception {
		CommObjResponse<SpaImg> respone = new CommObjResponse<SpaImg>();
		MultipartFile file = request.getFile("upload_file");
		SpaImg body = new SpaImg();
		if (null != file) {
			
			String originalFilename = file.getOriginalFilename();
			int dotPos = originalFilename.lastIndexOf(".");
	        if (dotPos < 0) {
	            throw new Exception();
	        }
	        String fileExt = originalFilename.substring(dotPos + 1).toLowerCase();
	        // 判断是否是合法的文件后缀
	        if (!FileUtil.isFileAllowed(fileExt)) {
	        	 throw new Exception();
	        }
	        String pageName = originalFilename.substring(0,dotPos).replaceAll("-", "/");
	        String fileName =pageName+ UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;//图片名称
	        
			// 调用put方法上传
			Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());// 图片上传动作
			// 打印返回的信息
			if (res.isOK() && res.isJson()) {
				String imgUrl = JSONObject.parseObject(res.bodyString()).getString("key");
				 body.setImgUrl(ImgCos+imgUrl);
				// 返回这张存储照片的地址
				respone.setBody(body);
			} else {
				logger.error("七牛异常:" + res.bodyString());
			}
		} else {
			logger.error("七牛异常:");
		}
		return respone;
	}

}
