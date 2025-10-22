package com.example.songapp.repositories;

import com.example.songapp.model.Artist;
import com.example.songapp.model.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistRepository implements IArtistRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Artist> getAllArtists() {
        return entityManager.createQuery("from Artist", Artist.class).getResultList();
    }

    @Override
    public Artist getArtistById(int id) {
        return entityManager.find(Artist.class, id);
    }

    @Override
    public Artist createArtist(Artist artist) {
        return entityManager.merge(artist);
    }

    @Override
    public Artist updateArtist(int id, Artist artist) {
        return entityManager.merge(artist);
    }

    @Override
    public void deleteArtist(int id) {
        Artist artist = getArtistById(id);
        entityManager.remove(artist);
    }
}
