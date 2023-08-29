package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.CourseType;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.model.CourseType.Main;
import static com.example.demo.model.CourseType.Secondary;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public String getCoursesCount() {
        List<Course> courses = courseRepository.findAll();
        List<Course> mainCoursesCount = courses.stream().filter(c -> c.getType().equals(Main)).toList();
        List<Course> secondaryCoursesCount = courses.stream().filter(c -> c.getType().equals(Secondary)).toList();

        return "We have " + mainCoursesCount.size() + " main courses and " +
                            secondaryCoursesCount.size() + " secondary courses.";
    }

    @Override
    public String getCoursesCountByType(CourseType type) {
        List<Course> courses = courseRepository.findCoursesByType(type);
        return "We have " + courses.size() + " of type " + type;
    }

}
