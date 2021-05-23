package com.github.alexandre.dive.presentation.model;

public class Pokemon {
    private String name;
    private String url;
    private int weight;
    private int height;

    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public int getWeight() { return weight; }
    public int getHeight() {
        return height;
    }
    public void setWeight(int w) {
        this.weight = w;
    }
    public void setHeight(int h) {
        this.height = h;
    }
}