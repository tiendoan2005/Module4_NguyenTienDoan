package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class StorageService {

    private final Path uploadDir;

    public StorageService() {
        this.uploadDir = Path.of(System.getProperty("user.dir"), "uploads");
        try {
            Files.createDirectories(uploadDir); // tạo nếu chưa có
        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo thư mục uploads", e);
        }
    }

    public String saveFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        String fileName = file.getOriginalFilename();
        Path path = uploadDir.resolve(fileName).normalize().toAbsolutePath();
        Files.write(path, bytes,StandardOpenOption.CREATE);
        return "/uploads/" + fileName;
    }
}
