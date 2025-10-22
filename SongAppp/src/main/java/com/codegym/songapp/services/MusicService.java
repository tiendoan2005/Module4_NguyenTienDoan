package com.codegym.songapp.services;

import com.codegym.songapp.models.Artist;
import com.codegym.songapp.models.Song;
import com.codegym.songapp.repositories.IArtistRepository;
import com.codegym.songapp.repositories.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService implements IMusicService {

    @Autowired
    ISongRepository songRepository;

    @Autowired
    IArtistRepository artistRepository;

    @Override
    public Page<Song> getAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public Song getById(int id) {
        Optional<Song> songOptional = songRepository.findById(Long.valueOf(id));
        return songOptional.orElse(null);
    }

    @Override
    public Song create(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song update(int id, Song song) {
        song.setId(Long.valueOf(id));
        return songRepository.save(song);
    }

    @Override
    public void delete(int id) {
        songRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public Artist getArtistById(int id) {
        Optional<Artist> artistOptional = artistRepository.findById(Long.valueOf(id));
        return artistOptional.orElse(null);
    }

    @Override
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public List<Song> getSongsByArtistId(int id) {
        return List.of();
    }

    @Override
    public List<Artist> getArtists() {
        return (List<Artist>) artistRepository.findAll();
    }


}
