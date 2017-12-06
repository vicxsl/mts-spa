package com.qisen.mts.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qisen.mts.admin.service.MemberImportService;
import com.qisen.mts.common.model.response.BaseResponse;

@Controller
@RequestMapping("/admin/import")
public class MemberImportController {
	@Autowired
	private MemberImportService importService;
	
	@ResponseBody
	@RequestMapping("/memberImport")
	public BaseResponse memberImport(MultipartFile file, Integer eid, Integer sid)throws Exception{
		return importService.memberImport(file, eid, sid);
	}
	@ResponseBody
	@RequestMapping("/treatImport")
	public BaseResponse treatImport(MultipartFile file, Integer eid, Integer sid)throws Exception{
		return importService.treatImport(file, eid, sid);
	}
}
