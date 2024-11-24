package com.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.AdmissionRecordDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.services.AdmissionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admission")
public class AdmissionRecordController {

    @Autowired
    AdmissionRecordService admissionRecordService;

    @GetMapping("/getAdmissionRecord/{admissionId}")
    public ResponseEntity<AdmissionRecordDTO>getAdmissionRecordById(@RequestParam Long admissionId){
        AdmissionRecordDTO admissionRecordDTO=admissionRecordService.getAdmissionRecordById(admissionId);
        return ResponseEntity.ok(admissionRecordDTO);
    }

    @PostMapping("/addAdmissionOfStudent/studentId/{studentId}/fees/{fees}")
    public ResponseEntity<AdmissionRecordDTO> assignAdmissionOfStudent(@PathVariable Long studentId,
                                                                       @PathVariable Integer fees){
        AdmissionRecordDTO admissionRecordDTO=admissionRecordService.assignAdmissionOfStudent(studentId,fees);
        return ResponseEntity.ok(admissionRecordDTO);
    }
}