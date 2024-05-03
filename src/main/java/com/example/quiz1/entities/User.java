package com.example.quiz1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "ERROR FIRSTNAME")
    private String firstName;
    private String lastName;
    @NotBlank (message="ERROR EMAIL")
    @Email //Email it s a must
    private String email;
    @OneToMany (mappedBy = "user" , fetch = FetchType.LAZY)
    private List<Note> notes = new ArrayList<>();


}
