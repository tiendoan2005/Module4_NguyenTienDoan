package com.codegym.songapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên nghệ sĩ không được trống")
    @Size(max = 150)
    private String name;

    // thiết lập mối quan hệ giữa Bài Hát - Nghệ Sĩ.
    // n-n (Many-Many) --> chính xác @ManyToMany
    // Demo 1-n (One-Many) --> đơn giản

    @OneToMany(mappedBy = "artist", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Song> songs =  new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
