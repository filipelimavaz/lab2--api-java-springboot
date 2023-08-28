package br.com.ufpb.lab2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tagList")
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("tagList")
    private Set<Course> courseList = new HashSet<>();
}

