package com.example.app_.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class information {

    @SerializedName("token")
    @Expose
    private String token;
    public String getStatus() {
        return token;
    }

    public void setStatus(String token) {
        this.token = token;
    }

}
