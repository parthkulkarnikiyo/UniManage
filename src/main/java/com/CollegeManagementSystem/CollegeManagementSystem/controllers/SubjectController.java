package com.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.SubjectDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.SubjectEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.repository.SubjectRepository;
import com.CollegeManagementSystem.CollegeManagementSystem.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectSerivce;

    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO>getSubjectDetailsBySubjectId(@PathVariable Long subjectId){
        SubjectDTO subjectDTO=subjectSerivce.getSubjectDetailsBySubjectId(subjectId);
        return ResponseEntity.ok(subjectDTO);
    }

    @PostMapping
    public ResponseEntity<SubjectDTO>addSubjectDetails(@RequestBody SubjectDTO subjectDTO){
        return ResponseEntity.ok(subjectSerivce.addSubjectDetails(subjectDTO));
    }

    @PatchMapping("/assignSubjectToProfessor/subjectId/{subjectId}/professor/{professorId}")
    public ResponseEntity<SubjectDTO>assignSubjectToProfessor(@PathVariable Long subjectId,@PathVariable Long professorId){
        return ResponseEntity.ok(subjectSerivce.assignSubjectToProfessor(subjectId,professorId));
    }

    @PatchMapping("/addStudentsInSubject/subjectId/{subjectId}/studentId/{studentId}")
    public ResponseEntity<SubjectDTO>addStudentsToSubject(@PathVariable Long subjectId,@PathVariable Long studentId){
        return ResponseEntity.ok(subjectSerivce.addStudentsToSubject(subjectId,studentId));
    }
}