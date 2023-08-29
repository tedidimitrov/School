package com.example.demo.controller;

import com.example.demo.model.DTO.PersonDTO;
import com.example.demo.model.DTO.StudentDTO;
import com.example.demo.model.DTO.TeacherDTO;
import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        return new ResponseEntity<>(teacherService.createTeacher(teacherDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("id") Long id,
                                               @RequestBody TeacherDTO teacherDTO
    ) {
        return new ResponseEntity<>(teacherService.updateTeacher(id, teacherDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<String> getStudentCount () {
        return new ResponseEntity<>(teacherService.getTeachersCount(), HttpStatus.OK);
    }

    @GetMapping(path = "/people/course/{course}/group/{group}")
    public ResponseEntity<Set<PersonDTO>> getPeopleByCourseAndGroup (@PathVariable("course") String course,
                                                                     @PathVariable("group") String group) {
        return new ResponseEntity<>(teacherService.getPeopleByCourseAndGroup(course, group), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public String deleteTeacher(@PathVariable("id") Long id) {
        return teacherService.deleteTeacher(id);
    }
}
