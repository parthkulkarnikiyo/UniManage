package com.CollegeManagementSystem.CollegeManagementSystem.services;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.AdmissionRecordDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.AdmissionRecordEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.AdmissionRecordRepository;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdmissionRecordService {

    @Autowired
    AdmissionRecordRepository admissionRecordRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper mapper;

    public AdmissionRecordDTO getAdmissionRecordById(Long admissionId) {
        log.trace("Entering getAdmissionRecordById with admissionId: {}", admissionId);
        if (!admissionRecordRepository.existsById(admissionId)) {
            log.error("No admission found with admissionId: {}", admissionId);
            throw new ResourceNotFoundException("No admission found with admissionId: " + admissionId);
        }
        log.info("Admission record found with admissionId: {}", admissionId);
        AdmissionRecordEntity admissionRecordEntity = admissionRecordRepository.findById(admissionId)
                .orElseThrow(() -> {
                    log.error("Admission record unexpectedly not found after existsById check for admissionId: {}", admissionId);
                    return new ResourceNotFoundException("No admission found with admissionId: " + admissionId);
                });
        log.info("Successfully fetched AdmissionRecordEntity from database for admissionId: {}", admissionId);
        return mapper.map(admissionRecordEntity, AdmissionRecordDTO.class);
    }

    public AdmissionRecordDTO assignAdmissionOfStudent(Long studentId, Integer fees) {
        log.trace("Entering assignAdmissionOfStudent with studentId: {} and fees: {}", studentId, fees);
        if (!studentRepository.existsById(studentId)) {
            log.error("No student found with studentId: {}", studentId);
            throw new ResourceNotFoundException("No resource found with studentId: " + studentId);
        }
        log.info("Student found with studentId: {}", studentId);

        StudentEntity student = studentRepository.findById(studentId).orElseThrow(() -> {
            log.error("Student unexpectedly not found after existsById check for studentId: {}", studentId);
            return new ResourceNotFoundException("No student found with studentId: " + studentId);
        });

        log.info("Creating new AdmissionRecordEntity for studentId: {} with fees: {}", studentId, fees);
        AdmissionRecordEntity admissionRecordEntity = new AdmissionRecordEntity();
        admissionRecordEntity.setFees(fees);
        admissionRecordEntity.setStudent(student);

        log.info("Saving AdmissionRecordEntity to database for studentId: {}", studentId);
        AdmissionRecordEntity savedAdmissionRecord = admissionRecordRepository.save(admissionRecordEntity);
        log.info("Successfully saved AdmissionRecordEntity with admissionId: {}", savedAdmissionRecord.getId());

        return mapper.map(savedAdmissionRecord, AdmissionRecordDTO.class);
    }
}
