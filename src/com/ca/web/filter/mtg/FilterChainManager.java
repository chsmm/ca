package com.ca.web.filter.mtg;

import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
/**
 * 过滤器链管理
 * @author ch
 *
 */
public interface FilterChainManager {
	
	/**
	 * 创建链
	 * @param chainName
	 * @param filters
	 */
	void createChain(String chainName,List<Filter> filters);
	
	/**
	 * 获取所有链名称
	 * @return
	 */
	Set<String> getChainName();
	
	/**
	 * 获取代理链
	 * @param original
	 * @param chainName
	 * @return
	 */
	FilterChain getProxyFilterChain(FilterChain original, String chainName);
	
	/**
	 * 默认代理链
	 * @param chain
	 * @return
	 */
	FilterChain getDefaultProxyFilterChain(FilterChain chain);
	
	/**
	 * 获取指定名称的过滤器
	 * @param filterName
	 * @return
	 */
	Filter getFilter(String filterName);
	
	

}
