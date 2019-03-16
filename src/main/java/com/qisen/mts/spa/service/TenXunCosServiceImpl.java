package com.qisen.mts.spa.service;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.util.FileUtil;
import com.qisen.mts.spa.model.entity.SpaImg;

@Service
public class TenXunCosServiceImpl implements TenXunCosService {
	// 1 初始化用户身份信息（secretId, secretKey）。
	@Value("#{configProperties['SecretId']}")
	private String SecretId;
	// 1 初始化用户身份信息（secretId, secretKey）。
	@Value("#{configProperties['SecretKey']}")
	private String SecretKey;
	// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
	@Value("#{configProperties['bucketName']}")
	private String bucketName;
	@Value("#{configProperties['ApRegion']}")
	private String ApRegion;
	@Value("#{configProperties['ImgCos']}")
	private String ImgCos;

	@Override
	public CommObjResponse<SpaImg> saveImage(MultipartHttpServletRequest request) throws Exception {
		CommObjResponse<SpaImg> respone = new CommObjResponse<SpaImg>();
		MultipartFile file =  request.getFile("upload_file");
		InputStream inputStream = file.getInputStream();
		// 1 初始化用户身份信息（secretId, secretKey）。
		COSCredentials cred = new BasicCOSCredentials(SecretId, SecretKey);
		// 2 设置bucket的区域, COS地域的简称请参照
		// https://cloud.tencent.com/document/product/436/6224
		// clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法,
		// 使用可参见源码或者接口文档 FAQ 中说明。
		ClientConfig clientConfig = new ClientConfig(new Region(ApRegion));
		// 3 生成 cos 客户端。
		COSClient cosClient = new COSClient(cred, clientConfig);

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
        String key =pageName+ UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;//图片名称
		// 指定要上传到 COS 上对象键

		// 线程池大小，建议在客户端与COS网络充足(如使用腾讯云的CVM，同园区上传COS)的情况下，设置成16或32即可, 可较充分的利用网络资源
		// 对于使用公网传输且网络带宽质量不高的情况，建议减小该值，避免因网速过慢，造成请求超时。
		ExecutorService threadPool = Executors.newFixedThreadPool(32);
		// 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
		TransferManager transferManager = new TransferManager(cosClient, threadPool);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key,inputStream, objectMetadata);
		// 本地文件上传
		Upload upload = transferManager.upload(putObjectRequest);
		 // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
		upload.waitForCompletion();
		UploadResult uploadResult = upload.waitForUploadResult();
		SpaImg spa = new SpaImg();
		spa.setImgUrl(ImgCos+uploadResult.getKey());
		respone.setBody(spa);
		// 关闭 TransferManger
		transferManager.shutdownNow();
		// 关闭客户端(关闭后台线程)
		cosClient.shutdown();
		return respone;
	}

}
