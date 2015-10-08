package com.ca.web.filter.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ca.core.handler.AuthenticationAuthorizeException;
import com.ca.ticket.Ticket;
import com.ca.web.config.WebConfiguration;
import com.ca.web.credential.WebCredential;
import com.ca.web.util.WebUtil;
/**
 * 登录处理
 * @author ch
 *
 */
public class LoginFilter extends HandleFilter{
	/**
	 * 登录标识
	 */
	public static final String LOGIN_TICKET = "loginTicket";
	
	


	public LoginFilter(WebConfiguration webConfiguration) {
		super(webConfiguration);
	}
	
	@Override
	protected boolean preHandle(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		boolean noSession = (session == null);
		String ticketId = !noSession ? (String)session.getAttribute(Ticket.TICKET_ID):null;
		if(ticketId!=null){
			webConfiguration.getRedirectStrategy().redirect(request, response, WebUtil.getServerName(request)+webConfiguration.getSuccessUrl());
			return false;
		}
		String loginTicket = !noSession ? (String)session.getAttribute(LOGIN_TICKET):null;
		
		if(loginTicket!=null&&request.getMethod().equalsIgnoreCase("post")){
			try{
				//认证
				Ticket ticket =  authenticationAuthorizeService.createTicket(new WebCredential(request, response));
				session.setAttribute(Ticket.TICKET_ID, ticket.getId());
				webConfiguration.getCookieGenerator().addCookie(response, ticket.getId());
				webConfiguration.getRedirectStrategy().redirect(request, response,WebUtil.getServerName(request)+webConfiguration.getSuccessUrl());
				session.removeAttribute(LOGIN_TICKET);
				return false;
			}catch(AuthenticationAuthorizeException e){
				webConfiguration.getRedirectStrategy().redirect(request, response,WebUtil.getServerName(request)+webConfiguration.getLoginUrl()+"?msg="+e.getMessage());
				return false;
			}	
		}else{
			//访问登录页面添加登录标识
			request.getSession(true).setAttribute(LOGIN_TICKET,webConfiguration.getUniqueTicketIdGenerator().getNewTicketId(LOGIN_TICKET));
		}
		
		return true;
	}

	
	
	


}
