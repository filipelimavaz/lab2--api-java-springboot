package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Course;
import lombok.*;

@Data
public class AddRateDTO {

    private Long id;
    private String name;
    private Double media;

    public static AddRateDTO from (Course course) {
        AddRateDTO addRateDTO = new AddRateDTO();
        addRateDTO.id = course.getId();
        addRateDTO.name = course.getName();
        addRateDTO.media = course.calculaMedia();
        return addRateDTO;
    }
}
