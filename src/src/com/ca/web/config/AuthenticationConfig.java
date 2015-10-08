package com.ca.web.config;

import java.io.InputStream;
import java.util.Properties;

import com.ca.ticket.Ticket;
import com.ca.ticket.registry.DefaultTicketRegistry;
import com.ca.ticket.support.TicketExpirationStrategy;
import com.ca.util.DefaultUniqueTicketIdGenerator;
import com.ca.util.ReflectUtils;
import com.ca.web.support.DefaultRedirectStrategy;
import com.ca.web.util.CookieGenerator;


/**
 * 认证配置
 * @author ch
 *
 */
public final class AuthenticationConfig {
	/**
	 * 配置文件名称
	 */
	public static final String NAME = "config";
	
	/**
	 * 登录页面url
	 */
	private static final String LOGIN_URL = "loginUrl";
	
	private static final String LOGOUT_URL = "logoutUrl";
	
	/**
	 * 登录成功跳转url
	 */
	private static final String SUCCESS_URL = "successUrl";
	
	/**
	 * 授权失败跳转url
	 */
	private static final String AUTHORIZATION_FAILURE_URL = "authorizationFailureUrl";
	
	
	/**
	 * 忽略url匹配模式策略
	 */
	private static final String IGNOREURLPATTERNSTRATEGY = "ignoreUrlPatternStrategy";
	
	
	/**
	 * 页面跳转策略
	 */
	private static final String REDIRECTSTRATEGY_NAME = "redirectStrategy";
	
	
	/**
	 * 访问权限信息提取
	 */
	private static final String PERMISSIONARGUMENTEXTRACTION_NAME = "permissionArgumentExtraction";
	
	/**
	 * 认证授权处理类
	 */
	private static final String AUTHENTICATIONAUTHORIZEHANDLE_NAME = "authenticationAuthorizeHandle";
	/**
	 * 权限匹配策略
	 */
	private static final String PERMISSIONMATCHERSTRATEGY_NAME ="permissionMatcherStrategy"; 
	
	/**
	 * 标识注册处理类
	 */
	private static final String TICKETREGISTRY_NAME="ticketRegistry";
	
	/**
	 * 标识超时时间
	 */
	private static final String TICKET_TIMEOUT = "ticketTimeOut";
	
	
	/**
	 * 标识过期策略
	 */
	private static final String EXPIRATIONSTRATEGY_NAME="ticketExpirationStrategy";
	/**
	 * 标识生成处理类
	 */
	private static final String UNIQUETICKETIDGENERATOR_NAME="uniqueTicketIdGenerator";
	
	/**
	 * cookie 生成器
	 */
	private static final String COOKIEGENERATOR_NAME = "cookieGenerator";
	
	private static final String COOKIENAME = "cookieName";
	
			
			
	/**
	 * Properties 文件
	 */
	private Properties props;
	
	public AuthenticationConfig(String properties) throws Exception {
		if(properties!=null){
			InputStream is =ReflectUtils.getDefaultClassLoader().getResourceAsStream(properties);
			try {
				props  = new Properties();
				props.load(is);
			}
			finally {
				is.close();
			}
		}else{
			throw new NullPointerException("配置文件不能为空");
		}
		
	}
	
	public String getLoginUrl(){
		return  props.getProperty(LOGIN_URL);
	}
	
	public String getLogoutUrl(){
		return props.getProperty(LOGOUT_URL);
	}
	
	public String getSuccessUrl(){
		return  props.getProperty(SUCCESS_URL);
	}
	
	public String getAuthorizationFailureUrl(){
		return  props.getProperty(AUTHORIZATION_FAILURE_URL);
	}
	
	public String getIgnoreUrlPatternStrategyClassName(){
		return  props.getProperty(IGNOREURLPATTERNSTRATEGY);
	}

	public String getRedirectStrategyClassName(){
		return  props.getProperty(REDIRECTSTRATEGY_NAME,DefaultRedirectStrategy.class.getName());
	}
	
	public String getPermissionArgumentExtractionClassName(){
		return  props.getProperty(PERMISSIONARGUMENTEXTRACTION_NAME);
	}
	
	public String getAuthenticationAuthorizeHandleClassName(){
		return props.getProperty(AUTHENTICATIONAUTHORIZEHANDLE_NAME);
	}
	
	
	public String getPermissionMatcherStrategyClassName(){
		return props.getProperty(PERMISSIONMATCHERSTRATEGY_NAME);
	}
	
	public String getTicketRegistryClassName(){
		return props.getProperty(TICKETREGISTRY_NAME,DefaultTicketRegistry.class.getName());
	}
	
	public String getTicketTimeOut(){
		return props.getProperty(TICKET_TIMEOUT);
	}
	
	public String getTicketExpirationStrategyClassName(){
		return props.getProperty(EXPIRATIONSTRATEGY_NAME,TicketExpirationStrategy.class.getName());
	}
	
	
	public String getUniqueTicketIdGeneratorClassName(){
		return props.getProperty(UNIQUETICKETIDGENERATOR_NAME,DefaultUniqueTicketIdGenerator.class.getName());
	}
	
	public String getCookieGeneratorClassName(){
		return props.getProperty(COOKIEGENERATOR_NAME,CookieGenerator.class.getName());
	}
	public String getCookieName(){
		return props.getProperty(COOKIENAME,Ticket.TICKET_ID);
	}
	

	
	
	

}
