package com.tistory.eclipse4j.api.restdocs;

public class DocsTypeUtils {

	public static boolean isNumberType(Class<?> type) {
		return type.getSimpleName().toLowerCase().contains("long");
	}

	public static boolean isStringType(Class<?> type) {
		return type.getSimpleName().toLowerCase().contains("string") || isEnumType(type);
	}

	public static boolean isMemberType(Class<?> type) {
		return type.isMemberClass();
	}

	public static boolean isBooleanType(Class<?> type) {
		return type.getSimpleName().toLowerCase().contains("boolean");
	}

	public static boolean isEnumType(Class<?> type) {
		return type.isEnum();
	}

	public static boolean isListType(Class<?> type) {
		return type.getSimpleName().toLowerCase().contains("list") || type.getSimpleName().toLowerCase().contains("set");
	}
}
