package com.example.yellowsoft.weeelco;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by yellowsoft on 12/2/18.
 */

public class Orders implements Serializable {
    public String id,name,email,phone,pick_address,pick_date,pick_time,drop_address,drop_date,drop_time,price,payment_method,payment_status,current_status,date,
    company_id,company_title,company_title_ar,car_id,car_title,car_title_ar,pick_area_id,pick_area_title,pick_area_title_ar,drop_area_id,drop_area_title,drop_area_title_ar;
    public Orders(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        name = jsonObject.get("name").getAsString();
        email = jsonObject.get("email").getAsString();
        phone = jsonObject.get("phone").getAsString();
        pick_address =jsonObject.get("pick_address").getAsString();
        pick_date = jsonObject.get("pick_date").getAsString();
        pick_time = jsonObject.get("pick_time").getAsString();
        drop_address = jsonObject.get("drop_address").getAsString();
        drop_date = jsonObject.get("drop_date").getAsString();
        drop_time = jsonObject.get("drop_time").getAsString();
        price = jsonObject.get("price").getAsString();
        payment_method = jsonObject.get("payment_method").getAsString();
        payment_status = jsonObject.get("payment_status").getAsString();
        current_status = jsonObject.get("current_status").getAsString();
        date = jsonObject.get("date").getAsString();
        company_id = jsonObject.get("company").getAsJsonObject().get("id").getAsString();
        company_title = jsonObject.get("company").getAsJsonObject().get("title").getAsString();
        company_title_ar = jsonObject.get("company").getAsJsonObject().get("title_ar").getAsString();
        car_id = jsonObject.get("car").getAsJsonObject().get("id").getAsString();
        car_title = jsonObject.get("car").getAsJsonObject().get("title").getAsString();
        car_title_ar = jsonObject.get("car").getAsJsonObject().get("title_ar").getAsString();
        pick_area_id = jsonObject.get("pick_area").getAsJsonObject().get("id").getAsString();
        pick_area_title = jsonObject.get("pick_area").getAsJsonObject().get("title").getAsString();
        pick_area_title_ar = jsonObject.get("pick_area").getAsJsonObject().get("title_ar").getAsString();
        drop_area_id = jsonObject.get("drop_area").getAsJsonObject().get("id").getAsString();
        drop_area_title = jsonObject.get("drop_area").getAsJsonObject().get("title").getAsString();
        drop_area_title_ar = jsonObject.get("drop_area").getAsJsonObject().get("title_ar").getAsString();


    }
}
