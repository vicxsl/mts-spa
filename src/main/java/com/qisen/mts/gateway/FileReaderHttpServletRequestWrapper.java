package com.qisen.mts.gateway;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private String encoding = "utf-8";
	// 文件头类型
	private static final String MULTIPART_HEADER = "Content-type";
	// 是否是上传文件
	private boolean multipart;
	// 保存提交的数据
	private Map<String, Object> params = new HashMap<String, Object>();

	public FileReaderHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		// 判断是否是上传文件
		multipart = request.getHeader(MULTIPART_HEADER) != null
				&& request.getHeader(MULTIPART_HEADER).startsWith("multipart/form-data");
		// 如果是上传文件
		if (multipart) {
			try {
				ServletFileUpload upload = new ServletFileUpload();
				// 使用apache进行上传
				// 设置编码
				upload.setHeaderEncoding(encoding);
				// 解析上传的数据
				List<FileItem> fileItems = upload.parseRequest(request);
				// 遍历
				for (Iterator<FileItem> it = fileItems.iterator(); it.hasNext();) {
					// 获取当前的FileItem
					FileItem item = (FileItem) it.next();

					// 如果是文本域
					if (item.isFormField()) {
						params.put(item.getFieldName(), item.getString(encoding));
					} else {
						// 替换特殊字符
						String filename = item.getName().replace("\\", "/");
						filename = filename.substring(filename.lastIndexOf("/") + 1);

						// 保存到系统临时文件夹中
						File file = new File(System.getProperty("java.io.tmpdir"), filename);

						// 新建文件输出流
						OutputStream ops = new FileOutputStream(file);
						// 输出到文件流中
						ops.write(item.get());
						// 关闭文件流
						ops.close();

						// 将值放到Map中
						params.put(item.getFieldName(), file);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object getAttribute(String name) {
		// 如果是上传文件，则从Map中取值，支持直接获取文件对象
		if (multipart && params.containsKey(name)) {
			return params.get(name);
		}
		return super.getAttribute(name);
	}

	@Override
	public String getParameter(String name) {
		// 如果是上传文件，则从Map中取值，
		if (multipart && params.containsKey(name)) {
			return params.get(name).toString();
		}
		return super.getParameter(name);
	}

}
