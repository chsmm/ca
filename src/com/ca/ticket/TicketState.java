package com.ca.ticket;
/**
 * 标识状态
 * @author ch
 *
 */
public interface TicketState {
	/**
	 * 创建时间
	 * @return
	 */
	long getCreationTime();
	
	/**
	 * 最后访问时间
	 * @return
	 */
	long getLastTimeUsed();

}
