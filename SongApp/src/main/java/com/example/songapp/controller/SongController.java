package com.example.songapp.controller;


import com.example.songapp.WebConfig;
import com.example.songapp.model.Song;
import com.example.songapp.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private IMusicService songService;

    @GetMapping
    public String list (Model model, @ModelAttribute("message") String message) {
        model.addAttribute("songs", songService.getAllSongs());
        return "song/list";
    }

    @GetMapping("/new")
    public String createSong (Model model) {
        model.addAttribute("song", new Song());
        return "song/form";
    }

    @PostMapping("/new")
    public String doCreate(@ModelAttribute Song song,
                           @RequestParam("file") MultipartFile file) throws IOException {

        // Thư mục vật lý thực tế (trùng với WebConfig)
        String uploadDir = WebConfig.SONG_FOLDER;
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // Lưu file vào thư mục
        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.isEmpty()) {
            File destination = new File(uploadDir + "/" + fileName);
            file.transferTo(destination);
            song.setFilePath("/songs/" + fileName); // đường dẫn truy cập qua browser
        }

        songService.createSong(song);
        return "redirect:/song";
    }

}
