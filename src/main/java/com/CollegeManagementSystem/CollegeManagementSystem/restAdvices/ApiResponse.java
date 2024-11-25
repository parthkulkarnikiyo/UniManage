package com.CollegeManagementSystem.CollegeManagementSystem.restAdvices;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Data
public class ApiResponse<T> {

    LocalDateTime timeStamp;
    ApiError apiError;
    T body;

    // Default constructor
    public ApiResponse() {
        timeStamp = LocalDateTime.now();
    }

    // Constructor with ApiError
    public ApiResponse(ApiError error) {
        this();
        apiError = error;
    }

    // Constructor with T (body)
    public ApiResponse(T body) {
        this();
        this.body = body;
    }

    // Constructor with all three parameters
    public ApiResponse(LocalDateTime timeStamp, ApiError apiError, T body) {
        this.timeStamp = timeStamp;
        this.apiError = apiError;
        this.body = body;
    }
}
