package com.ca.web.filter.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ca.subject.Permission;
import com.ca.subject.Subject;
import com.ca.ticket.Ticket;
import com.ca.web.config.WebConfiguration;
import com.ca.web.util.WebUtil;
/**
 * 认证处理过滤器
 * 认证用户访问url是否具有相应权限
 * @author ch
 *
 */
public class AuthFilter extends HandleFilter{
	
	
	public AuthFilter(WebConfiguration webConfiguration) {
		super(webConfiguration);
	}

	@Override
	protected boolean preHandle(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String ticketId = (String)request.getSession().getAttribute(Ticket.TICKET_ID);
		/**
		 * 提取权限对象
		 */
		Permission permission = webConfiguration.getPermissionArgumentExtraction().extract(request);
		Ticket ticket = authenticationAuthorizeService.getTicket(ticketId);
		Subject subject = ticket.getSubject();
		//检查权限
		if (!subject.checkPermission(permission)) {
			webConfiguration.getRedirectStrategy().redirect(request, response,WebUtil.getServerName(request)+webConfiguration.getAuthorizationFailureUrl());
			return false;
		}
		return true;
	}


	

}
