package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Set<Student> getStudentsBySchoolGroup(String group);

    Set<Student> getStudentsByCourses(Course course);

    Set<Student> getStudentsByCoursesAndAgeGreaterThan(Course course, Integer age);

    Set<Student> getStudentsByCoursesAndSchoolGroup(Course course, String group);
}
