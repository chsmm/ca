package com.ca.core.impl;

import java.util.List;

import com.ca.core.AuthConfiguration;
import com.ca.core.AuthenticationAuthorizeService;
import com.ca.core.Credential;
import com.ca.core.handler.AuthenticationAuthorizeException;
import com.ca.core.handler.AuthenticationAuthorizeHandle;
import com.ca.subject.Permission;
import com.ca.subject.Principal;
import com.ca.subject.Subject;
import com.ca.subject.support.PermissionMatcherStrategy;
import com.ca.ticket.GenericTicket;
import com.ca.ticket.Ticket;
import com.ca.ticket.registry.TicketRegistry;
import com.ca.ticket.support.ExpirationStrategy;
import com.ca.util.UniqueTicketIdGenerator;

/**
 * 认证服务实现
 * 
 * @author ch
 *
 */
public class AuthenticationAuthorizeServiceImpl implements AuthenticationAuthorizeService {
	private AuthenticationAuthorizeHandle handle;
	private TicketRegistry ticketRegistry;
	private ExpirationStrategy expirationStrategy;
	private UniqueTicketIdGenerator uniqueTicketIdGenerator;
	private PermissionMatcherStrategy permissionMatcherStrategy;
	
	public AuthenticationAuthorizeServiceImpl() {}
	/**
	 * 使用 {@link AuthConfiguration} 提供的配置初始化
	 * @param authConfiguration
	 */
	public AuthenticationAuthorizeServiceImpl(AuthConfiguration authConfiguration){
		this.handle = authConfiguration.getAuthorizeHandle();
		this.ticketRegistry = authConfiguration.getTicketRegistry();
		this.uniqueTicketIdGenerator = authConfiguration.getUniqueTicketIdGenerator();
		this.expirationStrategy = authConfiguration.getExpirationStrategy();
		this.permissionMatcherStrategy = authConfiguration.getPermissionMatcherStrategy();
	}
	
	/**
	 * 使用具体配置
	 * @param handle
	 * @param ticketRegistry
	 * @param uniqueTicketIdGenerator
	 * @param expirationStrategy
	 * @param permissionMatcherStrategy
	 */
	public AuthenticationAuthorizeServiceImpl(AuthenticationAuthorizeHandle handle, TicketRegistry ticketRegistry,UniqueTicketIdGenerator uniqueTicketIdGenerator,
			ExpirationStrategy expirationStrategy,
			PermissionMatcherStrategy permissionMatcherStrategy) {
		this.handle = handle;
		this.ticketRegistry = ticketRegistry;
		this.uniqueTicketIdGenerator = uniqueTicketIdGenerator;
		this.expirationStrategy = expirationStrategy;
		this.permissionMatcherStrategy = permissionMatcherStrategy;
	}
	

	
	



	public AuthenticationAuthorizeHandle getHandle() {
		return handle;
	}

	public void setHandle(AuthenticationAuthorizeHandle handle) {
		this.handle = handle;
	}

	public TicketRegistry getTicketRegistry() {
		return ticketRegistry;
	}



	public void setTicketRegistry(TicketRegistry ticketRegistry) {
		this.ticketRegistry = ticketRegistry;
	}

	public ExpirationStrategy getExpirationStrategy() {
		return expirationStrategy;
	}

	public void setExpirationStrategy(ExpirationStrategy expirationStrategy) {
		this.expirationStrategy = expirationStrategy;
	}

	public UniqueTicketIdGenerator getUniqueTicketIdGenerator() {
		return uniqueTicketIdGenerator;
	}



	public void setUniqueTicketIdGenerator(
			UniqueTicketIdGenerator uniqueTicketIdGenerator) {
		this.uniqueTicketIdGenerator = uniqueTicketIdGenerator;
	}



	public PermissionMatcherStrategy getPermissionMatcherStrategy() {
		return permissionMatcherStrategy;
	}



	public void setPermissionMatcherStrategy(PermissionMatcherStrategy permissionMatcherStrategy) {
		this.permissionMatcherStrategy = permissionMatcherStrategy;
	}



	@Override
	public Ticket createTicket(Credential credential) throws AuthenticationAuthorizeException {
		Principal principal = handle.authentication(credential);
		List<Permission> permissions= handle.authorize(credential,principal);
		
		Ticket ticket = new GenericTicket(uniqueTicketIdGenerator.getNewTicketId(Ticket.TICKET_ID), createSubject(principal,permissions) ,expirationStrategy);
		ticketRegistry.addTicket(ticket);
		return ticket;
	}

	@Override
	public Ticket getTicket(String ticketId) {
		final Ticket ticket = ticketRegistry.getTicket(ticketId);
		if (ticket != null) {
			synchronized (ticket) {
				if (ticket.isExpired()) {
					destroyTicket(ticketId);
					return null;
				}
			}
		}
		return ticket;
	}
	
	private Subject createSubject(Principal principal,List<Permission> permissions){
		return new Subject(principal,permissions,permissionMatcherStrategy);
	}

	@Override
	public void destroyTicket(final String ticketId) {
		
		ticketRegistry.deleteTicket(ticketId);
	}
	

}
