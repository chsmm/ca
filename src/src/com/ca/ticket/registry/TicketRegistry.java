package com.ca.ticket.registry;

import java.util.Collection;

import com.ca.ticket.Ticket;

/**
 * 标识注册接口
 * @author ch
 *
 */
public interface TicketRegistry {
	/**
	 * 添加标识
	 * @param ticket
	 */
	void addTicket(Ticket ticket);
	
	/**
	 * 获取标识
	 * @param ticketId 标识编号
	 * @return
	 */
	Ticket getTicket(String ticketId);
	
	/**
	 * 获取具体类型的表示
	 * @param ticketId 标识编号
	 * @param clazz 类型
	 * @return
	 */
	<T extends Ticket> T getTicket(String ticketId,Class<? extends Ticket> clazz);
	
	/**
	 * 删除标识
	 * @param ticketId 标识编号
	 * @return
	 */
	boolean deleteTicket(String ticketId);
	
	/**
	 * 获取所有标识
	 * @return
	 */
	Collection<Ticket> getTickets();

}
