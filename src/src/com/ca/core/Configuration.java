package com.ca.core;

import com.ca.util.UniqueTicketIdGenerator;
/**
 * 配置接口
 * @author ch
 *
 */
public interface Configuration {
	/**
	 * 标识生成器
	 * @return
	 */
	UniqueTicketIdGenerator getUniqueTicketIdGenerator() ;
	
	
}
