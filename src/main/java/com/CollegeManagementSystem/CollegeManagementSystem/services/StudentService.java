package com.CollegeManagementSystem.CollegeManagementSystem.services;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.StudentDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper mapper;

    public StudentDTO getStudentById(Long studentId) {
        log.trace("Entering getStudentById with studentId: {}", studentId);

        if (!studentRepository.existsById(studentId)) {
            log.error("No student found with id: {}", studentId);
            throw new ResourceNotFoundException("No resource found with specified id");
        }

        log.info("Fetching student details for studentId: {}", studentId);
        StudentEntity studentEntity = studentRepository.findById(studentId).get();

        log.info("Successfully retrieved student details for studentId: {}", studentId);
        return mapper.map(studentEntity, StudentDTO.class);
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        log.trace("Entering addStudent with studentDTO: {}", studentDTO);

        // Mapping the DTO to Entity
        StudentEntity student = mapper.map(studentDTO, StudentEntity.class);

        log.info("Saving student entity: {}", student);
        studentRepository.save(student);

        log.info("Successfully added new student: {}", student.getId());
        return mapper.map(student, StudentDTO.class);
    }
}
