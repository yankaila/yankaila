package com.example.myapplication.migration;


import com.example.myapplication.models.FoodModel;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion ==0){
           schema.create("FoodModel")
                   .addField("foodId",String.class)
                   .addField("name",String.class)
                   .addField("path",String.class)
                   .addField("author",String.class)
                   .addField("poster",String.class);
           schema.create("AlbumModel")
                   .addField("albumId",String.class)
                   .addField("name",String.class)
                   .addField("poster",String.class)
                   .addField("playNum",String.class)
                   .addRealmListField("list",schema.get("FoodModel"));
           schema.create("FoodSourceModel")
                   .addRealmListField("album",schema.get("AlbumModel"))
                   .addRealmListField("hot",schema.get("FoodModel"));
            oldVersion = newVersion;
        }
    }
}
