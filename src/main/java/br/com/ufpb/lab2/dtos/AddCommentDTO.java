package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Comment;
import br.com.ufpb.lab2.entities.Course;
import lombok.*;

import java.util.List;

@Data
public class AddCommentDTO {

    private Long id;
    private String name;
    List<Comment> comments;

    public static AddCommentDTO from (Course course) {
        AddCommentDTO addCommentDTO = new AddCommentDTO();
        addCommentDTO.id = course.getId();
        addCommentDTO.name = course.getName();
        addCommentDTO.comments = course.getCommentList();
        return addCommentDTO;
    }
}
