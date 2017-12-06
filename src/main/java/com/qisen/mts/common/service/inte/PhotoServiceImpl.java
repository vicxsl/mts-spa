package com.qisen.mts.common.service.inte;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.ImageVariation;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.PhotoRequest;
import com.qisen.mts.common.model.request.UploadParam;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.util.ConfigUtil;
import com.qisen.mts.common.util.FileUtil;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Service
public class PhotoServiceImpl implements IPhotoService {
	private static final String USER_PHOTO_FOLDER_NAME = "customize";
	private static final String PHOTO_LIB_FOLDER_NAME = "/photoLibrary";
	@Autowired
	private ConfigUtil config;

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse getLibraryList() {
		CommObjResponse<List<Object>> resp = new CommObjResponse<>();
		List<Object> list = new ArrayList<>();
		String url = config.getPara("PHOTO_PATH")+PHOTO_LIB_FOLDER_NAME;
		File file = new File(url);
		list = this.getChildRecursiveFileName(file, false);
		for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			if (USER_PHOTO_FOLDER_NAME.equals(map.get("name")))
				iterator.remove();
		}
		resp.setBody(list);
		return resp;
	}

	/**
	 * @param file
	 *            递归得到文件夹下面子文件夹 flag为true返回文件类型，为false不返回文件类型
	 * @return
	 */
	private List<Object> getChildRecursiveFileName(File file, boolean flag) {
		List<Object> list = new ArrayList<Object>();
		File[] fileArr1 = file.listFiles();
		if (fileArr1 == null)
			return null;
		;
		// 过滤掉大图 _l 中图 _m 小图_s
		List<File> fileList = new ArrayList<File>();
		for (File file2 : fileArr1) {
			if (file2.isDirectory()) {
				fileList.add(file2);
				continue;
			}
			String name = file2.getName().split("\\.")[0];
			if (!name.endsWith("_l") && !name.endsWith("_m") && !name.endsWith("_s"))
				fileList.add(file2);
		}
		// 按照修改时间排序
		FileUtil.quickSortByLastModifyTime(fileList);
		for (File childFile : fileList) {
			Map<String, Object> map = new HashMap<String, Object>();
			File child = new File(file.getPath() + "/" + childFile.getName());
			if (child.isDirectory()) {
				List<Object> childList = this.getChildRecursiveFileName(child, flag);
				if (childList.size() > 0) {
					map.put("sub", childList);
				}
			} else if (!flag) {
				continue;
			}
			map.put("name", childFile.getName());
			list.add(map);
		}
		return list;
	}

	/**
	 * 循环删除file
	 * 
	 * @param file
	 */
	private boolean deleteRecursiveFile(File file) {
		if (!file.exists())
			return true;
		if (file.isDirectory()) {
			File f[] = file.listFiles();
			if (f == null || f.length == 0) {
				return file.delete();
			}
			for (File fi2 : f) {
				if (this.deleteRecursiveFile(fi2)) {
					continue;
				} else {
					return false;
				}
			}
			return file.delete();
		} else {
			return file.delete();
		}
	}

	@Override
	public BaseResponse getUserStorageCategories(BaseRequest<Object> request) {
		CommObjResponse<List<Object>> resp = new CommObjResponse<>();
		List<Object> list = new ArrayList<Object>();
		String url = config.getPara("PHOTO_PATH")+PHOTO_LIB_FOLDER_NAME;
		// String url=ConfigUtils.getValue("PHOTO_URL");
		File file = new File(url + "/"+USER_PHOTO_FOLDER_NAME+"/" + request.getEid());
		list = this.getChildRecursiveFileName(file, false);
		resp.setBody(list);
		return resp;
	}

	@Override
	public BaseResponse addUserStorageCategory(BaseRequest<PhotoRequest> request) {
		String url = config.getPara("PHOTO_PATH")+PHOTO_LIB_FOLDER_NAME;
		// String url=ConfigUtils.getValue("PHOTO_URL");
		if (request.getEid() == 0 || StringUtils.isEmpty(request.getBody().getName())) {
			return new CommObjResponse<Boolean>(false);
		}
		File file = new File(url + "/"+USER_PHOTO_FOLDER_NAME+"/" + request.getEid() + "/" + request.getBody().getName());
		return new CommObjResponse<Boolean>(file.mkdirs());
	}

	@Override
	public BaseResponse deleteUserStorageCategory(BaseRequest<PhotoRequest> request) {
		String url = config.getPara("PHOTO_PATH")+PHOTO_LIB_FOLDER_NAME;
		// String url=ConfigUtils.getValue("PHOTO_URL");
		if (request.getEid() == 0 || StringUtils.isEmpty(request.getBody().getName()))
			return new CommObjResponse<Boolean>(false);
		File file = new File(url + "/"+USER_PHOTO_FOLDER_NAME+"/" + request.getEid() + "/" + request.getBody().getName());
		return new CommObjResponse<Boolean>(this.deleteRecursiveFile(file));
	}

	@Override
	public BaseResponse updateUserStorageCategory(BaseRequest<PhotoRequest> request) {
		String url = config.getPara("PHOTO_PATH")+PHOTO_LIB_FOLDER_NAME;
		// String url=ConfigUtils.getValue("PHOTO_URL");
		if (request.getEid() == 0 || StringUtils.isEmpty(request.getBody().getName())
				|| StringUtils.isEmpty(request.getBody().getNewName()))
			return new CommObjResponse<Boolean>(false);
		File file = new File(url + "/"+USER_PHOTO_FOLDER_NAME+"/" + request.getEid() + "/" + request.getBody().getName());
		return new CommObjResponse<Boolean>(
				file.renameTo(new File(url + "/"+USER_PHOTO_FOLDER_NAME+"/" + request.getEid() + "/" + request.getBody().getNewName())));
	}

	@Override
	public BaseResponse deleteFile(BaseRequest<PhotoRequest> request) {
		String url = config.getPara("PHOTO_PATH")+PHOTO_LIB_FOLDER_NAME;
		// String url=ConfigUtils.getValue("PHOTO_URL");
		if (request.getEid() == 0 || StringUtils.isEmpty(request.getBody().getName())
				|| StringUtils.isEmpty(request.getBody().getFileName()))
			return new CommObjResponse<Boolean>(false);
		File file = new File(url + "/"+USER_PHOTO_FOLDER_NAME+"/" + request.getEid() + "/" + request.getBody().getName() + "/"
				+ request.getBody().getFileName());
		return new CommObjResponse<Boolean>(file.delete());
	}

	@Override
	public BaseResponse getFiles(PageRequest<PhotoRequest> request) {
		Map<String, Object> content = new HashMap<String, Object>();
		String url = config.getPara("PHOTO_PATH")+PHOTO_LIB_FOLDER_NAME;
		// String url=ConfigUtils.getValue("PHOTO_URL");
		List<Object> listdata = new ArrayList<Object>();
		if (USER_PHOTO_FOLDER_NAME.equals(request.getBody().getType())) {
			File file = new File(url + "/"+USER_PHOTO_FOLDER_NAME+"/" + request.getEid() + "/" + request.getBody().getName());
			listdata = this.getChildRecursiveFileName(file, true);
		} else {
			File file = new File(url + "/" + request.getBody().getType() + "/" + request.getBody().getName());
			listdata = this.getChildRecursiveFileName(file, true);
		}
		if (listdata == null || "".equals(listdata)) {
			content.put("data", listdata);
			content.put("total", 0);
			return new CommObjResponse<Map<String, Object>>(content);
		}
		List<Object> list = new ArrayList<Object>();
		// 分页
		int begin = (request.getPageNum() - 1) * request.getPageSize();
		for (int i = 0; i < request.getPageSize(); i++) {
			if (i + begin < listdata.size()) {
				list.add(listdata.get(i + begin));
			}
		}
		content.put("data", list);
		content.put("total", listdata.size());
		return new CommObjResponse<Map<String, Object>>(content);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qisen.mts.common.service.inte.IPhotoService#processUpload(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public BaseResponse processUpload(MultipartFile file, UploadParam param) throws Exception {
		String ofn = file.getOriginalFilename();
		String fext = "." + (StringUtils.substringAfterLast(ofn, ".").length()>0?StringUtils.substringAfterLast(ofn, "."):"jpeg");
		// TODO 生成文件名
		File serverFile = new File(this.generateFile(param,fext));
		this.proceedWriteFiles(serverFile, file.getBytes(), param);
		return new CommObjResponse<String>(serverFile.getName());
	}
	
	private void proceedWriteFiles(File serverFile,byte[] bytes, UploadParam param)throws Exception{
		if(!serverFile.getParentFile().exists()){
			serverFile.getParentFile().mkdirs();
		}
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
		// 处理图片多分辨率
		if (!StringUtils.isEmpty(param.getVariations())) {
			List<ImageVariation> variations = JSON.parseArray(param.getVariations(), ImageVariation.class);
			Pattern dimension = Pattern.compile("(\\d+)[xX\\*](\\d+)");
			int[] rawDimension = null;
			BufferedImage bi = ImageIO.read(serverFile);
			if (bi != null) {
				rawDimension = new int[] { bi.getWidth(), bi.getHeight() };
			}
			for (ImageVariation variation : variations) {
				Matcher m = dimension.matcher(variation.getResolution());
				int[] targetDimension = null;
				int[] trimDimension = null;
				if (m.find() && (m.groupCount() == 2)) {
					targetDimension = new int[] { Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)) };
					if (Boolean.TRUE.equals(param.getTrim())) {
						BigDecimal targetWidth = new BigDecimal(targetDimension[0]);
						BigDecimal targetHeight = new BigDecimal(targetDimension[1]);

						BigDecimal rawWidth = new BigDecimal(rawDimension[0]);
						BigDecimal rawHeight = new BigDecimal(rawDimension[1]);

						if (rawWidth.compareTo(rawHeight) <= 0) {
							BigDecimal newHeight = rawWidth.multiply(targetHeight).divide(targetWidth, 10,
									BigDecimal.ROUND_HALF_UP);
							if (rawHeight.compareTo(newHeight) > 0) {
								rawHeight = newHeight;
							}
						} else {
							BigDecimal newWidth = rawHeight.multiply(targetWidth).divide(targetHeight, 10,
									BigDecimal.ROUND_HALF_UP);
							if (rawWidth.compareTo(newWidth) > 0) {
								rawWidth = newWidth;
							}
						}
						trimDimension = new int[] { rawWidth.intValue(), rawHeight.intValue() };
						Thumbnails.of(serverFile).sourceRegion(Positions.CENTER, trimDimension[0], trimDimension[1])
								.size(targetDimension[0], targetDimension[1])
								.toFile(makeThumbnailName(param, serverFile, variation.getSuffix()));
					} else {
						Thumbnails.of(serverFile).size(targetDimension[0], targetDimension[1])
								.toFile(makeThumbnailName(param, serverFile, variation.getSuffix()));
					}
				}
			}
		}
	}

	/**
	 * @param param
	 * @return
	 */
	private String generateFile(UploadParam param,String fext) {
		String path = config.getPara("PHOTO_PATH")
				+ (StringUtils.isEmpty(param.getDir()) ? File.separator : (param.getDir() + File.separator));
		if ("uuid".equals(param.getName())) {
			return path+UUID.randomUUID().toString()+fext;
		} else {
			return path+param.getName()+fext;
		}
	}

	private File makeThumbnailName(UploadParam param, File rawFile, String sizeSuffix) {
		String directory = rawFile.getParent()+File.separator;
		String rawName = rawFile.getName();
		int lastDot = rawName.lastIndexOf(".");
		if (lastDot >= 0) {
			String prefix = rawName.substring(0, lastDot);
			String suffix = rawName.substring(lastDot);
			return new File(directory + prefix + "_" + sizeSuffix + suffix);
		} else {
			return new File(directory + rawName + "_" + sizeSuffix+".jpeg");
		}
	}

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IPhotoService#processUploadBase64(com.qisen.mts.common.model.request.UploadParam)
	 */
	@Override
	public BaseResponse processUploadBase64(UploadParam param) throws Exception {
		String ofn = param.getRealName();
		String fext = "." + StringUtils.substringAfterLast(ofn, ".");
		// TODO 生成文件名
		File serverFile = new File(this.generateFile(param,fext));
		this.proceedWriteFiles(serverFile, Base64.getDecoder().decode(param.getBase64()), param);
		return new CommObjResponse<String>(serverFile.getName());
	}

}
