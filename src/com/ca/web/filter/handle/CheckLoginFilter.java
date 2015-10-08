package com.ca.web.filter.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ca.ticket.Ticket;
import com.ca.web.config.WebConfiguration;
import com.ca.web.util.CookieGenerator;
import com.ca.web.util.WebUtil;
/**
 * 登录检查过滤器 确认用户是否登录
 * @author ch
 *
 */
public class CheckLoginFilter extends HandleFilter{


	
	public CheckLoginFilter(WebConfiguration webConfiguration) {
		super(webConfiguration);
	}



	@Override
	protected boolean preHandle(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		String ticketId = null;
		boolean noSession = (session == null);
		CookieGenerator cookieGenerator = webConfiguration.getCookieGenerator();
		if (!noSession) {
			ticketId = (String) session.getAttribute(Ticket.TICKET_ID);
		}
		if (ticketId == null) {
			ticketId = cookieGenerator.getCookieValue(request);
		}
		boolean noTicketId = (ticketId == null);
		/**
		 * 未认证
		 */
		if (noTicketId) {
			webConfiguration.getRedirectStrategy().redirect(request, response, WebUtil.getServerName(request)+webConfiguration.getLoginUrl());
			return false;
		}
		/**
		 * 标识过期
		 */
		if (noSession && !noTicketId) {
			cookieGenerator.removeCookie(response);
			authenticationAuthorizeService.destroyTicket(ticketId);
			webConfiguration.getRedirectStrategy().redirect(request, response,WebUtil.getServerName(request)+webConfiguration.getLoginUrl());
			return false;
		}
		return true;
	}




	
	
	
	

}
