package com.ca.core;
/**
 * 认证授权配置
 * 需要使用 {@link AuthenticationAuthorizeService } 可实现该接口
 * @author ch
 *
 */
public interface AuthenticationAuthorizeServiceConfig {
	/**
	 * 设置AuthenticationAuthorizeService
	 * @param authenticationAuthorizeService
	 */
	void setAuthenticationAuthorizeService(AuthenticationAuthorizeService authenticationAuthorizeService);
}
