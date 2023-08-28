package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Course;
import lombok.*;

@Data
public class ReturnCourseDTO {

    private Long id;
    private String name;

    public static ReturnCourseDTO from (Course course) {
        ReturnCourseDTO returnCourseDTO = new ReturnCourseDTO();
        returnCourseDTO.id = course.getId();
        returnCourseDTO.name = course.getName();
        return returnCourseDTO;
    }
}
