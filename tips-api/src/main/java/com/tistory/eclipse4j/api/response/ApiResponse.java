package com.tistory.eclipse4j.api.response;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Builder
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public long getTimestamp(){
        return new Date().getTime();
    }
    public static <T> ApiResponse<T> success(T t){
        return new ApiResponse("OK", "SUCCESS", t);
    }

    public static <T> ApiResponse<T> fail(){
        return new ApiResponse("FAIL", "FAIL", null);
    }
}
