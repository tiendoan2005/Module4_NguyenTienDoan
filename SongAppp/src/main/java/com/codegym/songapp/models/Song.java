package com.codegym.songapp.models;

import com.codegym.songapp.models.validation.Create;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên bài hát không được trống")
    @Size(max = 200)
//    @Min(value = 5,message = "Tên bài hát tối thiểu 5 ký tự!")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @NotBlank(message = "Thể loại không được trống", groups = {Default.class, Create.class})
    @Size(max = 100)
    private String genre;

    @Column(name = "file_path", nullable = true)
    private String filePath; // table (MySQL) --> tạo column: file_path

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
