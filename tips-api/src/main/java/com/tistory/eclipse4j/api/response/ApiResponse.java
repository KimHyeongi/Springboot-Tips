package com.tistory.eclipse4j.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T t){
        return new ApiResponse("OK", "SUCCESS", t);
    }

    public static <T> ApiResponse<T> fail(){
        return new ApiResponse("FAIL", "FAIL", null);
    }
}
