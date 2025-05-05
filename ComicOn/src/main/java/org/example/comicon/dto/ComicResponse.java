package org.example.comicon.dto;

import lombok.*;
import org.example.comicon.entities.Comic;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComicResponse {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String plot;
    private BigDecimal price;
    private LocalDateTime uploadTime;

    public static ComicResponse fromEntity(Comic comic) {
        return ComicResponse.builder()
                .id(comic.getId())
                .title(comic.getTitle())
                .author(comic.getAuthor())
                .genre(comic.getGenre())
                .plot(comic.getPlot())
                .price(comic.getPrice())
                .uploadTime(comic.getUploadTime())
                .build();
    }
}
