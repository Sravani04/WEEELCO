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
 * Created by yellowsoft on 12/2/18.
 */

public class BookingsOrdersActivity extends Activity {
    ImageView back_btn;
    ListView listView;
    ArrayList<Orders> ordersfrom_api;
    BookingsOrdersAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookings_orders_list);
        Session.forceRTLIfSupported(this);
        ordersfrom_api = new ArrayList<>();
        listView = (ListView) findViewById(R.id.bookings_list);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        adapter = new BookingsOrdersAdapter(this,ordersfrom_api);
        listView.setAdapter(adapter);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingsOrdersActivity.this.onBackPressed();
            }
        });

        get_orders();

    }


    public void get_orders(){
        final KProgressHUD hud = KProgressHUD.create(BookingsOrdersActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"order-history.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            for (int i=0;i<result.size();i++){
                                Orders orders = new Orders(result.get(i).getAsJsonObject(),BookingsOrdersActivity.this);
                                ordersfrom_api.add(orders);
                            }

                            adapter.notifyDataSetChanged();

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
