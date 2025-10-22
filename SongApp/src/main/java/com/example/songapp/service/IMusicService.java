package com.example.songapp.service;

import com.example.songapp.model.Artist;
import com.example.songapp.model.Song;

import java.util.List;

public interface IMusicService {
    //CRUD
    List<Song> getAllSongs();
    Song getSongById(int id);
    Song createSong(Song song);
    Song updateSong(int id, Song song);
    void deleteSong(int id);

    Artist getArtistById(int id);
    Artist createArtist(Artist artist);
    List<Song> getSongsByArtist(int id);
    List<Artist> getAllArtists();
}
