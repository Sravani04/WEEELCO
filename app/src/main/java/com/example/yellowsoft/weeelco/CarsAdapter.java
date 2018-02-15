package com.example.yellowsoft.weeelco;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 8/2/18.
 */

public class CarsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Cars> cars;
    public CarsAdapter(Context context,ArrayList<Cars> cars){
        this.context = context;
        this.cars = cars;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item_view = inflater.inflate(R.layout.booking_screen_items,null);
        TextView car_title = (TextView) item_view.findViewById(R.id.car_title);
        ImageView car_image = (ImageView) item_view.findViewById(R.id.car_image);
        TextView cat_title = (TextView) item_view.findViewById(R.id.cat_title);
        TextView price = (TextView) item_view.findViewById(R.id.price);
        TextView passengers_count = (TextView) item_view.findViewById(R.id.passengers_count);
        TextView luggage_count = (TextView) item_view.findViewById(R.id.luggage_count);
        TextView doors_count = (TextView) item_view.findViewById(R.id.door_count);

        try {
            if (Session.GetLang(context).equals("en")) {
                car_title.setText(cars.get(i).models.get(0).title);
                cat_title.setText(cars.get(i).models.get(0).category_title);
            }else {
                car_title.setText(cars.get(i).models.get(0).title_ar);
                cat_title.setText(cars.get(i).models.get(0).category_title_ar);
            }
            Log.e("car_image", cars.get(i).models.get(0).images.toString());
            Picasso.with(context).load(cars.get(i).models.get(0).images.get(0).url).placeholder(R.drawable.car_inactive).into(car_image);
            price.setText(cars.get(i).price + " KD ");
            passengers_count.setText(cars.get(i).models.get(0).passengers);
            luggage_count.setText(cars.get(i).models.get(0).luggages);
            doors_count.setText(cars.get(i).models.get(0).doors);
        }catch (Exception e){
            e.printStackTrace();
        }


        return item_view;
    }
}
