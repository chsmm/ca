package com.ca.subject;

import java.util.List;

import com.ca.subject.support.PermissionMatcherStrategy;

/**
 * 主题 
 * 功能:
 * 1 保存用户与用户权限信息
 * 2 权限匹配
 * @author ch
 *
 */
public class Subject {
	
	/**
	 * 用户信息
	 */
	private Principal principal;
	
	/**
	 * 权限集合
	 */
	private List<Permission> permissions;
	
	/**
	 * 匹配策略
	 */
	private PermissionMatcherStrategy permissionMatcherStrategy;
	

	
	public Subject(Principal principal,List<Permission> permissions,PermissionMatcherStrategy permissionMatcherStrategy) {
		this.principal = principal;
		this.permissions = permissions;
		this.permissionMatcherStrategy = permissionMatcherStrategy;
	}
	
	
	public boolean checkPermission(Permission permission){
		for(Permission _permission :permissions){
			if(permissionMatcherStrategy.matches(_permission, permission)){
				return true;
			}
		}
		return false;
	}


	public Principal getPrincipal() {
		return principal;
	}


	public List<Permission> getPermissions() {
		return permissions;
	}

}
 