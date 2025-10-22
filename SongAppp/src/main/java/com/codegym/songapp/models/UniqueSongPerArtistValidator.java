package com.codegym.songapp.models;

import com.codegym.songapp.repositories.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class UniqueSongPerArtistValidator implements Validator {

    @Autowired
    ISongRepository songRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Song.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Song song = (Song) target;
        String title = song.getTitle();
        Long artistId = song.getArtist().getId();

        Optional<Song> optionalSong = songRepository.findByTitleIgnoreCaseAndArtistId(title, artistId);

        if (optionalSong.isPresent()) {
            errors.rejectValue("title", "", "Bài hát này đã tồn tại!");
        }
    }
}
