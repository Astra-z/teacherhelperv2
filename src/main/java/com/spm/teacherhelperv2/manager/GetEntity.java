package com.spm.teacherhelperv2.manager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.spm.teacherhelperv2.util.ObjectConvertUtils;

import javax.persistence.Transient;

public class GetEntity {

	public String getAnnotationValue(Field[] fields, String fieldName) {
		String annotationValue = null;
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			if(fieldName.equals(name)) {
				annotationValue=fields[i].getAnnotation(TableField.class).value();
			}
		}
		return annotationValue;
	}

	public Object setTableField(String data, Class<?> clazz, Field[] fields, Object obj) {	
		JSONObject fieldJson = JSONObject.parseObject(data);
//		("要修改的属性："+fieldJson);
		Set<String> keyList = fieldJson.keySet();
		for (String fieldName : keyList) {
			//"修改的字段名："+fieldName);
			char[] cs = fieldName.toCharArray();
			cs[0] -= 32;
			String fieldMethodName = String.valueOf(cs);
			String methodName = "set" + fieldMethodName;
//			("修改调用的方法名："+methodName);
			Class<?> typeClass = null;
			for (Field field : fields) {
				if (fieldName.equals(field.getName())&&field.getAnnotation(Transient.class)==null) {
					typeClass = (Class<?>) field.getGenericType();
				}
			}
			if (typeClass==null){
				continue;
			}
			try {
				Method method = clazz.getDeclaredMethod(methodName, typeClass);
				method.invoke(obj, ObjectConvertUtils.convertValue(fieldJson.get(fieldName), typeClass));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
//		("修改完成后："+obj);
		return obj;
	}

	private Object getClassTypeValue(Class<?> typeClass, Object value) {
		if (typeClass == Integer.class) {
			if (null == value) {
				return 0;
			}
			return value;
		} else if (typeClass == Short.class) {
			if (null == value) {
				return 0;
			}
			return ((Integer) value).shortValue();
		} else if (typeClass == Byte.class) {
			if (null == value) {
				return 0;
			}
			return ((Integer) value).byteValue();
		} else if (typeClass == Double.class) {
			if (null == value) {
				return 0;
			}
			return ((Integer) value).doubleValue();
		} else if (typeClass == Long.class) {
			if (null == value) {
				return 0;
			}
			return ((Integer) value).longValue();
		} else if (typeClass == String.class) {
			if (null == value) {
				return "";
			}
			return value;
		} else if (typeClass == Boolean.class) {
			if (null == value) {
				return true;
			}
			return value;
		} else if (typeClass == BigDecimal.class) {
			if (null == value) {
				return new BigDecimal(0);
			}
			return new BigDecimal(value + "");
		} else {
			return typeClass.cast(value);
		}
	}

}
