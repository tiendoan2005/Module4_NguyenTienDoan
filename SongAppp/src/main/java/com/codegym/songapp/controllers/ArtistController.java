package com.codegym.songapp.controllers;

import com.codegym.songapp.models.Artist;
import com.codegym.songapp.models.PhoneNumber;
import com.codegym.songapp.services.IMusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("artists")
public class ArtistController {

    @Autowired
    private IMusicService musicService;

    @GetMapping
    public String getArtists(Model model) {
        List<Artist> artists = musicService.getArtists();
        model.addAttribute("artists", artists);
        return "artist/list";
    }

    @GetMapping("/new")
    public String getNewArtist(Model model) {
        model.addAttribute("artist", new Artist());
        return "artist/form";
    }

    @PostMapping("/new")
    public String saveArtist(Artist artist, Model model) {
        musicService.createArtist(artist);
        model.addAttribute("message", "Artist has been saved successfully");
        return "redirect:/artists";
    }

    @GetMapping("/phone")
    public String getPhone(Model model) {
        model.addAttribute("phoneNumber", new PhoneNumber());
        return "phone";
    }

    @PostMapping("/phone")
    public String savePhone(@Valid @ModelAttribute("phoneNumber") PhoneNumber phoneNumber, Model model, BindingResult bindingResult) {
        new PhoneNumber().validate(phoneNumber, bindingResult);
        if (bindingResult.hasErrors()) {
            return "phone";
        }
        return "redirect:/artists";
    }
}
