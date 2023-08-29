package com.example.demo.service;

import com.example.demo.model.DTO.PersonDTO;
import com.example.demo.model.DTO.TeacherDTO;
import com.example.demo.model.Person;
import com.example.demo.model.Teacher;

import java.util.Set;

public interface TeacherService {

    Teacher createTeacher(TeacherDTO teacherDTO);

    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO);

    String deleteTeacher(Long id);

    String getTeachersCount();

    Set<PersonDTO> getPeopleByCourseAndGroup(String courseName, String group);
}
