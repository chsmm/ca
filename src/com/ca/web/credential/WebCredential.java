package com.ca.web.credential;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ca.core.Credential;
/**
 * 具体凭证
 * @author ch
 *
 */
public class WebCredential implements Credential{
	
	private HttpServletRequest request;
	
	private  HttpServletResponse response;
	
	public WebCredential(HttpServletRequest request , HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public String getName() {
		return "webCredential";
	}

	@Override
	public HttpServletRequest getRequest() {
		return request;
	}

	@Override
	public HttpServletResponse getResponse() {
		return response;
	}

	@Override
	public HttpSession getSession() {
		return request.getSession();
	}

}
