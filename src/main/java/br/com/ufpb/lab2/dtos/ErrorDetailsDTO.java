package br.com.ufpb.lab2.dtos;

import lombok.Data;

@Data
public class ErrorDetailsDTO {
    private int status;
    private String type;
    private String title;
    private String detail;
}
