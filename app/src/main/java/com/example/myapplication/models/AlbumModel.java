package com.example.myapplication.models;


import io.realm.RealmList;
import io.realm.RealmObject;

public class AlbumModel extends RealmObject {

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    private  String   albumId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String   name;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    private String     poster;

    public String getPlayNum() {
        return playNum;
    }

    public void setPlayNum(String playNum) {
        this.playNum = playNum;
    }

    private String  playNum;

    public RealmList<FoodModel> getList() {
        return list;
    }

    public void setList(RealmList<FoodModel> list) {
        this.list = list;
    }

    private RealmList<FoodModel> list;
}
