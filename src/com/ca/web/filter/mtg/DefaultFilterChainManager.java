package com.ca.web.filter.mtg;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;

import com.ca.core.AuthenticationAuthorizeService;
import com.ca.core.AuthenticationAuthorizeServiceConfig;
import com.ca.web.config.WebConfiguration;
import com.ca.web.filter.DefaultFilter;
/**
 * 默认过滤器链管理器
 * @author Administrator
 *
 */
public class DefaultFilterChainManager implements FilterChainManager{
	
	/**
	 * 过滤器映射
	 */
	private Map<String, Filter> filters;
	/**
	 * 过滤器链映射
	 */
	private Map<String, FilterChainList> filterChains;
	
	/**
	 * 默认链
	 */
	private DefaultFilterChainList defaultFilterChainList;
	
	public DefaultFilterChainManager() {
		filterChains = new LinkedHashMap<String, FilterChainList>();
		
	}
	
	public DefaultFilterChainManager(List<Filter> filters) {
		filterChains = new LinkedHashMap<String, FilterChainList>();
	}
	
	public DefaultFilterChainManager(WebConfiguration webConfiguration,AuthenticationAuthorizeService authenticationAuthorizeService) {
		filterChains = new LinkedHashMap<String, FilterChainList>();
		filters = new LinkedHashMap<String, Filter>();
		addDefaultFilter(webConfiguration,authenticationAuthorizeService);
	}

	@Override
	public void createChain(String chainName, List<Filter> filters) {
		filterChains.put(chainName,new DefaultFilterChainList(filters));
	}


	@Override
	public Set<String> getChainName() {
		return filterChains.keySet();
	}

	@Override
	public FilterChain getProxyFilterChain(FilterChain original,String chainName) {
		return  filterChains.get(chainName).proxy(original);
	}

	@Override
	public FilterChain getDefaultProxyFilterChain(FilterChain chain) {
		return defaultFilterChainList.proxy(chain);
	}
	
	@Override
	public Filter getFilter(String filterName) {
		return filters.get(filterName);
	}

	public void setDefaultFilterChainList(List<Filter> filters) {
		if(this.defaultFilterChainList==null){
			this.defaultFilterChainList =  new DefaultFilterChainList(filters);
		}
	}
	
	/**
	 * 使用 webConfiguration authenticationAuthorizeService 实例化过滤器
	 * @param webConfiguration
	 * @param authenticationAuthorizeService
	 */
	private void addDefaultFilter(WebConfiguration webConfiguration,AuthenticationAuthorizeService authenticationAuthorizeService){
		for(DefaultFilter defaultFilter : DefaultFilter.values()){
			Filter filter= defaultFilter.getFilter(webConfiguration);
			if(filter instanceof AuthenticationAuthorizeServiceConfig){
				((AuthenticationAuthorizeServiceConfig)filter).setAuthenticationAuthorizeService(authenticationAuthorizeService);
			}
			filters.put(defaultFilter.name(), filter);
		}
	}

	

}
