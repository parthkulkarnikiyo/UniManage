package com.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.ProfessorDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.services.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
@Slf4j
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorDTO> addProfessor(@RequestBody ProfessorDTO professorDTO) {
        log.trace("Entering addProfessor with ProfessorDTO: {}", professorDTO);
        ProfessorDTO createdProfessor = professorService.addProfessor(professorDTO);
        log.info("Professor added successfully with id: {}", createdProfessor.getId());
        return ResponseEntity.ok(createdProfessor);
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable Long professorId) {
        log.trace("Entering getProfessorById with professorId: {}", professorId);
        ProfessorDTO professorDTO = professorService.getProfessorById(professorId);
        log.info("Successfully fetched ProfessorDTO for professorId: {}", professorId);
        return new ResponseEntity<>(professorDTO, HttpStatus.OK);
    }

    @PatchMapping("/addStudent/studentId/{studentId}/professorId/{professorId}")
    public ResponseEntity<ProfessorDTO> addStudent(@PathVariable Long studentId,
                                                   @PathVariable Long professorId) {
        log.trace("Entering addStudent with studentId: {} and professorId: {}", studentId, professorId);
        ProfessorDTO updatedProfessor = professorService.assignProfessorToStudent(studentId, professorId);
        log.info("Student with id: {} successfully assigned to Professor with id: {}", studentId, professorId);
        return new ResponseEntity<>(updatedProfessor, HttpStatus.OK);
    }
}
