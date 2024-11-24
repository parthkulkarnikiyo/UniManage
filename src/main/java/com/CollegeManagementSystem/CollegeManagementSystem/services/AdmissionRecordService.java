package com.CollegeManagementSystem.CollegeManagementSystem.services;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.AdmissionRecordDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.AdmissionRecordEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.AdmissionRecordRepository;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmissionRecordService {

    @Autowired
    AdmissionRecordRepository admissionRecordRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper mapper;

    public  AdmissionRecordDTO getAdmissionRecordById(Long admissionId) {
        if(!admissionRecordRepository.existsById(admissionId)){
            throw new ResourceNotFoundException("No admission found with admissionId");
        }
        AdmissionRecordEntity admissionRecordEntity=admissionRecordRepository.findById(admissionId).orElseThrow();
        return mapper.map(admissionRecordEntity,AdmissionRecordDTO.class);
    }

    public AdmissionRecordDTO assignAdmissionOfStudent(Long studentId,Integer fees) {
        if(!studentRepository.existsById(studentId)){
            throw new ResourceNotFoundException("No resource found with student id");
        }
        StudentEntity student=studentRepository.findById(studentId).get();
        AdmissionRecordEntity admissionRecordEntity=new AdmissionRecordEntity();
        admissionRecordEntity.setFees(fees);
        admissionRecordEntity.setStudent(student);
        AdmissionRecordEntity admissionRecordEntity1=admissionRecordRepository.save(admissionRecordEntity);
        return mapper.map(admissionRecordEntity1,AdmissionRecordDTO.class);
    }
}
