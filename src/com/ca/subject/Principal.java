package com.ca.subject;
/**
 * 登录用户信息类 
 * 实现  {@link java.security.Principal} 接口 用户可在request中获取
 * @author ch
 *
 */
public abstract class Principal implements java.security.Principal {

	private String name;

	public Principal(String name) {
		this.name = name;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == null) {
			return false;
		} else if (!(o instanceof Principal)) {
			return false;
		} else {
			return getName().equals(((Principal) o).getName());
		}
	}

	@Override
	public int hashCode() {
		return 37 * getName().hashCode();
	}

}
