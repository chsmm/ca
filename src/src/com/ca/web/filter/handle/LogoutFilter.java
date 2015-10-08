package com.ca.web.filter.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ca.ticket.Ticket;
import com.ca.web.config.WebConfiguration;
import com.ca.web.util.WebUtil;
/**
 * 退出处理
 * @author ch
 *
 */
public class LogoutFilter extends HandleFilter{
	
	
	
	public LogoutFilter(WebConfiguration webConfiguration) {
		super(webConfiguration);
	}


	

	@Override
	protected boolean preHandle(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String ticketId = (String)session.getAttribute(Ticket.TICKET_ID);
		session.removeAttribute(Ticket.TICKET_ID);
		webConfiguration.getCookieGenerator().removeCookie(response);
		authenticationAuthorizeService.destroyTicket(ticketId);
		webConfiguration.getRedirectStrategy().redirect(request, response, WebUtil.getServerName(request)+webConfiguration.getLoginUrl());
		return true;
	}


	

}
