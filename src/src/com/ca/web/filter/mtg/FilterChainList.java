package com.ca.web.filter.mtg;

import javax.servlet.FilterChain;
/**
 * 过滤器链列表
 * 用于保存每个过滤器链对应的链列表
 * @author ch
 *
 */
public interface FilterChainList {
	
	/**
	 * 创建代理链
	 * @param chain
	 * @return
	 */
	FilterChain proxy(FilterChain chain);

}
