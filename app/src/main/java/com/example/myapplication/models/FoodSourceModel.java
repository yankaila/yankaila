package com.example.myapplication.models;

import io.realm.RealmList;
import io.realm.RealmObject;

public class FoodSourceModel extends RealmObject {

    private RealmList<AlbumModel>  album;
    private RealmList<FoodModel>   hot;

    public RealmList<AlbumModel> getAlbum() {
        return album;
    }

    public void setAlbum(RealmList<AlbumModel> album) {
        this.album = album;
    }

    public RealmList<FoodModel> getHot() {
        return hot;
    }

    public void setHot(RealmList<FoodModel> hot) {
        this.hot = hot;
    }
}
