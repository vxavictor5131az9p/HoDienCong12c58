package com.example.contact_page;

import java.io.Serializable;

public class Persion implements Serializable {
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

    @Override
    public String toString() {
        return this.id +"-"+this.name;
    }

    public Persion() {
    }

    public Persion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

}
