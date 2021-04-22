package com.tistory.eclipse4j.api.restdocs;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.tistory.eclipse4j.core.annotation.RestDocClass;
import com.tistory.eclipse4j.core.annotation.RestDocProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@Slf4j
public class RestDocsResponseFieldGenerator {
	private static List<String> rootPath = Lists.newArrayList();
	private static List<FieldDescriptor> descriptors = Lists.newArrayList();
	
	
	private static void addFieldWithPath(Class<?> clz){
		Field[] filedArray = clz.getDeclaredFields();
		List<Field> fields = Arrays.asList(filedArray);
		RestDocClass restDocClass = clz.getDeclaredAnnotation(RestDocClass.class);
		if( Objects.isNull(restDocClass) ) {
			return;
		}
		String path = Joiner.on(".").join(rootPath);
		fields.forEach(f->{
			RestDocProperty docProperty = f.getDeclaredAnnotation(RestDocProperty.class);
			log.info("f {}", f.getType());
			if( DocsTypeUtils.isNumberType( f.getType() ) ) {
				descriptors.add( fieldWithPath(path+"."+ f.getName()).type(JsonFieldType.NUMBER).description(docProperty.description()) );
				return;
			}
			if( DocsTypeUtils.isStringType( f.getType() ) ) {
				descriptors.add( fieldWithPath(path+"."+f.getName()).type(JsonFieldType.STRING).description(docProperty.description()) );
				return;
			}
			if( DocsTypeUtils.isBooleanType( f.getType() ) ) {
				descriptors.add( fieldWithPath(path+"."+f.getName()).type(JsonFieldType.BOOLEAN).description(docProperty.description()) );
				return;
			}
			if( DocsTypeUtils.isMemberType( f.getType() ) ) {
				rootPath.add(f.getName());
				addFieldWithPath(f.getType());
				return;
			}
			if( DocsTypeUtils.isListType( f.getType() ) ) {
				rootPath.add(f.getName());
				ParameterizedType integerListType = (ParameterizedType) f.getGenericType();
		        Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
				addFieldWithPath(integerListClass);
				return;
			}
		});
	}
	
	public static List<FieldDescriptor> make(Class<?> clz, String path) {
		rootPath.add(path);
		addFieldWithPath(clz);
		return descriptors;
	}
}
