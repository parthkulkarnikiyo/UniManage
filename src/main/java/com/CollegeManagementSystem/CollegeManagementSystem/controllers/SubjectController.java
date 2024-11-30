package com.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.SubjectDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.services.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
@Slf4j
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO> getSubjectDetailsBySubjectId(@PathVariable Long subjectId) {
        log.trace("Entering getSubjectDetailsBySubjectId with subjectId: {}", subjectId);
        SubjectDTO subjectDTO = subjectService.getSubjectDetailsBySubjectId(subjectId);
        log.info("Successfully fetched SubjectDTO for subjectId: {}", subjectId);
        return ResponseEntity.ok(subjectDTO);
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> addSubjectDetails(@RequestBody SubjectDTO subjectDTO) {
        log.trace("Entering addSubjectDetails with SubjectDTO: {}", subjectDTO);
        SubjectDTO createdSubject = subjectService.addSubjectDetails(subjectDTO);
        log.info("Subject added successfully with id: {}", createdSubject.getId());
        return ResponseEntity.ok(createdSubject);
    }

    @PatchMapping("/assignSubjectToProfessor/subjectId/{subjectId}/professor/{professorId}")
    public ResponseEntity<SubjectDTO> assignSubjectToProfessor(@PathVariable Long subjectId, @PathVariable Long professorId) {
        log.trace("Entering assignSubjectToProfessor with subjectId: {} and professorId: {}", subjectId, professorId);
        SubjectDTO updatedSubject = subjectService.assignSubjectToProfessor(subjectId, professorId);
        log.info("Successfully assigned subjectId: {} to professorId: {}", subjectId, professorId);
        return ResponseEntity.ok(updatedSubject);
    }

    @PatchMapping("/addStudentsInSubject/subjectId/{subjectId}/studentId/{studentId}")
    public ResponseEntity<SubjectDTO> addStudentsToSubject(@PathVariable Long subjectId, @PathVariable Long studentId) {
        log.trace("Entering addStudentsToSubject with subjectId: {} and studentId: {}", subjectId, studentId);
        SubjectDTO updatedSubject = subjectService.addStudentsToSubject(subjectId, studentId);
        log.info("Successfully added studentId: {} to subjectId: {}", studentId, subjectId);
        return ResponseEntity.ok(updatedSubject);
    }
}
