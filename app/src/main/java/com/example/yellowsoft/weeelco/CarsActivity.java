package com.example.yellowsoft.weeelco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 8/2/18.
 */

public class CarsActivity extends Activity {
    ListView listView;
    ImageView back_btn;
    ArrayList<Cars> carsfrom_api;
    CarsAdapter adapter;
    String year,pick_time,pick_date,drop_date,drop_time;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookings_screen);
        Session.forceRTLIfSupported(this);
        carsfrom_api = new ArrayList<>();
        year = getIntent().getStringExtra("year");
        pick_date = getIntent().getStringExtra("date");
        pick_time = getIntent().getStringExtra("time");
        drop_date = getIntent().getStringExtra("drop_date");
        drop_time = getIntent().getStringExtra("drop_time");
        listView = (ListView) findViewById(R.id.bookings_list);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        adapter = new CarsAdapter(this,carsfrom_api);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CarsActivity.this,CarDetailsPage.class);
                intent.putExtra("cars",carsfrom_api.get(i));
                intent.putExtra("date",pick_date);
                intent.putExtra("time",pick_time);
                intent.putExtra("drop_date",drop_date);
                intent.putExtra("drop_time",drop_time);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarsActivity.this.onBackPressed();
            }
        });

        get_cars();

    }

    public void get_cars(){
        final KProgressHUD hud = KProgressHUD.create(CarsActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();

        Ion.with(this)
                .load(Session.SERVERURL+"cars.php")
                .setBodyParameter("year",year)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try{
                            hud.dismiss();
                            Log.e("carsresponse",result.toString());
                            for (int i=0;i<result.size();i++){
                                Cars cars = new Cars(result.get(i).getAsJsonObject(),CarsActivity.this);
                                carsfrom_api.add(cars);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
