package com.ca.ticket.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ca.ticket.Ticket;

/**
 * 基于 {@link ConcurrentHashMap} 实现  {@link TicketRegistry}接口的 默认标识注册类
 * @author ch
 *
 */
public class DefaultTicketRegistry implements TicketRegistry{
	
	private final Map<String,Ticket> cache;
	
	public DefaultTicketRegistry() {
		cache = new ConcurrentHashMap<String, Ticket>();
	}
	
	public DefaultTicketRegistry(final int initialCapacity) {
		cache = new ConcurrentHashMap<String, Ticket>(initialCapacity);
	}

	@Override
	public void addTicket(final Ticket ticket) {
		cache.put(ticket.getId(), ticket);
	}

	@Override
	public Ticket getTicket(final String ticketId) {
		if(ticketId==null){
			return null;
		}
		return cache.get(ticketId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Ticket> T getTicket(final String ticketId,final Class<? extends Ticket> clazz) {
		Ticket ticket = getTicket(ticketId);
		if (ticket == null) {
            return null;
        }
		if (!clazz.isAssignableFrom(ticket.getClass())) {
            throw new ClassCastException("Ticket [" + ticket.getId()
                + " is of type " + ticket.getClass()
                + " when we were expecting " + clazz);
        }
		
		return (T)ticket;
	}

	@Override
	public boolean deleteTicket(final String ticketId) {
		if(ticketId==null){
			return false;
		}
		return (cache.remove(ticketId) != null);
	}

	@Override
	public Collection<Ticket> getTickets() {
		return Collections.unmodifiableCollection(this.cache.values());
	}

}
