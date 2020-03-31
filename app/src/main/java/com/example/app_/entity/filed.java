package com.example.app_.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class filed {
public String name;
public int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
