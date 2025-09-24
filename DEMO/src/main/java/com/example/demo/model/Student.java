package com.example.demo.model;

public class Student {
    private String id;
    private String name;
    private float garden;
    private String rank;

    public Student() {
    }

    public Student(String id, String name, float garden, String rank) {
        this.id = id;
        this.name = name;
        this.garden = garden;
        this.rank = rank;
    }

    public String calculateRank(float g) {
        if (g >= 9) return "Xuất sắc";
        else if (g >= 8) return "Giỏi";
        else if (g >= 6.5) return "Khá";
        else if (g >= 5) return "Trung bình";
        else return "Yếu";
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
        this.rank = calculateRank(garden);
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
