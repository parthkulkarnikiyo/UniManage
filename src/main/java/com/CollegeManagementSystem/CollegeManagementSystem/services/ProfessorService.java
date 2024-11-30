package com.CollegeManagementSystem.CollegeManagementSystem.services;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.ProfessorDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.ProfessorEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.ProfessorRepository;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper mapper;

    public ProfessorDTO addProfessor(ProfessorDTO professorDTO) {
        log.trace("Entering addProfessor with ProfessorDTO: {}", professorDTO);
        ProfessorEntity professorEntity = mapper.map(professorDTO, ProfessorEntity.class);
        professorRepository.save(professorEntity);
        log.info("Professor added successfully with id: {}", professorEntity.getId());
        return mapper.map(professorEntity, ProfessorDTO.class);
    }

    public ProfessorDTO getProfessorById(Long professorId) {
        log.trace("Entering getProfessorById with professorId: {}", professorId);
        if (!professorRepository.existsById(professorId)) {
            log.error("No professor found with id: {}", professorId);
            throw new ResourceNotFoundException("No professor with given id");
        }
        ProfessorEntity professorEntity = professorRepository.findById(professorId).get();
        log.info("Successfully fetched ProfessorDTO for professorId: {}", professorId);
        return mapper.map(professorEntity, ProfessorDTO.class);
    }

    public ProfessorDTO assignProfessorToStudent(Long studentId, Long professorId) {
        log.trace("Entering assignProfessorToStudent with studentId: {} and professorId: {}", studentId, professorId);

        if (!professorRepository.existsById(professorId)) {
            log.error("No professor found with id : {} in assignProfessorToStudent", professorId);
            throw new ResourceNotFoundException("No professor present with specified professor id");
        }
        if (!studentRepository.existsById(studentId)) {
            log.error("No student found with id: {}", studentId);
            throw new ResourceNotFoundException("No student present with specified student id");
        }

        StudentEntity studentEntity = studentRepository.findById(studentId).get();
        ProfessorEntity professorEntity = professorRepository.findById(professorId).get();
        studentEntity.getProfessors().add(professorEntity);
        professorEntity.getStudents().add(studentEntity);

        ProfessorEntity updatedProfessorEntity = professorRepository.save(professorEntity);
        log.info("Successfully assigned professorId: {} to studentId: {}", professorId, studentId);

        return mapper.map(updatedProfessorEntity, ProfessorDTO.class);
    }
}
