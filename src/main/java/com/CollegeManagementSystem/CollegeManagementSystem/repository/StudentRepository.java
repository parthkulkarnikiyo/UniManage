package com.CollegeManagementSystem.CollegeManagementSystem.repository;

import com.CollegeManagementSystem.CollegeManagementSystem.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
}
