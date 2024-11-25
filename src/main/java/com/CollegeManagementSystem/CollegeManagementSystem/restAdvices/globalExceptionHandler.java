package com.CollegeManagementSystem.CollegeManagementSystem.restAdvices;

import com.CollegeManagementSystem.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class globalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoSuchElementExecption(ResourceNotFoundException exception)
    {
        ApiError apierror=ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
        return buildErrorResponseEntity(apierror);
    }


    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apierror) {
        return new ResponseEntity<>(new ApiResponse<>(apierror),apierror.getStatus());
    }


}
