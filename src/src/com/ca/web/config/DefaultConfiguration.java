package com.ca.web.config;

import com.ca.core.AuthConfiguration;
import com.ca.core.handler.AuthenticationAuthorizeHandle;
import com.ca.subject.Permission;
import com.ca.subject.support.PermissionMatcherStrategy;
import com.ca.ticket.registry.TicketRegistry;
import com.ca.ticket.support.ExpirationStrategy;
import com.ca.util.ReflectUtils;
import com.ca.util.UniqueTicketIdGenerator;
import com.ca.web.support.ArgumentExtraction;
import com.ca.web.support.RedirectStrategy;
import com.ca.web.support.UrlPatternMatcherStrategy;
import com.ca.web.util.CookieGenerator;
/**
 * 默认配置
 * 实现 {@link AuthConfiguration } 与  {@link WebConfiguration}
 * @author ch
 *
 */
public class DefaultConfiguration implements AuthConfiguration,WebConfiguration{
	
	protected String loginUrl;
	
	protected String logoutUrl;
	
	protected String successUrl;
	
	protected String authorizationFailureUrl;
	
	protected AuthenticationAuthorizeHandle authorizeHandle;
	
	protected TicketRegistry ticketRegistry;
	
	protected ExpirationStrategy expirationStrategy;
	
	protected UniqueTicketIdGenerator uniqueTicketIdGenerator;
	
	protected PermissionMatcherStrategy permissionMatcherStrategy;
	
	protected UrlPatternMatcherStrategy ignoreUrlPatternStrategy;
	
	protected RedirectStrategy redirectStrategy;
		
	protected CookieGenerator cookieGenerator;

	protected ArgumentExtraction<Permission> permissionArgumentExtraction;
	
	
	public DefaultConfiguration() {}
	
	public DefaultConfiguration(AuthenticationConfig configProperties) {
		init(configProperties);
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getAuthorizationFailureUrl() {
		return authorizationFailureUrl;
	}

	public void setAuthorizationFailureUrl(String authorizationFailureUrl) {
		this.authorizationFailureUrl = authorizationFailureUrl;
	}

	
	public AuthenticationAuthorizeHandle getAuthorizeHandle() {
		return authorizeHandle;
	}

	public void setAuthorizeHandle(AuthenticationAuthorizeHandle authorizeHandle) {
		this.authorizeHandle = authorizeHandle;
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

	public void setPermissionMatcherStrategy(
			PermissionMatcherStrategy permissionMatcherStrategy) {
		this.permissionMatcherStrategy = permissionMatcherStrategy;
	}

	public UrlPatternMatcherStrategy getIgnoreUrlPatternStrategy() {
		return ignoreUrlPatternStrategy;
	}

	public void setIgnoreUrlPatternStrategy(
			UrlPatternMatcherStrategy ignoreUrlPatternStrategy) {
		this.ignoreUrlPatternStrategy = ignoreUrlPatternStrategy;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	public CookieGenerator getCookieGenerator() {
		return cookieGenerator;
	}

	public void setCookieGenerator(CookieGenerator cookieGenerator) {
		this.cookieGenerator = cookieGenerator;
	}

	public ArgumentExtraction<Permission> getPermissionArgumentExtraction() {
		return permissionArgumentExtraction;
	}

	public void setPermissionArgumentExtraction(ArgumentExtraction<Permission> permissionArgumentExtraction) {
		this.permissionArgumentExtraction = permissionArgumentExtraction;
	}

	private void init(AuthenticationConfig configProperties){
		initUrl(configProperties);
		initCommonHandle(configProperties);
		initAuthenticationAuthorizeService(configProperties);
	}
	
	/**
	 * 初始化url配置
	 * @param configProperties
	 */
	private void initUrl(AuthenticationConfig configProperties){
		final String loginUrl = configProperties.getLoginUrl();
		final String logoutUrl = configProperties.getLogoutUrl();
		final String successUrl = configProperties.getSuccessUrl();
		final String authorizationFailureUrl = configProperties.getAuthorizationFailureUrl();
		if(loginUrl==null || logoutUrl==null||  successUrl==null || authorizationFailureUrl == null){
			throw new NullPointerException("url 为空");
		}
		setLogoutUrl(logoutUrl);
		setLoginUrl(loginUrl);
		setSuccessUrl(successUrl);
		setAuthorizationFailureUrl(authorizationFailureUrl);
	}
	
	
	/**
	 * 初始化公用组件
	 * @param configProperties
	 */
	private void initCommonHandle(AuthenticationConfig configProperties){
		try {
			String IgnoreUrlPatternStrategy = configProperties.getIgnoreUrlPatternStrategyClassName();
			if(IgnoreUrlPatternStrategy!=null){
				setIgnoreUrlPatternStrategy(this.<UrlPatternMatcherStrategy>newInstance(IgnoreUrlPatternStrategy));
			}
			setRedirectStrategy(this.<RedirectStrategy>newInstance(configProperties.getRedirectStrategyClassName()));
			setCookieGenerator(this.<CookieGenerator>newInstance(configProperties.getCookieGeneratorClassName(),configProperties.getCookieName()));
			setUniqueTicketIdGenerator((this.<UniqueTicketIdGenerator>newInstance(configProperties.getUniqueTicketIdGeneratorClassName())));
			setPermissionArgumentExtraction(this.<ArgumentExtraction<Permission>> newInstance(configProperties.getPermissionArgumentExtractionClassName()));
		} catch (Exception e) {
			throw new IllegalArgumentException("初始失败, 参数配置不正确!",e);
		}
		
		/*configProperties.getIgnoreUrlPatternStrategyType();
		configProperties.getIgnoreUrls();*/
	}
	

	
	/**
	 * 初始化认证授权服务
	 * @param configProperties
	 */
	private void initAuthenticationAuthorizeService(AuthenticationConfig configProperties){
		try{
			authorizeHandle = newInstance(configProperties.getAuthenticationAuthorizeHandleClassName());
			permissionMatcherStrategy = newInstance(configProperties.getPermissionMatcherStrategyClassName());
			ticketRegistry = newInstance(configProperties.getTicketRegistryClassName());
			final String timeOut =configProperties.getTicketTimeOut();
			if(timeOut!=null){
				expirationStrategy = newInstance(configProperties.getTicketExpirationStrategyClassName(),Long.parseLong(timeOut));
			}else{
				expirationStrategy = newInstance(configProperties.getTicketExpirationStrategyClassName());	
			}
			//AuthenticationAuthorizeService authenticationAuthorizeService = new AuthenticationAuthorizeServiceImpl(authorizeHandle, ticketRegistry, getUniqueTicketIdGenerator(), expirationStrategy, permissionMatcherStrategy);
		}catch(Exception e){
			throw new IllegalArgumentException("初始化 AuthenticationAuthorizeService失败, 参数配置不正确!",e);
		}
	}
	/**
	 * 创建实例
	 * @param className
	 * @param args
	 * @return
	 * @throws Exception
	 */
	private <T> T newInstance(final String className,final Object... args) throws Exception{
		try{
		if(className!=null){
			return ReflectUtils.newIntance(className, args);
		}
		throw new NullPointerException("类名为空");
		}catch(Exception e){
			throw e;
		}
		
	}

}
