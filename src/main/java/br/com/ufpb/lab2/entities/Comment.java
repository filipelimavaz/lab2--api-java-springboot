package br.com.ufpb.lab2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date = LocalDate.now();
    private String text;
    private boolean removed;

    @ManyToOne
    @JsonIgnoreProperties("commentList")
    private Course course;
}
