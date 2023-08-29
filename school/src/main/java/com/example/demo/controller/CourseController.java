package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.CourseType;
import com.example.demo.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Course> createCourse(@RequestBody @Valid Course course) {
        return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.CREATED);
    }

    @GetMapping(path = "/total")
    public ResponseEntity<String> getCoursesCount() {
        return new ResponseEntity<>(courseService.getCoursesCount(), HttpStatus.OK);
    }

    @GetMapping(path = "{type}")
    public ResponseEntity<String> getCoursesCountByType(@PathVariable("type") CourseType type) {
        return new ResponseEntity<>(courseService.getCoursesCountByType(type), HttpStatus.OK);
    }
}
