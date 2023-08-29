package com.example.demo.model;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nonnull
    @NotNull
    private String name;
    @Nonnull
    @NotNull
    @Min(value = 1, message = "Age must be positive")
    private Integer age;
    @Nonnull
    private String schoolGroup;

    public void checkAndSetName(String name) {
        if (name != null && name.length() > 0) {
            this.name = name;
        }
    }

    public void checkAndSetAge(Integer age) {
        if (age != null && age > 0) {
            this.age = age;
        }
    }

    public void checkAndSetGroup(String groupName) {
        if (groupName != null && groupName.length() > 0) {
            this.schoolGroup = groupName;
        }
    }

}
