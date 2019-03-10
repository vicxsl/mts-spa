package com.qisen.mts.common.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

/**
 * @author GuoJun
 * @date 2016年10月10日 下午3:07:09
 * @describe 文件类操作工具
 */
public class FileUtil {

	public static void quickSortByLastModifyTime(List<File> list) {
		subQuickSort(list, 0, list.size() - 1);
	}

	private static void subQuickSort(List<File> list, int start, int end) {
		if (list == null || (end - start + 1) < 2) {
			return;
		}
		int part = partition(list, start, end);
		if (part == start) {
			subQuickSort(list, part + 1, end);
		} else if (part == end) {
			subQuickSort(list, start, part - 1);
		} else {
			subQuickSort(list, start, part - 1);
			subQuickSort(list, part + 1, end);
		}
	}

	private static int partition(List<File> list, int start, int end) {
		long value = list.get(end).lastModified();
		int index = start - 1;
		for (int i = start; i < end; i++) {
			if (list.get(i).lastModified() < value) {
				index++;
				if (index != i) {
					exchangeElements(list, index, i);
				}
			}
		}
		if ((index + 1) != end) {
			exchangeElements(list, index + 1, end);
		}
		return index + 1;
	}

	public static void exchangeElements(List<File> list, int index1, int index2) {
		File temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	// 图片允许的后缀扩展名
	public static String[] IMAGE_FILE_EXTD = new String[] { "png", "bmp", "jpg", "jpeg", "pdf" };

	public static boolean isFileAllowed(String fileName) {
		for (String ext : IMAGE_FILE_EXTD) {
			if (ext.equals(fileName)) {
				return true;
			}
		}
		return false;
	}
	public static MultipartFile base64ToMultipart(String base64) {
	    try {
	        String[] baseStrs = base64.split(",");

	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = new byte[0];
	        b = decoder.decodeBuffer(baseStrs[1]);

	        for(int i = 0; i < b.length; ++i) {
	            if (b[i] < 0) {
	                b[i] += 256;
	            }
	        }
	        return new BASE64DecodedMultipartFile(b, baseStrs[0]);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
