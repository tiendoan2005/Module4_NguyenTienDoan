package com.example.songapp.repositories;

import com.example.songapp.model.Artist;
import com.example.songapp.model.Song;

import java.util.List;

public interface IArtistRepository {
    List<Artist> getAllArtists();
    Artist getArtistById(int id);
    Artist createArtist(Artist artist);
    Artist updateArtist(int id, Artist artist);
    void deleteArtist(int id);
}
