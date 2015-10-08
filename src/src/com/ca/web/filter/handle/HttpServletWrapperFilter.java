package com.ca.web.filter.handle;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.ca.subject.Subject;
import com.ca.ticket.Ticket;
import com.ca.web.config.WebConfiguration;
/**
 * request 封装过滤器
 * @author ch
 *
 */
public class HttpServletWrapperFilter extends HandleFilter {
	
	public HttpServletWrapperFilter(WebConfiguration webConfiguration) {
		super(webConfiguration);
	}

	@Override
	protected void executeFilterChain(HttpServletRequest request,HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
		String ticketId= (String)request.getSession().getAttribute(Ticket.TICKET_ID);
		Ticket ticket = authenticationAuthorizeService.getTicket(ticketId);
		chain.doFilter(new CaHttpServletRequestWrapper(request,ticket.getSubject()), response);
	}
	
	final class CaHttpServletRequestWrapper extends HttpServletRequestWrapper{
		
		private Subject subject;
		
		public CaHttpServletRequestWrapper(HttpServletRequest request,Subject subject) {
			super(request);
			this.subject = subject;
		}

		@Override
		public Principal getUserPrincipal() {
			return subject.getPrincipal();
		}

		@Override
		public String getRemoteUser() {
			return subject.getPrincipal().getName();
		}
		
	}

}
