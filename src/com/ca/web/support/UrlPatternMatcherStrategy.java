package com.ca.web.support;
/**
 * url匹配模式
 * @author ch
 *
 */
public interface UrlPatternMatcherStrategy {
	/**
	 * 匹配
	 * @param url
	 * @return
	 */
	boolean matching(String url);
	
	/**
	 * 模式
	 * @param pattern
	 */
	void setPattern(String pattern);

}
