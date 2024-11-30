package com.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.StudentDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId) {
        log.trace("Entering getStudentById with studentId: {}", studentId);
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        log.info("Successfully fetched StudentDTO for studentId: {}", studentId);
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        log.trace("Entering addStudent with StudentDTO: {}", studentDTO);
        StudentDTO createdStudent = studentService.addStudent(studentDTO);
        log.info("Student added successfully with id: {}", createdStudent.getId());
        return new ResponseEntity<>(createdStudent, HttpStatus.OK);
    }
}
