package com.developr.me_commars.model;

public class Catogory {
    private String name,icon,color,berief;
    private int id;


    public Catogory(String name, String icon, String color, String berief, int id) {
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.berief = berief;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBerief() {
        return berief;
    }

    public void setBerief(String berief) {
        this.berief = berief;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
