package com.assignment.Week2Assignment.responses;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class GlobalApiResponse<T> {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private T data;
    private ApiErrorResponse error;

    public GlobalApiResponse(){
        this.timestamp=LocalDateTime.now();
    }

    public GlobalApiResponse(T data){
        this();
        this.data=data;
    }

    public GlobalApiResponse(ApiErrorResponse error){
        this();
        this.error=error;
    }

}
