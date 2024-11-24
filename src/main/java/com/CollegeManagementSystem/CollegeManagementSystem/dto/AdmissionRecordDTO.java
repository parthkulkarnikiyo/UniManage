package com.CollegeManagementSystem.CollegeManagementSystem.dto;

import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRecordDTO {
    private Long id;
    private Integer fees;
    private StudentEntity student;
}
