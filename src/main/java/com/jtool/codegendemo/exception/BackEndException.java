package com.jtool.codegendemo.exception;

import com.jtool.codegenannotation.CodeGenExceptionDefine;

@CodeGenExceptionDefine(code="-98", desc="后端错误")
public class BackEndException extends Exception {
	private static final long serialVersionUID = 6255503754366082895L;
}
