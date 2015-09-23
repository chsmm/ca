package com.ca.ticket;

import com.ca.subject.Subject;
import com.ca.ticket.support.ExpirationStrategy;

/**
 * 具体标识
 * @author ch
 *
 */
public class GenericTicket extends AbstractTicket{
	
	public GenericTicket(String id,Subject subject,ExpirationStrategy expirationPolicy) {
		super(id,expirationPolicy,subject);
	}

	
}
