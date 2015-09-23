package com.ca.web.filter.mtg;

import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import com.ca.web.filter.handle.ProxyFilterChain;
/**
 * 默认过滤器链
 * @author ch
 *
 */
public class DefaultFilterChainList implements FilterChainList{
	
	private List<Filter> filters;
	
	public DefaultFilterChainList(List<Filter> filters) {
		this.filters = filters;
	}

	@Override
	public FilterChain proxy(FilterChain chain) {
		return new ProxyFilterChain(chain, filters);
	}

}
