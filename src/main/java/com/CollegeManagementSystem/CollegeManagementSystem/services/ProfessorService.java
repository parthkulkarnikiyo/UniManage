package com.CollegeManagementSystem.CollegeManagementSystem.services;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.ProfessorDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.ProfessorEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.ProfessorRepository;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper mapper;

    public ProfessorDTO addProfessor(ProfessorDTO professorDTO) {
        ProfessorEntity professorEntity=mapper.map(professorDTO,ProfessorEntity.class);
        professorRepository.save(professorEntity);
        return mapper.map(professorEntity,ProfessorDTO.class);
    }

    public ProfessorDTO getProfessorById(Long professorId)  {
        if(!professorRepository.existsById(professorId)){
            throw new ResourceNotFoundException("No professor with given ");
        }
        ProfessorEntity professorEntity=professorRepository.findById(professorId).get();
        return mapper.map(professorEntity,ProfessorDTO.class);
    }

    public ProfessorDTO assignProfessorToStudent(Long studentId, Long professorId) {
        if(!professorRepository.existsById(professorId)){
            throw new ResourceNotFoundException("No professor present with specified professor id");
        }
        if(!studentRepository.existsById(studentId)){
            throw new ResourceNotFoundException("No student present with specified student id");
        }
        StudentEntity studentEntity=studentRepository.findById(studentId).get();
        ProfessorEntity professorEntity=professorRepository.findById(professorId).get();
        studentEntity.getProfessors().add(professorEntity);
        professorEntity.getStudents().add(studentEntity);
        return mapper.map(professorRepository.save(professorEntity),ProfessorDTO.class);
    }
}
