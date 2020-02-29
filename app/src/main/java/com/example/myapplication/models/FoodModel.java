package com.example.myapplication.models;

import io.realm.RealmObject;

public class FoodModel extends RealmObject {
   private String foodId;
    private String name;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;
    private String author;
    private String poster;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }




}
