package com.ca.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 凭证  提供用户登录信息
 * @author ch
 *
 */
public interface Credential {
	/**
	 * 凭证名称
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 请求信息
	 * 
	 * @return
	 */
	HttpServletRequest getRequest();

	/**
	 * 返回信息
	 * 
	 * @return
	 */
	HttpServletResponse getResponse();

	/**
	 * session
	 * 
	 * @return
	 */
	HttpSession getSession();

}
