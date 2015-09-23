package com.ca.web.util;

import javax.servlet.http.HttpServletRequest;
/**
 * web工具类
 * @author ch
 *
 */
public final class WebUtil {
	private WebUtil(){}
	
	
	/**
	 * 获取访问服务器名称
	 * @param request
	 * @return
	 */
	public static String getServerName(HttpServletRequest request){
		return (request.getScheme() + "://" + request.getServerName() + ":"+ request.getServerPort() + request.getContextPath() + "/");
	}
	/**
	 * 获取请求url
	 * @param request
	 * @return
	 */
	public static String getRequestURL(HttpServletRequest request){
		return request.getRequestURL().substring(WebUtil.getServerName(request).length()).toString();
	}
}
