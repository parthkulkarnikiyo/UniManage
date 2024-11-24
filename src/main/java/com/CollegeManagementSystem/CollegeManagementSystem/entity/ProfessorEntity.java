package com.CollegeManagementSystem.CollegeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.LongNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Professor")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private  String title;

   @OneToMany(mappedBy = "professor")
   @JsonIgnore
   private List<SubjectEntity>subjects;


   @ManyToMany(cascade =CascadeType.ALL)
   @JoinTable(
           name = "Professor_Student_Mapping",
           joinColumns=@JoinColumn(name = "professor_id"),
           inverseJoinColumns = @JoinColumn(name = "student_id")
   )
   List<StudentEntity>students;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ProfessorEntity that = (ProfessorEntity) o;
      return Objects.equals(id, that.id) && Objects.equals(title, that.title);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, title);
   }
}
