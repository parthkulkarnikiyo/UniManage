package com.CollegeManagementSystem.CollegeManagementSystem.dto;

import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.SubjectEntity;
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
public class ProfessorDTO {

    private Long id;
    private  String title;
    private List<SubjectEntity> subjects;
    private List<StudentEntity> students;
}
