package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @NotBlank(message = "Không được để trống MSV")
    @Size(min = 3, max = 10, message = "Mã sinh viên từ 3 - 10 ký tự!")
    private String id;

    @NotBlank(message = "Không được để trống tên sinh viên")
    private String name;

    @Min(value = 0, message = "Điểm không được nhỏ hơn 0")
    @Max(value = 10, message = "Điểm không được cao hơn 10")
    private float garden;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    String avatar; //Lưu đường dẫn file (đường dẫn tương đối)

    public Student() {
    }

    public Student(String id, String name, float garden) {
        this.id = id;
        this.name = name;
        this.garden = garden;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGarden() {
        return garden;
    }

    public void setGarden(float garden) {
        this.garden = garden;
    }
}
