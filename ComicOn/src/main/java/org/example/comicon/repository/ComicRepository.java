package org.example.comicon.repository;

import org.example.comicon.entities.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComicRepository extends JpaRepository<Comic, Long> {

    List<Comic> findAllByOrderByUploadTimeDesc();

    List<Comic> findAllByOrderByPriceDesc();

    List<Comic> findByGenreIgnoreCaseOrderByUploadTimeDesc(String genre);

    List<Comic> findByGenreIgnoreCaseOrderByPriceDesc(String genre);
    List<Comic> findByGenre(String genre);
}
