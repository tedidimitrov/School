package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Set<Teacher> getTeachersByCourseAndSchoolGroup(Course course, String group);
}
