package com.CollegeManagementSystem.CollegeManagementSystem.services;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.StudentDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper mapper;

    public StudentDTO getStudentById(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("No resource found with specified id");
        }
        StudentEntity studentEntity = studentRepository.findById(studentId).get();
        return mapper.map(studentEntity, StudentDTO.class);
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        StudentEntity student = mapper.map(studentDTO, StudentEntity.class);
        studentRepository.save(student);
        return mapper.map(student, StudentDTO.class);
    }
}
