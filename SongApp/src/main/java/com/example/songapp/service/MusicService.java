package com.example.songapp.service;

import com.example.songapp.model.Artist;
import com.example.songapp.model.Song;
import com.example.songapp.repositories.ArtistRepository;
import com.example.songapp.repositories.ISongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService implements IMusicService {

    @Autowired
     ISongRepository songRepository;
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Song> getAllSongs() {
        return songRepository.getAllSongs();
    }

    @Override
    public Song getSongById(int id) {
        return songRepository.getSongById(id);
    }

    @Override
    public Song createSong(Song song){
        return songRepository.createSong(song);
    }

    @Override
    public Song updateSong(int id, Song song) {
        return songRepository.updateSong(id, song);
    }

    @Override
    public void deleteSong(int id){
        songRepository.deleteSong(id);
    }

    @Override
    public Artist getArtistById(int id) {
        return artistRepository.getArtistById(id);
    }

    @Override
    public Artist createArtist(Artist artist) {
        return artistRepository.createArtist(artist);
    }

    @Override
    public List<Song> getSongsByArtist(int id) {
        return List.of();
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.getAllArtists();
    }
}
