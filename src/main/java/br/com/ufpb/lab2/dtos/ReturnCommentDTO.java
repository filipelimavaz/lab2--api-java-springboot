package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Comment;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReturnCommentDTO {

    private List<CommentDTO> commentDTOList;

    public static List<CommentDTO> returnCommentDTO(List<Comment> commentList) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setComment(comment.getText());
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
