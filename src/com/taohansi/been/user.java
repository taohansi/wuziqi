package com.taohansi.been;

import java.io.Serializable;

public class user implements Serializable{
    private String name;
    private int color;

    public user() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
