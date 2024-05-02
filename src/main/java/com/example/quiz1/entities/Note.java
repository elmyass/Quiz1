package com.example.quiz1.entities;

import com.example.quiz1.enums.NoteState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double score;
    private NoteState noteState = NoteState.New;
    @ManyToOne
    private Test test;
    @ManyToOne
    private User user;

}
