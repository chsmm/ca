package com.ca.web.filter.handle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ca.core.AuthenticationAuthorizeService;
import com.ca.core.AuthenticationAuthorizeServiceConfig;
import com.ca.web.config.WebConfiguration;
/**
 * 处理过滤器
 * @author ch
 *
 */
public class HandleFilter implements Filter, AuthenticationAuthorizeServiceConfig{

	
	protected WebConfiguration webConfiguration;
	
	protected  AuthenticationAuthorizeService authenticationAuthorizeService ;
	
	public HandleFilter(WebConfiguration webConfiguration) {
		this.webConfiguration = webConfiguration;
	}
	/**
	 * 前处理
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return true;
	}
	
	/**
	 * 执行链
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void executeFilterChain(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException, ServletException{
		chain.doFilter(request, response);
	}
	
	/**
	 * 后处理
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void postHandle(HttpServletRequest request, HttpServletResponse response)throws Exception{}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse)res;
		try{
			boolean  executeFilterChain = preHandle(request,response);
			if(executeFilterChain){
				executeFilterChain(request,response,chain);
			}
			postHandle( request, response);
		}catch(IOException e){
			throw e;
		}catch(Exception e){
			throw new ServletException(e);
		}
	
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}
	
	@Override
	public void destroy() {}
	
	
	@Override
	public void setAuthenticationAuthorizeService(AuthenticationAuthorizeService authenticationAuthorizeService) {
		this.authenticationAuthorizeService = authenticationAuthorizeService;
	}

}
