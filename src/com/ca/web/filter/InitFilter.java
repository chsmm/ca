package com.ca.web.filter;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import com.ca.core.AuthenticationAuthorizeService;
import com.ca.core.impl.AuthenticationAuthorizeServiceImpl;
import com.ca.web.config.AuthenticationConfig;
import com.ca.web.config.DefaultConfiguration;
import com.ca.web.filter.mtg.DefaultFilterChainManager;
import com.ca.web.filter.mtg.FilterChainManager;

/**
 * 初始化过滤器
 * @author ch
 *
 */
public abstract class InitFilter implements Filter{
	
	protected FilterChainManager filterChainManager;
	
	

	public FilterChainManager getFilterChainManager() {
		return filterChainManager;
	}


	public void setFilterChainManager(FilterChainManager filterChainManager) {
		this.filterChainManager = filterChainManager;
	}


	@Override
	public final void init(FilterConfig config) throws ServletException {
		try {
			
			final AuthenticationConfig authenticationConfig = new AuthenticationConfig(config.getInitParameter(AuthenticationConfig.NAME));
			final DefaultConfiguration configuration = new DefaultConfiguration(authenticationConfig);
			final AuthenticationAuthorizeService authenticationAuthorizeService =  new AuthenticationAuthorizeServiceImpl(configuration);
			final DefaultFilterChainManager filterChainManager = new DefaultFilterChainManager(configuration,authenticationAuthorizeService);
			
			filterChainManager.setDefaultFilterChainList(Arrays.asList(
								filterChainManager.getFilter(DefaultFilter.checkLogin.name()),
								filterChainManager.getFilter(DefaultFilter.wrapper.name()),
								filterChainManager.getFilter(DefaultFilter.Ignore.name()),
								filterChainManager.getFilter(DefaultFilter.auth.name())
							));
			filterChainManager.createChain(configuration.getLoginUrl(), new ArrayList<Filter>(Arrays.asList(filterChainManager.getFilter(DefaultFilter.login.name()))));
			
			filterChainManager.createChain(configuration.getLogoutUrl(), new ArrayList<Filter>(Arrays.asList(filterChainManager.getFilter(DefaultFilter.checkLogin.name()),filterChainManager.getFilter(DefaultFilter.logout.name()))));
			setFilterChainManager(filterChainManager);
		} catch (Exception e) {
			throw new ServletException("权限过滤器初始化失败",e);
		}
	}
	
	
	@Override
	public void destroy() {}
	

}
