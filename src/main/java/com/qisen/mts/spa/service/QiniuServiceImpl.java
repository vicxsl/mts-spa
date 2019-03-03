package com.qisen.mts.spa.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.util.FileUtil;
@Service
public class QiniuServiceImpl implements QiniuService {
	private static final Logger logger = LoggerFactory.getLogger(QiniuServiceImpl.class);
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "P3kIxmvLZWZnphFeAiAY_51yKYt3S9X8f54KZRqp";
	String SECRET_KEY = "6hF-P42amavLwa5mbSzY4Pz2tSF8KXTGVe0ecART";
	// 要上传的空间
	String bucketname = "sentreeshop";

	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
	Configuration cfg = new Configuration(Zone.zone2());
	// ...其他参数参考类注释
	UploadManager uploadManager = new UploadManager(cfg);

	// 测试域名，只有30天有效期
	private static String QINIU_IMAGE_DOMAIN = "http://pnmomcx8j.bkt.clouddn.com/";

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	@Override
	public String getUpToken() throws Exception {
		return auth.uploadToken(bucketname);
	}

	@Override
	public CommObjResponse<JSONObject>  saveImage(MultipartFile file) throws Exception {
		CommObjResponse<JSONObject>  respone=new CommObjResponse<JSONObject> ();
		JSONObject body=new JSONObject();
		int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if (dotPos < 0) {
            throw new Exception();
        }
        String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
        // 判断是否是合法的文件后缀
        if (!FileUtil.isFileAllowed(fileExt)) {
        	 throw new Exception();
        }

        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
        // 调用put方法上传
        Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
        // 打印返回的信息
        if (res.isOK() && res.isJson()) {
        	body.put("imgName", JSONObject.parseObject(res.bodyString()).get("key"));
            // 返回这张存储照片的地址
            respone.setBody(body);
        } else {
            logger.error("七牛异常:" + res.bodyString());
            throw new Exception();
        }
        return respone;
	}

}
