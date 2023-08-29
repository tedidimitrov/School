package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

    Optional<Course> findCourseByName(String name);

    List<Course> findCoursesByType(CourseType type);
}
