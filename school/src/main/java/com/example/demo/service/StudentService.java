package com.example.demo.service;

import com.example.demo.model.DTO.StudentDTO;
import com.example.demo.model.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {

    Student createStudent(StudentDTO student);

    StudentDTO updateStudent(Long id, String name, Integer age, String groupNames, Set<String> courseName);

    String deleteStudent(Long id);

    String getStudentCount();

    Set<StudentDTO> getStudentsByGroup(String group);

    Set<StudentDTO> getStudentsByCourse(String courseName);

    Set<StudentDTO> getStudentsByCourseAndOlderThan(String courseName, Integer age);

    StudentDTO buildStudentDTO(Student student);
}
