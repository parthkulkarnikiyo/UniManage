package com.CollegeManagementSystem.CollegeManagementSystem.dto;

import com.CollegeManagementSystem.CollegeManagementSystem.entity.ProfessorEntity;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.SubjectEntity;
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
public class StudentDTO {

    private Long id;

    private String name;

    private List<ProfessorEntity> professors;

    private List<SubjectEntity>subjects;
}
