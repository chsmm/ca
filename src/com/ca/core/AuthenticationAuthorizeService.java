package com.ca.core;

import com.ca.core.handler.AuthenticationAuthorizeException;
import com.ca.ticket.Ticket;



/**
 * 认证授权服务
 * 
 * @author ch
 *
 */
public interface AuthenticationAuthorizeService {
	
	/**
	 * 认证 与 授权
	 * @param credential
	 * @return
	 */
	Ticket createTicket(Credential credential)  throws AuthenticationAuthorizeException;
	
	
	/**
	 * 验证权限
	 * @param ticketId
	 * @param permission
	 * @return
	 */
	Ticket getTicket(String ticketId);
	
	/**
	 * 删除ticket
	 * @param ticketId
	 */
	void destroyTicket(String ticketId);

}
