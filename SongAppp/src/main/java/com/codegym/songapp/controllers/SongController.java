package com.codegym.songapp.controllers;

import com.codegym.songapp.models.Song;
import com.codegym.songapp.models.UniqueSongPerArtistValidator;
import com.codegym.songapp.models.validation.Create;
import com.codegym.songapp.services.IMusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private IMusicService musicService;

    @GetMapping
    public String list(Model model, @ModelAttribute("message") String message, Pageable pageable) {
        Page<Song> songs = musicService.getAll(pageable);

        model.addAttribute("songs", songs);
        return "song/list";
    }

    @GetMapping("/{id}")
    public String getSongById(@ModelAttribute("id") Song song, Model model) {
        model.addAttribute("song", song);
        return "song/detail";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("artists", musicService.getArtists());
        return "song/form";
    }

    @Autowired
    UniqueSongPerArtistValidator uniqueSongPerArtistValidator;

    @PostMapping("/new")
    public String doCreate(
            @RequestParam(value = "song", required = false) MultipartFile file, //Lấy tham số tiếp theo (BindingResult), gán thông tin sau xác minh
            @Valid @ModelAttribute("song") Song song,
            BindingResult bindingResult, // kiểm tra lỗi
            Model model) {

        uniqueSongPerArtistValidator.validate(song, bindingResult); //Kiểm tra bài hát của nghệ sĩ đã tồn tại chưa

        if (bindingResult.hasErrors()) {
            model.addAttribute("artists", musicService.getArtists());
            return "song/form";
        }
        musicService.create(song);
        // chưa xử lý thêm bài hát (+ upload file bài hát)
        // song.setFilePath(file.getOriginalFilename());
        // StorageService --> upload file (mp3. ogg, .wav,...)
        return "redirect:/song";

    }
}