package com.example.demo.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO extends PersonDTO {
    @Nullable
    private String courseName;
}
