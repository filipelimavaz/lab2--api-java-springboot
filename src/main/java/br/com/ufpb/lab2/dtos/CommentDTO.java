package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Comment;
import lombok.Data;

@Data
public class CommentDTO {

    private String comment;

    public static CommentDTO from (Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.comment = comment.getText();
        return commentDTO;
    }
}
