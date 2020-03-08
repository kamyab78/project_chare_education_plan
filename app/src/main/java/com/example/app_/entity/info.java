package com.example.app_.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class info {
    @SerializedName("key")
    @Expose
    private String key;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
