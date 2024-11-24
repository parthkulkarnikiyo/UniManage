package com.CollegeManagementSystem.CollegeManagementSystem.dto;

import com.CollegeManagementSystem.CollegeManagementSystem.entity.ProfessorEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long id;
    private  String title;
    private ProfessorEntity professor;
    private List<StudentEntity> subjects;
}