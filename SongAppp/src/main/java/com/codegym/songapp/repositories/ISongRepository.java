package com.codegym.songapp.repositories;

import com.codegym.songapp.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ISongRepository extends
        CrudRepository<Song, Long>,
        PagingAndSortingRepository<Song, Long> {
    Optional<Song> findByTitleIgnoreCaseAndArtistId(String title, long songId);
}
