package com.jtool.codegendemo.controller;

import com.jtool.codegenannotation.CodeGenExceptionDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.UUID;

@ControllerAdvice
public class AppControllerAdvice {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler
	public void processException(HttpServletResponse response, Exception e) throws IOException {
		try (Writer writer = response.getWriter()) {

			CodeGenExceptionDefine codeGenExceptionDefine = e.getClass().getAnnotation(CodeGenExceptionDefine.class);

			if (codeGenExceptionDefine != null) {
				String responseString = "{\"code\":\"" + codeGenExceptionDefine.code() + "\"}";
				writer.write(responseString);
			} else {
				String eid = UUID.randomUUID().toString();
				log.error("未知错误-99\teid:" + eid, e);
				writer.write("{\"code\":\"-99\", \"eid\":\"" + eid + "\"}");
			}
		}
	}

}