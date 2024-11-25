package com.assignment.Week2Assignment.responses;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
public class ApiErrorResponse {
    private HttpStatus httpStatus;
    private String msg;
    private List<String> subErrors;
}
