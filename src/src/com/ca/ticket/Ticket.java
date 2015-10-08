package com.ca.ticket;

import com.ca.subject.Subject;


/**
 * 用户标识
 * @author ch
 *
 */
public interface Ticket {
	
	String TICKET_ID = "ticketId";
	
	/**
	 * 编号
	 * @return
	 */
	String getId();
	
	/**
	 * 主题
	 * @return
	 */
	Subject getSubject();
	
	/**
	 * 是否过期
	 * @return
	 */
	boolean isExpired();
	
}
