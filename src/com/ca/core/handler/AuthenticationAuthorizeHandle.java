package com.ca.core.handler;

import java.util.List;

import com.ca.core.Credential;
import com.ca.subject.Permission;
import com.ca.subject.Principal;

/**
 * 认证与授权接口
 * 该接口需要使用者自行扩展
 * @author ch
 *
 */
public interface AuthenticationAuthorizeHandle {
	
	/**
	 * 基于凭证认证用户
	 * @param credential
	 * @return Principal 用户信息
	 * @throws AuthenticationAuthorizeException
	 */
	Principal authentication(Credential credential) throws AuthenticationAuthorizeException;
	
	/***
	 * 对用户授权
	 * @param credential
	 * @param principal
	 * @return
	 * @throws AuthenticationAuthorizeException
	 */
	List<Permission> authorize(Credential credential,Principal principal) throws AuthenticationAuthorizeException;
}
