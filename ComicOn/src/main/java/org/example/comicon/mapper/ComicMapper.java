package org.example.comicon.mapper;

import org.example.comicon.dto.ComicRequest;
import org.example.comicon.dto.ComicResponse;
import org.example.comicon.entities.Comic;

import java.time.LocalDateTime;

public class ComicMapper {

    public static Comic toEntity(ComicRequest dto) {
        return Comic.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .genre(dto.getGenre())
                .plot(dto.getPlot())
                .price(dto.getPrice())
                .uploadTime(LocalDateTime.now())
                .build();
    }

    public static ComicResponse toResponse(Comic comic) {
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
