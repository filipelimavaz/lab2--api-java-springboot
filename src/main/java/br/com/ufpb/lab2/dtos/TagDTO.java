package br.com.ufpb.lab2.dtos;

import br.com.ufpb.lab2.entities.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagDTO {

    private Long id;
    private String name;

    public static TagDTO from (Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.id = tag.getId();
        tagDTO.name = tag.getName();
        return tagDTO;
    }
}
