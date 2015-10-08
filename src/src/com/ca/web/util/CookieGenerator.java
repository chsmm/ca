package com.ca.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * cookie 生成器
 * @author ch
 *
 */
public class CookieGenerator {
	
	
	public static final String DEFAULT_COOKIE_PATH = "/";
	
	/**
	 * cookie 目录
	 */
	private String path=DEFAULT_COOKIE_PATH;
	
	/**
	 * cookie名称
	 */
	private String cookieName;
	
	/**
	 * 生命周期
	 */
	private Integer maxAge = -1;
	
	/**
	 * 浏览器使用的安全协议 true:HTTPS或SSL false : http 
	 */
	private boolean cookieSecure =false;
	
	/**
	 * 游览器显示该cookie信息  true:不显示 false:显示
	 */
	private boolean cookieHttpOnly = true;
	
	public CookieGenerator(String cookieName) {
		this.cookieName = cookieName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public boolean isCookieSecure() {
		return cookieSecure;
	}

	public void setCookieSecure(boolean cookieSecure) {
		this.cookieSecure = cookieSecure;
	}

	public boolean isCookieHttpOnly() {
		return cookieHttpOnly;
	}

	public void setCookieHttpOnly(boolean cookieHttpOnly) {
		this.cookieHttpOnly = cookieHttpOnly;
	}
	
	
	/**
	 * 新增cookie
	 * @param response
	 * @param cookieValue
	 */
	public void addCookie(HttpServletResponse response ,String cookieValue){
		Cookie cookie =createCookie(cookieValue);
		cookie.setMaxAge(getMaxAge());
		cookie.setSecure(isCookieSecure());
		cookie.setHttpOnly(isCookieHttpOnly());
		response.addCookie(cookie);
	}
	
	/**
	 * 删除cookie
	 * @param response
	 */
	public void removeCookie(HttpServletResponse response){
		Cookie cookie =createCookie("");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	/**
	 * 创建cookie
	 * @param cookieValue
	 * @return
	 */
	protected Cookie createCookie(String cookieValue){
		Cookie cookie = new Cookie(getCookieName(), cookieValue);
		cookie.setPath(getPath());
		return cookie;
	}
	
	/**
	 * 获取 cookie 值
	 * @param request
	 * @return
	 */
	public String getCookieValue(HttpServletRequest request){
		Cookie cookie = getCookie(request);
		return (cookie!=null ? cookie.getValue() : null);
	}
	
	/**
	 * 获取 cookie 
	 * @param request
	 * @return
	 */
	public Cookie getCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(getCookieName())){
					return cookie;
				}
			}
		}
		return null;
	}
	
}
