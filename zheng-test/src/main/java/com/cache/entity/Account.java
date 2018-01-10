package com.cache.entity;

import java.io.Serializable;

public class Account implements Serializable{

    public static final int serialVersionUID = -1;

    private int id;
    private String name;

    public Account(String name) {
        this.name = name;
    }
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
        return "id=" + this.id + " name=" + this.name;
    }
}