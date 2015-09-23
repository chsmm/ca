package com.ca.ticket.support;

import com.ca.ticket.TicketState;


/**
 * 标识超时策略
 * @author ch
 *
 */
public interface ExpirationStrategy {
	
	/**
	 * 是否过期
	 * @param state
	 * @return
	 */
	boolean isExpired(TicketState state);
}
