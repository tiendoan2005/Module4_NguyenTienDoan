package com.codegym.songapp.repositories;

import com.codegym.songapp.models.Artist;
import org.springframework.data.repository.CrudRepository;

public interface IArtistRepository extends CrudRepository<Artist, Long> {

}
