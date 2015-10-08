package com.ca.web.filter;

import javax.servlet.Filter;
import com.ca.util.ReflectUtils;
import com.ca.web.filter.handle.*;
/**
 * 默认处理过滤器
 * @author ch
 *
 */
public enum DefaultFilter {
		
		checkLogin(CheckLoginFilter.class),
		login(LoginFilter.class),
		logout(LogoutFilter.class),
		wrapper(HttpServletWrapperFilter.class),
		auth(AuthFilter.class){},
		Ignore(RequestExcludedFilter.class);
		
		private  final Class<? extends Filter> filterClass;
		
		private DefaultFilter(Class<? extends Filter> filterClass){
			this.filterClass  = filterClass;
			//this.filter =ReflectUtils.newIntance(filterClass); 
		}
		
		public Filter getFilter(Object ...objects){
			return ReflectUtils.newIntance(filterClass,objects);
		}
	

}
