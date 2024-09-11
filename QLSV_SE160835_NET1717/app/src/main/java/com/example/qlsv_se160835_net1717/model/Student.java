package com.example.qlsv_se160835_net1717.model;

public class Student {
    private int id;
    private String name;
    private String date;
    private String gender;
    private String email;
    private String address;
    private int idMajor;

    public Student(int id, String name, String date, String gender, String email, String address, int idMajor) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.idMajor = idMajor;
    }

    public Student(String name, String date, String gender, String email, String address, int idMajor) {
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.idMajor = idMajor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdMajor() {
        return idMajor;
    }

    public void setIdMajor(int idMajor) {
        this.idMajor = idMajor;
    }
}
