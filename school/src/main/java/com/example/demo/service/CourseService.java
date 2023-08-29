package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.CourseType;

public interface CourseService {

    Course createCourse(Course course);

    String getCoursesCount();

    String getCoursesCountByType(CourseType type);

}
