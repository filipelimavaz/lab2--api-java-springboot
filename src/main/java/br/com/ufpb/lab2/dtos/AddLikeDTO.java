package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Course;
import lombok.*;

@Data
public class AddLikeDTO {

    private Long id;
    private String name;
    private int likes;

    public static AddLikeDTO from(Course course) {
        AddLikeDTO addLikeDTO = new AddLikeDTO();
        addLikeDTO.id = course.getId();
        addLikeDTO.name = course.getName();
        addLikeDTO.likes = course.getLikes();
        return addLikeDTO;
    }
}
