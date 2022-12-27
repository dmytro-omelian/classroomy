package com.main.classroomy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teachers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String secondName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

}