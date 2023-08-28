package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Comment;
import br.com.ufpb.lab2.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecificCourseDTO {

    private Long id;
    private String name;
    private Double average;
    private int likes;
    List<CommentDTO> comments;

    public static SpecificCourseDTO from (Course course) {
        SpecificCourseDTO specificCourseDTO = new SpecificCourseDTO();
        specificCourseDTO.id = course.getId();
        specificCourseDTO.name = course.getName();
        specificCourseDTO.average = course.calculaMedia();
        specificCourseDTO.likes = course.getLikes();
        specificCourseDTO.comments = convertComentariosToDTO(course.getCommentList());
        return specificCourseDTO;
    }

    public static List<CommentDTO> convertComentariosToDTO(List<Comment> commentList) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setComment(comment.getText());
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
