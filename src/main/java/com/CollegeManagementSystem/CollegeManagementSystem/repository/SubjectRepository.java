package com.CollegeManagementSystem.CollegeManagementSystem.repository;

import com.CollegeManagementSystem.CollegeManagementSystem.dto.SubjectDTO;
import com.CollegeManagementSystem.CollegeManagementSystem.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity,Long> {
}
