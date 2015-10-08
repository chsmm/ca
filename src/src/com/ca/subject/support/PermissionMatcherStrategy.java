package com.ca.subject.support;

import com.ca.subject.Permission;



/**
 * 权限匹配策略
 * @author ch
 *
 */
public interface PermissionMatcherStrategy {
	/**
	 * 匹配权限
	 * @param userPermission 用户拥有权限
	 * @param extractPermission 匹配的权限
	 * @return
	 */
	boolean matches(Permission userPermission,Permission extractPermission);

}
