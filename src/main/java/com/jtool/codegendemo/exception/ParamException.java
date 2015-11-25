package com.jtool.codegendemo.exception;

import com.jtool.codegenannotation.CodeGenExceptionDefine;

@CodeGenExceptionDefine(code="-3", desc="参数错误")
public class ParamException extends Exception {
	private static final long serialVersionUID = 1L;
}
