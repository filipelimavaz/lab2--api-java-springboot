package br.com.ufpb.lab2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private List<Double> rateList = new ArrayList<>();
    private int likes;

    @OneToMany
    private List<Comment> commentList = new ArrayList<>();

    @ManyToMany
    @EqualsAndHashCode.Exclude
    private List<Tag> tagList = new ArrayList<>();

    public void adicionaLike() {
        this.likes++;
    }

    public void adicionaNota(Double nota) {
        this.rateList.add(nota);
    }

    public void adicionaComentario(Comment comment) {
        this.commentList.add(comment);
    }

    public Double calculaMedia() {
        if (rateList == null || rateList.isEmpty()) {
            return 0.0;
        } else {
            Double aux = 0.0;
            for (Double nota : rateList) {
                if (nota != null) {
                    aux += nota;
                }
            }
            return aux/rateList.size();
        }
    }
}
