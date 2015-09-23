package com.ca.web.filter.handle;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 代理链
 * @author ch
 *
 */
public class ProxyFilterChain implements FilterChain{
	/**
	 * 原始链
	 */
	private FilterChain originalChain;
	/**
	 * 过滤器
	 */
	private List<Filter> filters;
	/**
	 * 下标
	 */
	private int index;
	
	
	

	public  ProxyFilterChain(FilterChain originalChain, List<Filter> filters) {
		this.originalChain = originalChain;
		this.filters = filters;
		this.index = 0;
	}
	
	/**
	 * 执行原始链
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void executeOriginalChain(ServletRequest request, ServletResponse response) throws IOException, ServletException{
		originalChain.doFilter(request, response);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		if (filters == null || filters.size() == index) {
			executeOriginalChain(request,response);
		} else {
			filters.get(index++).doFilter(request, response, this);
		}
	}

}
