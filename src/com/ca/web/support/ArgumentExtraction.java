package com.ca.web.support;

import javax.servlet.http.HttpServletRequest;
/**
 * 参数提取器
 * @author ch
 *
 * @param <T>
 */
public interface ArgumentExtraction <T>{
	
	/**+
	 * 提取指定参数
	 * @param request
	 * @return
	 */
	T extract(HttpServletRequest request);

}
