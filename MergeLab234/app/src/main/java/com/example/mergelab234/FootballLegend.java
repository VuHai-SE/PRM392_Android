package com.example.mergelab234;

public class FootballLegend {
    private String name;
    private String details;
    private int playerImage;
    private int countryImage;

    public FootballLegend(String name, String details, int playerImage, int countryImage) {
        this.name = name;
        this.details = details;
        this.playerImage = playerImage;
        this.countryImage = countryImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(int playerImage) {
        this.playerImage = playerImage;
    }

    public int getCountryImage() {
        return countryImage;
    }

    public void setCountryImage(int countryImage) {
        this.countryImage = countryImage;
    }
}
