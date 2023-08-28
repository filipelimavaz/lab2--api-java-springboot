package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Comment;
import br.com.ufpb.lab2.entities.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {

    private Long id;
    private String name;
    private Double average;
    private int likes;
    private List<Comment> commentList;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.average = course.calculaMedia();
        this.likes = course.getLikes();
        this.commentList = course.getCommentList();
    }

    public int comparaMedia(CourseDTO courseDTO) {
        if(this.average > courseDTO.getAverage()) {
            return -1;
        } else if(this.average.equals(courseDTO.getAverage())) {
            return 0;
        } else {
            return 1;
        }
    }

    public int comparaLike(CourseDTO courseDTO) {
        if (this.likes > courseDTO.getLikes()) {
            return -1;
        } else if (this.likes < courseDTO.getLikes()) {
            return 1;
        } else {
            return 0;
        }
    }

}
