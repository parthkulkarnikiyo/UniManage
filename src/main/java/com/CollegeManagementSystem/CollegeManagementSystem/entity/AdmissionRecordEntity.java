package com.CollegeManagementSystem.CollegeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "AdmissionRecords")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer fees;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Student_Name")
    private StudentEntity student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmissionRecordEntity that = (AdmissionRecordEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fees, that.fees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fees);
    }
}