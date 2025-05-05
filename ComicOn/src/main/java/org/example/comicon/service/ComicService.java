package org.example.comicon.service;

import org.example.comicon.dto.ComicUpdateRequest;
import org.example.comicon.entities.Comic;
import org.example.comicon.repository.ComicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicService {

    private final ComicRepository comicRepository;

    public ComicService(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }

    public List<Comic> getAllComics() {
        return comicRepository.findAll();
    }

    public List<Comic> getComicsByGenre(String genre) {
        return comicRepository.findByGenre(genre);
    }

    public Comic addComic(Comic comic) {
        comic.setUploadTime(java.time.LocalDateTime.now());
        return comicRepository.save(comic);
    }

    public String buyComic(Long id) {
        return comicRepository.findById(id)
                .map(comic -> "Comic purchased: " + comic.getTitle() + " ($" + comic.getPrice() + ")")
                .orElse("Comic not found!");
    }

    public List<Comic> getAllComicsSorted(String sortBy) {
        if ("price".equalsIgnoreCase(sortBy)) {
            return comicRepository.findAllByOrderByPriceDesc();
        }
        return comicRepository.findAllByOrderByUploadTimeDesc();
    }

    public List<Comic> getComicsByGenreSorted(String genre, String sortBy) {
        if ("price".equalsIgnoreCase(sortBy)) {
            return comicRepository.findByGenreIgnoreCaseOrderByPriceDesc(genre);
        }
        return comicRepository.findByGenreIgnoreCaseOrderByUploadTimeDesc(genre);
    }

    public Comic updateComic(Long id, ComicUpdateRequest updateRequest) {
        Comic comic = comicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comic not found with id: " + id));

        if (updateRequest.getTitle() != null) comic.setTitle(updateRequest.getTitle());
        if (updateRequest.getAuthor() != null) comic.setAuthor(updateRequest.getAuthor());
        if (updateRequest.getGenre() != null) comic.setGenre(updateRequest.getGenre());
        if (updateRequest.getPlot() != null) comic.setPlot(updateRequest.getPlot());
        if (updateRequest.getPrice() != null) comic.setPrice(updateRequest.getPrice());

        return comicRepository.save(comic);
    }

    public void deleteComic(Long id) {
        if (!comicRepository.existsById(id)) {
            throw new RuntimeException("Comic not found with id: " + id);
        }
        comicRepository.deleteById(id);
    }


}
