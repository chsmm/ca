package com.ca.core;

import com.ca.core.handler.AuthenticationAuthorizeHandle;
import com.ca.subject.support.PermissionMatcherStrategy;
import com.ca.ticket.registry.TicketRegistry;
import com.ca.ticket.support.ExpirationStrategy;
/**
 * 认证配置接口 
 * 为 {@link AuthenticationAuthorizeService} 接口提供认证配置
 * @author ch
 *
 */
public interface AuthConfiguration extends Configuration{
	
	/**
	 * 认证授权处理类
	 * @return
	 */
	 AuthenticationAuthorizeHandle getAuthorizeHandle() ;
	 
	 /**
	  * 标识信息注册接口
	  * @return
	  */
	 TicketRegistry getTicketRegistry() ;
	 /**
	  * 标识过期策略
	  * @return
	  */
	 ExpirationStrategy getExpirationStrategy() ;
	 
	 /**
	  * 权限匹配策略
	  * @return
	  */
	 PermissionMatcherStrategy getPermissionMatcherStrategy() ; 
}
