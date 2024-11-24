package com.CollegeManagementSystem.CollegeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Subjects")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor")
    private ProfessorEntity professor;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Student_Subject_Mapping",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<StudentEntity> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

}