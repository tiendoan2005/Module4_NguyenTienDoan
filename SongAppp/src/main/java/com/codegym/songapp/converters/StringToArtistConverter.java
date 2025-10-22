package com.codegym.songapp.converters;

import com.codegym.songapp.models.Artist;
import com.codegym.songapp.repositories.IArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component // Khai báo converter là 1 bean trong IoC
public class StringToArtistConverter implements Converter<String, Artist> {

    @Autowired
    private IArtistRepository artistRepository;

    @Override
    public Artist convert(String source) {
        Long artistId = Long.parseLong(source); // chuyển sang ID (Long)
        Artist artist = artistRepository.findById(artistId).orElse(null); // sử dụng IArtistRepository --> gọi findById() --> Artist
        return artist;
    }
}