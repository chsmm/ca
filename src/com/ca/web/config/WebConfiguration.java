package com.ca.web.config;

import com.ca.core.Configuration;
import com.ca.subject.Permission;
import com.ca.web.support.ArgumentExtraction;
import com.ca.web.support.RedirectStrategy;
import com.ca.web.support.UrlPatternMatcherStrategy;
import com.ca.web.util.CookieGenerator;

/**
 * web参数配置
 * @author ch
 *
 */
public interface WebConfiguration extends Configuration{
	
	String getLoginUrl();

	String getLogoutUrl();

	String getSuccessUrl();

	String getAuthorizationFailureUrl();

	UrlPatternMatcherStrategy getIgnoreUrlPatternStrategy();

	RedirectStrategy getRedirectStrategy();

	CookieGenerator getCookieGenerator();

	ArgumentExtraction<Permission> getPermissionArgumentExtraction();
}
