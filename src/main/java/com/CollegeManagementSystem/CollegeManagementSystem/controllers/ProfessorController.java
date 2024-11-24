package com.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.ProfessorDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.dto.StudentDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorDTO> addProfessor(@RequestBody ProfessorDTO professorDTO){
        ProfessorDTO professorDTO1=professorService.addProfessor(professorDTO);
        return ResponseEntity.ok(professorDTO1);
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable Long professorId){
        return new ResponseEntity<>(professorService.getProfessorById(professorId), HttpStatus.OK);
    }

    @PatchMapping("/addStudent/studentId/{studentId}/professorId/{professorId}")
    public ResponseEntity<ProfessorDTO>addStudent(@PathVariable Long studentId,
                                                  @PathVariable Long professorId){
        ProfessorDTO professorDTO=professorService.assignProfessorToStudent(studentId,professorId);
        return new ResponseEntity<>(professorDTO,HttpStatus.OK);
    }

}