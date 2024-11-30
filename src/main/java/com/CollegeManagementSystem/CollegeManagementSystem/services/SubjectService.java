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
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
        log.trace("Entering getSubjectDetailsBySubjectId with subjectId: {}", subjectId);

        if(!subjectRepository.existsById(subjectId)){
            log.error("No subject found with id: {}", subjectId);
            throw new ResourceNotFoundException("No subject with specified id");
        }

        log.info("Fetching details for subjectId: {}", subjectId);
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).get();

        log.info("Successfully retrieved subject details for subjectId: {}", subjectId);
        return mapper.map(subjectEntity, SubjectDTO.class);
    }

    public SubjectDTO addSubjectDetails(SubjectDTO subjectDTO) {
        log.trace("Entering addSubjectDetails with subjectDTO: {}", subjectDTO);

        SubjectEntity subjectEntity = mapper.map(subjectDTO, SubjectEntity.class);
        log.info("Saving subject: {}", subjectEntity);

        SubjectEntity savedSubjectEntity = subjectRepository.save(subjectEntity);

        log.info("Successfully added subject with id: {}", savedSubjectEntity.getSubjectId());
        return mapper.map(savedSubjectEntity, SubjectDTO.class);
    }

    public SubjectDTO assignSubjectToProfessor(Long subjectId, Long professorId) {
        log.trace("Entering assignSubjectToProfessor with subjectId: {} and professorId: {}", subjectId, professorId);

        if(!subjectRepository.existsById(subjectId)){
            log.error("No subject found with id: {}", subjectId);
            throw new ResourceNotFoundException("No subject with specified id");
        }
        if(!professorRepository.existsById(professorId)){
            log.error("No professor found with id: {}", professorId);
            throw new ResourceNotFoundException("No professor with specified id");
        }

        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).get();
        ProfessorEntity professorEntity = professorRepository.findById(professorId).get();

        log.info("Assigning subjectId: {} to professorId: {}", subjectId, professorId);
        subjectEntity.setProfessor(professorEntity);
        professorEntity.getSubjects().add(subjectEntity);

        SubjectEntity updatedSubjectEntity = subjectRepository.save(subjectEntity);

        log.info("Successfully assigned subjectId: {} to professorId: {}", subjectId, professorId);
        return mapper.map(updatedSubjectEntity, SubjectDTO.class);
    }

    public SubjectDTO addStudentsToSubject(Long subjectId, Long studentId) {
        log.trace("Entering addStudentsToSubject with subjectId: {} and studentId: {}", subjectId, studentId);

        if(!subjectRepository.existsById(subjectId)){
            log.error("No subject found with id: {} in addStudentsToSubject", subjectId);
            throw new ResourceNotFoundException("No subject with specified id");
        }
        if(!studentRepository.existsById(studentId)){
            log.error("No student found with id: {}", studentId);
            throw new ResourceNotFoundException("No student with specified id");
        }

        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).get();
        StudentEntity student = studentRepository.findById(studentId).get();

        log.info("Adding studentId: {} to subjectId: {}", studentId, subjectId);
        subjectEntity.getStudents().add(student);
        student.getSubjects().add(subjectEntity);

        SubjectEntity updatedSubjectEntity = subjectRepository.save(subjectEntity);

        log.info("Successfully added studentId: {} to subjectId: {}", studentId, subjectId);
        return mapper.map(updatedSubjectEntity, SubjectDTO.class);
    }
}
