package com.example.yellowsoft.weeelco;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by yellowsoft on 8/2/18.
 */

public class Areas implements Serializable {
    public String id,title,title_ar,type,latitude,longitude;
    String area_id;
    public Areas(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        title = jsonObject.get("title").getAsString();
        title_ar = jsonObject.get("title_ar").getAsString();
        type="0";

    }

    public Areas(JsonObject jsonObject, Context context,String type){
        this.type = type;
        if(type.equals("0")){
            id = jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();


        }else {
            id = jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();

        }
    }
}
