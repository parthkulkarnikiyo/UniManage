package com.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.AdmissionRecordDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.services.AdmissionRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/admission")
@Slf4j
public class AdmissionRecordController {

    @Autowired
    AdmissionRecordService admissionRecordService;
    

    @GetMapping("/getAdmissionRecord/{admissionId}")
    public ResponseEntity<AdmissionRecordDTO>getAdmissionRecordById(@RequestParam Long admissionId){
        log.trace("Inside get mapping of admission controller with id = ${}",admissionId );
        AdmissionRecordDTO admissionRecordDTO=admissionRecordService.getAdmissionRecordById(admissionId);
        return ResponseEntity.ok(admissionRecordDTO);
    }

    @PostMapping("/addAdmissionOfStudent/studentId/{studentId}/fees/{fees}")
    public ResponseEntity<AdmissionRecordDTO> assignAdmissionOfStudent(@PathVariable Long studentId,
                                                                       @PathVariable Integer fees){
        log.trace("Inside post mapping of admission controller with" );
        AdmissionRecordDTO admissionRecordDTO=admissionRecordService.assignAdmissionOfStudent(studentId,fees);
        return ResponseEntity.ok(admissionRecordDTO);
    }
}