package org.example.comicon.controller;

import org.example.comicon.dto.ComicRequest;
import org.example.comicon.dto.ComicResponse;
import org.example.comicon.dto.ComicUpdateRequest;
import org.example.comicon.entities.Comic;
import org.example.comicon.service.ComicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comics")
public class ComicController {

    private final ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping
    public List<ComicResponse> getComics(@RequestParam(required = false, name = "sortBy") String sortBy) {
        return comicService.getAllComicsSorted(sortBy).stream()
                .map(ComicResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/genre/{genre}")
    public List<ComicResponse> getComicsByGenre(
            @PathVariable String genre,
            @RequestParam(required = false, name = "sortBy") String sortBy) {
        return comicService.getComicsByGenreSorted(genre, sortBy).stream()
                .map(ComicResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ComicResponse> addComic(@RequestBody ComicRequest comicRequest) {
        Comic comic = Comic.builder()
                .title(comicRequest.getTitle())
                .author(comicRequest.getAuthor())
                .genre(comicRequest.getGenre())
                .plot(comicRequest.getPlot())
                .price(comicRequest.getPrice())
                .uploadTime(LocalDateTime.now())
                .build();

        Comic saved = comicService.addComic(comic);
        return new ResponseEntity<>(ComicResponse.fromEntity(saved), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/buy")
    public String buyComic(@PathVariable Long id) {
        return comicService.buyComic(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ComicResponse> updateComic(
            @PathVariable Long id,
            @RequestBody ComicUpdateRequest updateRequest) {

        Comic updated = comicService.updateComic(id, updateRequest);
        return ResponseEntity.ok(ComicResponse.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComic(@PathVariable Long id) {
        comicService.deleteComic(id);
        return ResponseEntity.noContent().build();
    }


}
