package org.example.comicon.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComicUpdateRequest {
    private String title;
    private String author;
    private String genre;
    private String plot;
    private BigDecimal price;
}
