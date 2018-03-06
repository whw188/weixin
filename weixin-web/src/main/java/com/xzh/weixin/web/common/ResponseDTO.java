/**
 * 
 */
package com.xzh.weixin.web.common;

import java.io.Serializable;

/**
 * @author wangwei18
 * 
 *         2014年7月2日 下午12:04:32
 */
public class ResponseDTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code = ReturnCode.ACTIVE_FAILURE.code();
	private String msg = ReturnCode.ACTIVE_FAILURE.msg();
	private T attach;

	public ResponseDTO(ReturnCode returnCode) {
		this.code = returnCode.code();
		this.msg = returnCode.msg();
	}

	public ResponseDTO() {

	}

	public ResponseDTO(int code) {
		this.code = code;
	}

 

	public void setReturnCode(ReturnCode returnCode) {
		this.code = returnCode.code();
		this.msg = returnCode.msg();
	}

	public ReturnCode nowReturnCode() {// 此处不能写getxx,会被spring 识别然后返回出去
		return ReturnCode.codeToEnum(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getAttach() {
		return attach;
	}

	public void setAttach(T attach) {
		this.attach = attach;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
