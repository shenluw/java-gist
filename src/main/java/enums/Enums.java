package enums;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口描述:
 *
 * @author shenluw
 * @date 7/20/2020 2:40 PM
 */
public abstract class Enums {

	public static boolean contains(Class clazz, Object value) {
		assert clazz != null;
		Map<Object, Object> objs = cacheValues.get(clazz);
		if (objs == null) {
			objs = cacheIn(clazz);
		}
		return objs.containsKey(value);
	}

	public static <E> E from(Class clazz, Object value) {
		assert clazz != null;
		Map<Object, Object> objs = cacheValues.get(clazz);
		if (objs == null) {
			objs = cacheIn(clazz);
		}
		return (E) objs.get(value);
	}

	public static void check(Class clazz, Object value) {
		if (!contains(clazz, value)) {
			throw new IllegalArgumentException("enum " + clazz.getSimpleName() + " not support value " + value);
		}
	}

	/**
	 * enum: { code或者value : enumInstance }
	 */
	private final static Map<Class, Map<Object, Object>> cacheValues = new HashMap<>();

	private static Map<Object, Object> cacheIn(Class clazz) {
		EnumSet enums = EnumSet.allOf(clazz);
		Map<Object, Object> map = new HashMap<>(enums.size());
		for (Object e : enums) {
			if (e instanceof ValueEnum) {
				map.put(((ValueEnum<?>) e).getValue(), e);
			} else {
				map.put(getValueOrCode(e), e);
			}
		}
		cacheValues.put(clazz, map);
		return map;
	}


	private static Object getValueOrCode(Object obj) {
		Class<?> clazz = obj.getClass();
		Method method;
		try {
			method = clazz.getMethod("getValue");
		} catch (NoSuchMethodException e) {
			try {
				method = clazz.getMethod("getCode");
			} catch (NoSuchMethodException noSuchMethodException) {
				throw new IllegalArgumentException(String.format("enum [%s] not support", clazz.getName()));
			}
		}

		try {
			return method.invoke(obj);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException(String.format("enum [%s] invoke fail", clazz.getName()), e);
		}
	}

}
