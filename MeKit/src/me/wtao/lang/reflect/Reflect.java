package me.wtao.lang.reflect;

import java.lang.reflect.Method;

import android.util.Log;

public abstract class Reflect {
	private static final String TAG = Reflect.class.getSimpleName();

	/**
	 * traverse all possible methods, and output to log for testing
	 */
	public static void log(Class<?> CLASS_target) {
		Log.v(TAG, CLASS_target.getCanonicalName());
		
		Method[] methods = CLASS_target.getMethods();
		for (Method method : methods) {
			Class<?>[] params = method.getParameterTypes();
			StringBuilder sb = new StringBuilder();
			sb.append("  - ");
			sb.append(method.getReturnType());
			sb.append(' ');
			sb.append(method.getName());
			sb.append('(');
			final int length = params.length;
			for (int i = 0; i != length; ++i) {
				sb.append(params[i].getName());
				if (i != length - 1) {
					sb.append(", ");
				}
			}
			sb.append(')');
			Log.v(TAG, sb.toString());
		}
	}
}
