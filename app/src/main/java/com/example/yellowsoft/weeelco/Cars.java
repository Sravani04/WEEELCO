package com.example.yellowsoft.weeelco;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yellowsoft on 8/2/18.
 */

public class Cars implements Serializable{
    public String id,year,price;
    public ArrayList<Company> companies;
    public ArrayList<Model> models;
    public Cars(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        year = jsonObject.get("year").getAsString();
        price = jsonObject.get("price").getAsString();
        companies = new ArrayList<>();
        for (int i = 0; i < jsonObject.get("company").getAsJsonArray().size(); i++) {
            Company company = new Company(jsonObject.get("company").getAsJsonArray().get(i).getAsJsonObject(), context);
            companies.add(company);
        }
        models = new ArrayList<>();
        for (int i = 0; i < jsonObject.get("model").getAsJsonArray().size(); i++) {
            Model model = new Model(jsonObject.get("model").getAsJsonArray().get(i).getAsJsonObject(), context);
            models.add(model);
        }
    }

    public class Company implements Serializable{
        public String id,title,title_ar,area_id,area_title,area_title_ar,hours,image,banner,payment_id,payment_title,payment_title_ar,payment_image,small_description,small_description_ar,
                description,description_ar;
        public Company(JsonObject jsonObject,Context context){
            id = jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();
            area_id = jsonObject.get("area").getAsJsonObject().get("id").getAsString();
            area_title = jsonObject.get("area").getAsJsonObject().get("title").getAsString();
            area_title_ar = jsonObject.get("area").getAsJsonObject().get("title_ar").getAsString();
            hours = jsonObject.get("hours").getAsString();
            image = jsonObject.get("image").getAsString();
            payment_id = jsonObject.get("payment").getAsJsonObject().get("id").getAsString();
            payment_title = jsonObject.get("payment").getAsJsonObject().get("title").getAsString();
            payment_title_ar = jsonObject.get("payment").getAsJsonObject().get("title_ar").getAsString();
            payment_image = jsonObject.get("payment").getAsJsonObject().get("image").getAsString();
            small_description = jsonObject.get("small_description").getAsString();
            small_description_ar = jsonObject.get("small_description_ar").getAsString();
            description = jsonObject.get("description").getAsString();
            description_ar = jsonObject.get("description_ar").getAsString();

        }
    }

    public class Model implements Serializable{
        public String id,title,title_ar,passengers,luggages,gears,transmission,doors,category_id,category_title,category_title_ar,brand_id,brand_title,brand_title_ar,
                description,description_ar;
        public ArrayList<Included> includes;
        public ArrayList<Image>images;
        //public ArrayList<Images> images;
        public Model(JsonObject jsonObject,Context context){
            id = jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();
            passengers = jsonObject.get("passengers").getAsString();
            luggages = jsonObject.get("luggages").getAsString();
            gears = jsonObject.get("gears").getAsString();
            transmission = jsonObject.get("transmission").getAsString();
            doors = jsonObject.get("doors").getAsString();
            category_id = jsonObject.get("category").getAsJsonObject().get("id").getAsString();
            category_title = jsonObject.get("category").getAsJsonObject().get("title").getAsString();
            category_title_ar = jsonObject.get("category").getAsJsonObject().get("title_ar").getAsString();
            brand_id = jsonObject.get("brand").getAsJsonObject().get("id").getAsString();
            brand_title = jsonObject.get("brand").getAsJsonObject().get("title").getAsString();
            brand_title_ar = jsonObject.get("brand").getAsJsonObject().get("title_ar").getAsString();
            description = jsonObject.get("description").getAsString();
            description_ar = jsonObject.get("description_ar").getAsString();
            includes = new ArrayList<>();
            for (int i = 0; i < jsonObject.get("included").getAsJsonArray().size(); i++) {
                Included included = new Included(jsonObject.get("included").getAsJsonArray().get(i).getAsJsonObject(), context);
                includes.add(included);
            }
            images = new ArrayList<>();
            for (int i = 0; i < jsonObject.get("images").getAsJsonArray().size(); i++) {
                Image image = new Image(jsonObject.get("images").getAsJsonArray().get(i).getAsString(), context);
                images.add(image);
            }


        }

        public class Included implements Serializable{
            public String id,title,title_ar;
            public Included(JsonObject jsonObject,Context context){
                id = jsonObject.get("id").getAsString();
                title = jsonObject.get("title").getAsString();
                title_ar = jsonObject.get("title_ar").getAsString();
            }
        }

        public class Image implements Serializable{
            String url;
            public Image(String string,Context context){
               this.url=string;
            }
        }

    }


//    public JsonObject getJson(){
//
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("id",id);
//        jsonObject.addProperty("year",year);
//        jsonObject.addProperty("price",price);
//
//        JsonArray companies = new JsonArray();
//        for (int i =0;i<this.companies.size();i++){
//            JsonObject company = new JsonObject();
//            company.addProperty("id",this.companies.get(i).id);
//            company.addProperty("title",this.companies.get(i).title);
//            company.addProperty("title_ar",this.companies.get(i).title_ar);
//            company.addProperty("hours",this.companies.get(i).hours);
//            company.addProperty("image",this.companies.get(i).image);
//            company.addProperty("small_description",this.companies.get(i).small_description);
//            company.addProperty("small_description_ar",this.companies.get(i).small_description_ar);
//            company.addProperty("description",this.companies.get(i).description);
//            company.addProperty("description_ar",this.companies.get(i).description_ar);
//
//            JsonObject area = new JsonObject();
//            area.addProperty("id",this.companies.get(i).area_id);
//            area.addProperty("title",this.companies.get(i).area_title);
//            area.addProperty("title_ar",this.companies.get(i).area_title_ar);
//            company.add("area",area);
//
//        }
//
//
//        JsonArray models = new JsonArray();
//        for (int i =0;i<this.models.size();i++){
//            JsonObject model = new JsonObject();
//            model.addProperty("id",this.models.get(i).id);
//            model.addProperty("title",this.models.get(i).title);
//            model.addProperty("title_ar",this.models.get(i).title_ar);
//            model.addProperty("passengers",this.models.get(i).passengers);
//            model.addProperty("luggages",this.models.get(i).luggages);
//            model.addProperty("gears",this.models.get(i).gears);
//            model.addProperty("transmission",this.models.get(i).transmission);
//            model.addProperty("doors",this.models.get(i).doors);
//            model.addProperty("description",this.models.get(i).description);
//            model.addProperty("description_ar",this.models.get(i).description_ar);
//
//            JsonObject category = new JsonObject();
//            category.addProperty("id",this.models.get(i).category_id);
//            category.addProperty("title",this.models.get(i).category_title);
//            category.addProperty("title_ar",this.models.get(i).category_title_ar);
//            model.add("category",model);
//
//            JsonObject brand = new JsonObject();
//            brand.addProperty("id",this.models.get(i).brand_id);
//            brand.addProperty("title",this.models.get(i).brand_title);
//            brand.addProperty("title_ar",this.models.get(i).brand_title_ar);
//            model.add("category",brand);
//
//
//
//        }
//
//
//        jsonObject.add("companies",companies);
//
//        return jsonObject;
//    }


}
