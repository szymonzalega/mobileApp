package com.example.myapplication.dto;

public class User {

    public User() {
    }

    public User(String nick, String description, Double lon, Double lat) {
        this.nick = nick;
        this.description = description;
        this.lon = lon;
        this.lat = lat;
    }

    private String nick;
    private String description;
    private Double lon;
    private Double lat;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "User{" +
                "nick='" + nick + '\'' +
                ", description='" + description + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
