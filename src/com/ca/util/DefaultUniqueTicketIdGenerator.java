package com.ca.util;

import java.util.UUID;
/**
 * 基于 {@link UUID} 实现  {@link UniqueTicketIdGenerator}接口的 默认标识生成类
 * 默认标识编号生成器
 * @author ch
 *
 */
public class DefaultUniqueTicketIdGenerator implements UniqueTicketIdGenerator{

	@Override
	public String getNewTicketId(String prefix) {
		StringBuilder buffer = new StringBuilder(prefix.length()+32);
    	buffer.append(prefix);
        buffer.append(UUID.randomUUID().toString().replaceAll("-", ""));
		return buffer.toString();
	}
}
