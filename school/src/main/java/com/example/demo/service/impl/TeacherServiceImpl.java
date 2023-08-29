package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Teacher;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public Teacher updateTeacher(Long id, String name, Integer age, String courseName) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Teacher with id " + id + " doesn't exist!"));
        if (name != null && name.length() > 0) {
            teacher.setName(name);
        }

        if (age != null && age > 0) {
            teacher.setAge(age);
        }

        if (courseName != null) {
            Course cr = courseRepository.findCourseByName(courseName).orElseThrow(
                    () -> new IllegalStateException("Course with name " + courseName + "doesn't exist!")
            );
            teacher.setCourse(cr);
        }
        return teacher;
    }

    @Override
    public String deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Teacher with id " + id + " doesn't exist!"));
        teacherRepository.delete(teacher);
        return "Teacher with id: " + id + " has been deleted!";
    }
}
