package com.CollegeManagementSystem.CollegeManagementSystem.services;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.SubjectDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.ProfessorEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.SubjectEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.ProfessorRepository;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.StudentRepository;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper mapper;

    public SubjectDTO getSubjectDetailsBySubjectId(Long subjectId) {
        if(!subjectRepository.existsById(subjectId)){
            throw new ResourceNotFoundException("No subject with specified id");
        }
        SubjectEntity subjectEntity=subjectRepository.findById(subjectId).get();
        return mapper.map(subjectEntity,SubjectDTO.class);
    }

    public SubjectDTO addSubjectDetails(SubjectDTO subjectDTO) {
        SubjectEntity subjectEntity=mapper.map(subjectDTO,SubjectEntity.class);
        return mapper.map(subjectRepository.save(subjectEntity),SubjectDTO.class);
    }

    public SubjectDTO assignSubjectToProfessor(Long subjectId, Long professorId) {
        if(!subjectRepository.existsById(subjectId)){
            throw new ResourceNotFoundException("No subject with specified id");
        }
        if(!professorRepository.existsById(professorId)){
            throw new ResourceNotFoundException("No professor with specified id");
        }
        SubjectEntity subjectEntity=subjectRepository.findById(subjectId).get();
        ProfessorEntity professorEntity=professorRepository.findById(professorId).get();
        subjectEntity.setProfessor(professorEntity);
        professorEntity.getSubjects().add(subjectEntity);
        return mapper.map(subjectRepository.save(subjectEntity),SubjectDTO.class);
    }

    public SubjectDTO addStudentsToSubject(Long subjectId, Long studentId) {
        if(!subjectRepository.existsById(subjectId)){
            throw new ResourceNotFoundException("No subject with specified id");
        }
        if(!studentRepository.existsById(studentId)){
            throw new ResourceNotFoundException("No student with specified id");
        }
        SubjectEntity subjectEntity=subjectRepository.findById(subjectId).get();
        StudentEntity student=studentRepository.findById(studentId).get();
        subjectEntity.getStudents().add(student);
        student.getSubjects().add(subjectEntity);
        return mapper.map(subjectRepository.save(subjectEntity),SubjectDTO.class);
    }
}