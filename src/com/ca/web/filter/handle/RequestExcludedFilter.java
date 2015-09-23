package com.ca.web.filter.handle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ca.web.config.WebConfiguration;
import com.ca.web.util.WebUtil;

/**
 * 不拦截请求处理过滤器
 * @author ch
 *
 */
public class RequestExcludedFilter implements Filter{
	
	
	private WebConfiguration webConfiguration;
	
	public RequestExcludedFilter(WebConfiguration webConfiguration) {
		this.webConfiguration = webConfiguration;
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String requestUrl = WebUtil.getRequestURL((HttpServletRequest)request);
		if (requestUrl.equals(webConfiguration.getSuccessUrl()) || requestUrl.equals(webConfiguration.getAuthorizationFailureUrl())) {
			((ProxyFilterChain) chain).executeOriginalChain(request, response);
			return;
		}
		if (webConfiguration.getIgnoreUrlPatternStrategy() != null && webConfiguration.getIgnoreUrlPatternStrategy().matching(requestUrl)) {
			((ProxyFilterChain) chain).executeOriginalChain(request, response);
			return;
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void destroy() {}

}
