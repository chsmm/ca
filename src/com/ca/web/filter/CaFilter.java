package com.ca.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ca.web.filter.mtg.FilterChainManager;
import com.ca.web.util.WebUtil;


/**
 * 权限认证过滤器
 * 
 * @author ch
 * 
 */
public class CaFilter extends InitFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		String requestUrl = WebUtil.getRequestURL(request);
		try {
			FilterChain proxyChain = null;
			//获取过滤链管理器
			FilterChainManager filterChainManager = getFilterChainManager();
			// 获取与指定url进行关联的过滤器链
			for(String chainName : filterChainManager.getChainName()){
				if(chainName.equals(requestUrl)){
					proxyChain = filterChainManager.getProxyFilterChain(chain, chainName);
					if(proxyChain!=null){
						break;
					}
				}
			}
			//匹配不到 使用默认代理过滤器链
			if(proxyChain==null){
				proxyChain =  filterChainManager.getDefaultProxyFilterChain(chain);
			}
			proxyChain.doFilter(req, res);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
