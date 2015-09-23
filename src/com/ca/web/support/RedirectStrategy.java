package com.ca.web.support;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 跳转策略
 * @author ch
 *
 */
public interface RedirectStrategy {
	
	/**
	 * 跳转
	 * @param request
	 * @param response
	 * @param redirectUrl
	 * @throws IOException
	 */
	void redirect(HttpServletRequest request, HttpServletResponse response, String redirectUrl) throws IOException;

}
