package com.example.customlistview;

public class TraiCay {
    private String ten;
    private String moTa;
    private int hinh;

    public TraiCay(String ten, String moTa, int hinh) {
        this.ten = ten;
        this.moTa = moTa;
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
