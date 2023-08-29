package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.DTO.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Override
    public Student createStudent(StudentDTO studentDTO) {
        Student student = Student.builder()
                .name(studentDTO.getName())
                .age(studentDTO.getAge())
                .schoolGroup(studentDTO.getSchoolGroup())
                .build();
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public StudentDTO updateStudent(Long id, String name, Integer age, String groupName, Set<String> courseNames) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Student with id " + id + " doesn't exist!"));

        student.checkAndSetName(name);
        student.checkAndSetAge(age);
        student.checkAndSetGroup(groupName);

        if (courseNames != null) {
            for (String courseName : courseNames) {
                Course cr = courseRepository.findCourseByName(courseName).orElseThrow(
                        () -> new IllegalStateException("Course with name " + courseName + " doesn't exist!")
                );
                student.getCourses().add(cr);
            }
        }
        studentRepository.save(student);
        return buildStudentDTO(student);
    }

    @Override
    public String getStudentCount () {
        return "We have " + studentRepository.count() + " students.";
    }

    @Override
    public Set<StudentDTO> getStudentsByGroup (String group){
        Set<Student> students = studentRepository.getStudentsBySchoolGroup(group);
        return students.stream()
                .map(s -> buildStudentDTO(s))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<StudentDTO> getStudentsByCourse(String courseName) {
        Course course = courseRepository.findCourseByName(courseName).orElseThrow(
                () -> new IllegalStateException("Course with name " + courseName + " doesn't exist!"));
        Set<Student> students = studentRepository.getStudentsByCourses(course);
        return students.stream()
                .map(s -> buildStudentDTO(s))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<StudentDTO> getStudentsByCourseAndOlderThan(String courseName, Integer age) {
        Course course = courseRepository.findCourseByName(courseName).orElseThrow(
                () -> new IllegalStateException("Course with name " + courseName + " doesn't exist!"));
        Set<Student> students = studentRepository.getStudentsByCoursesAndAgeGreaterThan(course, age);
        return students.stream()
                .map(s -> buildStudentDTO(s))
                .collect(Collectors.toSet());
    }

    @Override
    public String deleteStudent (Long id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Student with id " + id + " doesn't exist!"));
        student.getCourses().clear();
        studentRepository.delete(student);
        return "Student with id: " + id + " has been deleted!";
    }

    public StudentDTO buildStudentDTO(Student student) {
            return StudentDTO.builder()
                    .name(student.getName())
                    .age(student.getAge())
                    .schoolGroup(student.getSchoolGroup())
                    .courseNames(student.getCourses().stream()
                            .map(Course::getName)
                            .collect(Collectors.toSet()))
                    .build();
        }
}
