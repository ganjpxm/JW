/**
 * $Id: ReflectUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>reflect method utility</p>
 * 
 * @author ganjp
 * @since 1.0
 */
public class ReflectUtil {
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * <p>copy source object to target object</p>
	 * 
	 * @param srcObject
	 * @param tgtObject
	 * 
	 */
	public static void copyValue(Object srcObject, Object tgtObject) {
		if (srcObject == null || tgtObject == null)
			return;

		Field[] sourceFields = srcObject.getClass().getDeclaredFields();
		for (int i = 0; i < sourceFields.length; i++) {
			Field sourceField = sourceFields[i];
			Field objectField = null;
			try {
				objectField = tgtObject.getClass().getDeclaredField(sourceField.getName());
			} catch (Exception ex) {
				continue;
			}
			Object value = null;
			try {
				Method method = getGetMethod(srcObject.getClass(), sourceField.getName());
				value = method.invoke(srcObject);
			} catch (Exception e) {
				try {
					value = sourceField.get(srcObject);
				} catch (Exception ex) {
					log.error("get" + sourceField.getName() + " value fail:" + ex.getMessage());
				}
			}
			try {
				Method method = getSetMethod(tgtObject.getClass(), objectField.getName());
				Object[] values = new Object[1];
				values[0] = value;
				method.invoke(tgtObject, values);
			} catch (Exception e) {
				try {
					objectField.set(tgtObject, value);
				} catch (Exception ex) {
					log.error("set method " + sourceField.getName() + " value fail:" + ex.getMessage());
				}
			}
		}
	}

	/**
	 * <p>get method object field name</p>
	 * 
	 * @param clazz		
	 * @param fieldName	
	 * @return Method
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Method getGetMethod(Class clazz, String fieldName) throws NoSuchMethodException {
		String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return clazz.getMethod(methodName);
	}

	/**
	 * <p>get set method by field name</p>
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Method getSetMethod(Class clazz, String fieldName) throws NoSuchMethodException, NoSuchFieldException {
		String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Class[] clazzz = new Class[1];
		clazzz[0] = clazz.getDeclaredField(fieldName).getType();
		return clazz.getMethod(methodName, clazzz);
	}
	
	/**
	 * <p>get field value by field name</p>
	 * <pre>	
	 * getPropertityValue(user,"org.orgName)
	 * </pre>
	 * 
	 * @param obj
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Object getPropertityValue(Object obj , String fieldName) throws Exception{
		if (obj == null || !StringUtil.hasText(fieldName)) {
			return null;
		}
		if (fieldName.indexOf("\\.")==-1) {
			Method method = ReflectUtil.getGetMethod(obj.getClass(), fieldName);
			return method.invoke(obj);
		} else {
			String[] fields = fieldName.split("\\.");
			if(fields.length == 0){
				return null;
			}
			Object result = obj;
			for (int i=0; i< fields.length;i++) {
				Method method = ReflectUtil.getGetMethod(result.getClass(), fields[i]);
				result = method.invoke(result);
			}
			return result;
		}
	}
	
	/**
	 * <p>Perform resolution of a class name.<p/>
	 *
	 * @param name The class name
	 * @return The class reference.
	 * @throws ClassNotFoundException From {@link Class#forName(String)}.
	 */
	@SuppressWarnings("rawtypes")
	public static Class classForName(String name) throws ClassNotFoundException {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if ( contextClassLoader != null ) {
				return contextClassLoader.loadClass(name);
			}
		} catch ( Throwable ignore ) {
			
		}
		return Class.forName( name );
	}
	
	/**
	 * Perform resolution of a class name.
	 * <p/>
	 * Here we first check the context classloader, if one, before delegating to
	 * {@link Class#forName(String, boolean, ClassLoader)} using the caller's classloader
	 *
	 * @param name The class name
	 * @param caller The class from which this call originated (in order to access that class's loader).
	 * @return The class reference.
	 * @throws ClassNotFoundException From {@link Class#forName(String, boolean, ClassLoader)}.
	 */
	@SuppressWarnings("rawtypes")
	public static Class classForName(String name, Class caller) throws ClassNotFoundException {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if ( contextClassLoader != null ) {
				return contextClassLoader.loadClass( name );
			}
		}
		catch ( Throwable ignore ) {
		}
		return Class.forName( name, true, caller.getClassLoader() );
	}
	
	/**
	 * Invoke the specified {@link Method} against the supplied target object
	 * with the supplied arguments. The target object can be <code>null</code>
	 * when invoking a static {@link Method}.
	 * <p>Thrown exceptions are handled via a call to {@link #handleReflectionException}.
	 * 
	 * @param method the method to invoke
	 * @param target the target object to invoke the method on
	 * @param args the invocation arguments (may be <code>null</code>)
	 * @return the invocation result, if any
	 */
	public static Object invokeMethod(Method method, Object target, Object[] args) {
		try {
			return method.invoke(target, args);
		}
		catch (Exception ex) {
			handleReflectionException(ex);
		}
		throw new IllegalStateException("Should never get here");
	}
	
	/**
	 * Handle the given reflection exception. Should only be called if
	 * no checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of an
	 * InvocationTargetException with such a root cause. Throws an
	 * IllegalStateException with an appropriate message else.
	 * 
	 * @param ex the reflection exception to handle
	 */
	public static void handleReflectionException(Exception ex) {
		if (ex instanceof NoSuchMethodException) {
			throw new IllegalStateException("Method not found: " + ex.getMessage());
		}
		if (ex instanceof IllegalAccessException) {
			throw new IllegalStateException("Could not access method: " + ex.getMessage());
		}
		if (ex instanceof InvocationTargetException) {
			handleInvocationTargetException((InvocationTargetException) ex);
		}
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		handleUnexpectedException(ex);
	}
	
	/**
	 * Handle the given invocation target exception. Should only be called if
	 * no checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of such
	 * a root cause. Throws an IllegalStateException else.
	 * @param ex the invocation target exception to handle
	 */
	public static void handleInvocationTargetException(InvocationTargetException ex) {
		rethrowRuntimeException(ex.getTargetException());
	}
	

	/**
	 * Rethrow the given {@link Throwable exception}, which is presumably the
	 * <em>target exception</em> of an {@link InvocationTargetException}.
	 * Should only be called if no checked exception is expected to be thrown by
	 * the target method.
	 * <p>Rethrows the underlying exception cast to an {@link RuntimeException}
	 * or {@link Error} if appropriate; otherwise, throws an
	 * {@link IllegalStateException}.
	 * 
	 * @param ex the exception to rethrow
	 * @throws RuntimeException the rethrown exception
	 */
	public static void rethrowRuntimeException(Throwable ex) {
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		if (ex instanceof Error) {
			throw (Error) ex;
		}
		handleUnexpectedException(ex);
	}
	
	/**
	 * 转换字符串到相应类型.
	 * 
	 * @param value 待转换的字符串
	 * @param toType 转换目标类型
	 */
	public static Object convertStringToObject(String value, Class<?> toType) {
		try {
			return ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}
	
	/**
	 * 将反射时的checked exception转换为unchecked exception.
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
	
	/**
	 * <p>Throws an IllegalStateException with the given exception as root cause.</p>
	 * 
	 * @param ex the unexpected exception
	 */
	private static void handleUnexpectedException(Throwable ex) {
		// Needs to avoid the chained constructor for JDK 1.4 compatibility.
		IllegalStateException isex = new IllegalStateException("Unexpected exception thrown");
		isex.initCause(ex);
		throw isex;
	}
}
