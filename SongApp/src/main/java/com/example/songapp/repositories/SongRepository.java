package com.example.songapp.repositories;

import com.example.songapp.model.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class SongRepository implements ISongRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Song> getAllSongs() {
        String query = "Select s from Song s order by s.id asc";
        return entityManager.createQuery(query, Song.class).getResultList();
    }

    @Override
    public Song getSongById(int id) {
        String query = "Select s from Song s where s.id = :id";
        return entityManager.createQuery(query, Song.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Song createSong(Song song){
        entityManager.persist(song);
        return song;
    }

    @Override
    public Song updateSong(int id, Song song) {
        Song saveSong = getSongById(id);
        saveSong.setTitle(song.getTitle());
        saveSong.setArtist(song.getArtist());
        saveSong.setGenre(song.getGenre());
        saveSong.setFilePath(song.getFilePath());
        saveSong = entityManager.merge(saveSong);
        return saveSong;
    }

    @Override
    public void deleteSong(int id){
        entityManager.remove(getSongById(id));
    }
}
