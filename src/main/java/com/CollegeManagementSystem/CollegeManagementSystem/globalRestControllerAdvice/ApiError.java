package com.CollegeManagementSystem.CollegeManagementSystem.globalRestControllerAdvice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@Builder
public class ApiError {
    String message;
    HttpStatus status;
    List<String> suberrors;
}
