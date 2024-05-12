package com.example.quiz1.entities;

import jakarta.persistence.*;
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
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String titre;
    @OneToMany
    private List<Question> questions;
    @OneToMany(mappedBy = "test" , fetch = FetchType.LAZY)
    private List<Detail> details = new ArrayList<>();
    @OneToMany(mappedBy = "test" , fetch = FetchType.LAZY)
    private List<Note> notes = new ArrayList<>();
}
