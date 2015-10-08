package com.ca.ticket.support;

import com.ca.ticket.TicketState;

/**
 * 具体标识超时策略
 * @author ch
 *
 */
public class TicketExpirationStrategy implements ExpirationStrategy {
	/**
	 * 默认的超时时间
	 */
	private static final int default_timeOut = 30;
	
	private int timeOut;

	public TicketExpirationStrategy() {
		this(default_timeOut);

	}

	public TicketExpirationStrategy(int timeOut) {
		this.timeOut = timeOut * 60000;
	}

	@Override
	public boolean isExpired(TicketState state) {
		return (state == null) || (state.getLastTimeUsed() - System.currentTimeMillis()>timeOut);
	}

}
