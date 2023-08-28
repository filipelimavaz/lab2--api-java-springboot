package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Course;
import br.com.ufpb.lab2.entities.Tag;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReturnCourseTagDTO {

    private Long id;
    private String name;
    private List<TagDTO> tagList = new ArrayList<>();

    public static ReturnCourseTagDTO from (Course course) {
        ReturnCourseTagDTO retornaDisciplinaTag = new ReturnCourseTagDTO();
        retornaDisciplinaTag.id = course.getId();
        retornaDisciplinaTag.name = course.getName();
        List<TagDTO> tagDTOs = new ArrayList<>();
        for (Tag tag : course.getTagList()) {
            tagDTOs.add(TagDTO.from(tag));
        }
        retornaDisciplinaTag.tagList = tagDTOs;
        return retornaDisciplinaTag;
    }
}
