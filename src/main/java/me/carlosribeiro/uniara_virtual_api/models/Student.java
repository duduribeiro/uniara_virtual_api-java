package me.carlosribeiro.uniara_virtual_api.models;

import com.google.gson.Gson;

public class Student {
    private String password, ra;
    public Student(String ra, String password) {
        this.password = password;
        this.ra       = ra;
    }

    public String getPassword() {
        return password;
    }

    public String getRa() {
        return ra;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
