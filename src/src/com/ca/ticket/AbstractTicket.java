package com.ca.ticket;

import com.ca.subject.Subject;
import com.ca.ticket.support.ExpirationStrategy;


/**
 * 实现标识 与标识状态接口
 * @author ch
 *
 */
public abstract class AbstractTicket implements Ticket, TicketState {

	private String id;

	private long creationTime;

	private long lastTimeUsed;
	
	private Subject subject;

	private ExpirationStrategy expirationPolicy;

	public AbstractTicket() {
	}

	public AbstractTicket(String id, ExpirationStrategy expirationPolicy,Subject subject) {
		this.creationTime = System.currentTimeMillis();
		this.lastTimeUsed = System.currentTimeMillis();
		this.id = id;
		this.expirationPolicy = expirationPolicy;
		this.subject = subject;
	}
	

	@Override
	public String getId() {

		return id;
	}

	@Override
	public long getCreationTime() {

		return creationTime;
	}

	@Override
	public long getLastTimeUsed() {

		return lastTimeUsed;
	}

	@Override
	public boolean isExpired() {
		return expirationPolicy == null ? isExpiredInternal()
				: expirationPolicy.isExpired(this);
	}
	

	@Override
	public Subject getSubject() {
		return subject;
	}

	protected boolean isExpiredInternal() {
		return false;
	}

	public final int hashCode() {
		return this.getId().hashCode();
	}

	public final String toString() {
		return this.getId();
	}

}
