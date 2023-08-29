package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.DTO.PersonDTO;
import com.example.demo.model.DTO.TeacherDTO;
import com.example.demo.model.Person;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final StudentService studentService;

    private static final Course NO_COURSE = null;

    @Override
    public Teacher createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = Teacher.builder()
                .name(teacherDTO.getName())
                .age(teacherDTO.getAge())
                .schoolGroup(teacherDTO.getSchoolGroup())
                .build();
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Teacher with id " + id + " doesn't exist!"));

        teacher.checkAndSetName(teacherDTO.getName());
        teacher.checkAndSetAge(teacherDTO.getAge());
        teacher.checkAndSetGroup(teacherDTO.getSchoolGroup());

        if (teacherDTO.getCourseName() != null) {
            Course cr = courseRepository.findCourseByName(teacherDTO.getCourseName()).orElseThrow(
                    () -> new IllegalStateException("Course with name " + teacherDTO.getCourseName() + "doesn't exist!")
            );
            teacher.setCourse(cr);
        }

        return buildTeacherDTO(teacher);
    }

    @Override
    public String deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Teacher with id " + id + " doesn't exist!"));
        teacher.setCourse(NO_COURSE);
        teacherRepository.delete(teacher);
        return "Teacher with id: " + id + " has been deleted!";
    }

    @Override
    public String getTeachersCount() {
        return "We have " + teacherRepository.count() + " teachers.";
    }

    @Override
    public Set<PersonDTO> getPeopleByCourseAndGroup(String courseName, String group) {
        Course course = courseRepository.findCourseByName(courseName).orElseThrow(
                () -> new IllegalStateException("Course with name " + courseName + " doesn't exist!"));
        Set<Student> students = studentRepository.getStudentsByCoursesAndSchoolGroup(course, group);
        Set<Teacher> teacher = teacherRepository.getTeachersByCourseAndSchoolGroup(course, group);
        Set<PersonDTO> result = students.stream()
                .map(s -> studentService.buildStudentDTO(s))
                .collect(Collectors.toSet());
        Set<PersonDTO> teacherDTOs = teacher.stream()
                .map(t -> buildTeacherDTO(t))
                .collect(Collectors.toSet());
        result.addAll(teacherDTOs);
        return result;
    }

    private TeacherDTO buildTeacherDTO(Teacher teacher) {
        TeacherDTO teacherDTO= TeacherDTO.builder()
                .name(teacher.getName())
                .age(teacher.getAge())
                .schoolGroup(teacher.getSchoolGroup())
                .build();

        if (teacher.getCourse() != null) {
            teacherDTO.setCourseName(teacher.getCourse().getName());
        }
        return teacherDTO;
    }
}
