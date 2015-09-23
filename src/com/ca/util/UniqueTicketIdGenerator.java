package com.ca.util;
/**
 * 标识编号生成器
 * @author ch
 *
 */
public interface UniqueTicketIdGenerator {
	/**
	 * 生成新标识
	 * @param prefix 标识前缀
	 * @return
	 */
	String getNewTicketId(String prefix);

}
