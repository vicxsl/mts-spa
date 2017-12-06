package com.qisen.mts.gateway;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.response.BaseResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = ExceptionWithCode.class)
	public @ResponseBody BaseResponse handleMtsException(HttpServletRequest req, ExceptionWithCode e) {
		BaseResponse resp = new BaseResponse();
		if (e.getMsgCode() != null) {
			resp.setCode(e.getMsgCode());
		} else {
			resp.setResult(ResultCode.FAILED);
		}
		return resp;
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public @ResponseBody BaseResponse handleRuntimeException(HttpServletRequest req, MethodArgumentNotValidException e) {
		BaseResponse resp = new BaseResponse();
		List<FieldError> errors = e.getBindingResult().getFieldErrors();
		String msg = "";
		for (FieldError error : errors) {
			msg += error.getField() + " " + error.getDefaultMessage() + ";";
		}
		logger.error(msg, e);
		resp.setResult(ResultCode.INVALID_PARAMETERS);
		return resp;
	}

	@ExceptionHandler(value = Exception.class)
	public @ResponseBody BaseResponse handleRuntimeException(HttpServletRequest req, Exception e) {
		BaseResponse resp = new BaseResponse();
		logger.error("error occured", e);
		resp.setResult(ResultCode.FAILED);
		return resp;
	}

}
