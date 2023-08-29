package com.example.demo.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;
    @Nonnull
    @NotNull
    @Column(unique=true)
    private String name;
    @Nonnull
    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseType type;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private Set<Teacher> teachers;
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
