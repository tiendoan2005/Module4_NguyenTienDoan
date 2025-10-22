package com.example.songapp.repositories;

import com.example.songapp.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISongRepository {
    List<Song> getAllSongs();
    Song getSongById(int id);
    Song createSong(Song song);
    Song updateSong(int id, Song song);
    void deleteSong(int id);
}
