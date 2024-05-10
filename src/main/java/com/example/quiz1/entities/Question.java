package com.example.quiz1.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Entity
@Table
@Getter
@Setter
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultylevel;
    private String category;
    @ManyToOne
    private Test test;

    public Question orElseThrow(Supplier<? extends RuntimeException> exceptionSupplier) {
        if (this == null) {
            throw exceptionSupplier.get();
        }
        return this;
    }
}
