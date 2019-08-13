package com.adrian971029.gametime.model;

public class Game {

    private String title;
    private String desc;

    public Game(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
