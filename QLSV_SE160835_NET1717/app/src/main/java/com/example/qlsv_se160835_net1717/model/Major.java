package com.example.qlsv_se160835_net1717.model;

public class Major {
    private int idMajor;
    private String nameMajor;

    public Major(int idMajor, String nameMajor) {
        this.idMajor = idMajor;
        this.nameMajor = nameMajor;
    }

    public int getIdMajor() {
        return idMajor;
    }

    public void setIdMajor(int idMajor) {
        this.idMajor = idMajor;
    }

    public String getNameMajor() {
        return nameMajor;
    }

    public void setNameMajor(String nameMajor) {
        this.nameMajor = nameMajor;
    }
}
