package com.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.StudentDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.ProfessorEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO>getStudentById(@PathVariable Long studentId){
        StudentDTO studentDTO =studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    public ResponseEntity<StudentDTO>addStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO studentDTO1=studentService.addStudent(studentDTO);
        return new ResponseEntity<>(studentDTO1, HttpStatus.OK);
    }

}
