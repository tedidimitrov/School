package com.example.demo.controller;

import com.example.demo.model.DTO.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Student> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.createStudent(studentDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long id,
                                                 @RequestBody StudentDTO studentDTO
    ) {
        return new ResponseEntity<>(studentService.updateStudent(id, studentDTO.getName(),
                studentDTO.getAge(), studentDTO.getSchoolGroup(), studentDTO.getCourseNames()), HttpStatus.OK);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<String> getStudentCount () {
        return new ResponseEntity<>(studentService.getStudentCount(), HttpStatus.OK);
    }

    @GetMapping(path = "/group/{group}")
    public ResponseEntity<Set<StudentDTO>> getStudentsByGroup (@PathVariable("group") String group) {
        return new ResponseEntity<>(studentService.getStudentsByGroup(group), HttpStatus.OK);
    }

    @GetMapping(path = "/course/{course}")
    public ResponseEntity<Set<StudentDTO>> getStudentsByCourse (@PathVariable("course") String courseName) {
        return new ResponseEntity<>(studentService.getStudentsByCourse(courseName), HttpStatus.OK);
    }

    @GetMapping(path = "/course/{course}/age/{age}")
    public ResponseEntity<Set<StudentDTO>> getStudentsByCourseAndOlderThan (@PathVariable("course") String courseName,
                                                                @PathVariable("age") Integer age) {
        return new ResponseEntity<>(studentService.getStudentsByCourseAndOlderThan(courseName, age), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public String deleteTeacher(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }
}
