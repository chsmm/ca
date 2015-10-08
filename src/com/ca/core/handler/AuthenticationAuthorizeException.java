package com.ca.core.handler;


/**
 * 认证与授权异常
 * 提供认证授权错误信息提示
 * @author ch
 *
 */
public class AuthenticationAuthorizeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9068588249216230339L;

	public AuthenticationAuthorizeException(String msg,Throwable throwable) {
		super(msg,throwable);
	}
	
	public AuthenticationAuthorizeException(String msg) {
		super(msg);
	}

}
