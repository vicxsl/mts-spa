package com.qisen.mts.admin.service;

import org.springframework.web.multipart.MultipartFile;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.gateway.ExceptionWithCode;

public interface MemberImportService {
	
	public BaseResponse memberImport(MultipartFile file, Integer eid, Integer sid) throws ExceptionWithCode;
	
	public BaseResponse treatImport(MultipartFile file,Integer eid,Integer sid);

}
