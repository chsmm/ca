package com.ca.util;

import java.lang.reflect.Constructor;

/**
 * 反射工具类
 * @author ch
 *
 */
public final class ReflectUtils {
	
	private ReflectUtils(){}
	
	/**
	 * 根据类名获取class
	 * @param className
	 * @return
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> loadClass(final String className)throws IllegalArgumentException{
		try {
			return (Class<T>)Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(className + " 找不到对于类.");
		}
	}
	
	/**
	 * 根据类名与构造参数实例化指定类
	 * @param className
	 * @param args
	 * @return
	 */
	public static <T> T newIntance(final String className,final Object ...args){
		return newIntance(ReflectUtils.<T>loadClass(className),args);
	}
	
	/**
	 * 根据class与构造参数实例化指定类
	 * @param clazz
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newIntance(final Class<T> clazz,final Object ...args){
		Constructor<?> constructor = null;
		for(Constructor<?> c : clazz.getConstructors()){
			if(isAssignableFrom(c.getParameterTypes(),args)){
				constructor = c;
				break;
			}
		}
		try {
			return constructor == null ? null : (T) constructor.newInstance(args);
		} catch (Exception e) {
			throw new IllegalArgumentException(clazz + " 创建实例失败.",e);
		} 
	}
	
	/**
	 * 判断指定  args class 是否 是 parameterTypes calss子类
	 * @param parameterTypes
	 * @param args
	 * @return
	 */
	private static boolean isAssignableFrom(Class<?>[] parameterTypes,Object[] args){
		if(parameterTypes.length==args.length){
			for(int i=0;i<args.length;i++){
				if(!parameterTypes[i].isAssignableFrom(args[i].getClass())){
						return false;
				}
				
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 获取默认类加载器
	 * @return
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
		}
		if (cl == null) {
			cl = ReflectUtils.class.getClassLoader();
			if (cl == null) {
				try {
					cl = ClassLoader.getSystemClassLoader();
				}
				catch (Throwable ex) {
				}
			}
		}
		return cl;
	}
	

}
