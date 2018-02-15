package com.example.yellowsoft.weeelco;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yellowsoft on 8/2/18.
 */

public class CarDetailsPage extends Activity {
    ImageView back_btn;
    TextView pickup_date_time,dropoff_date_time,modify_btn,car_title,passenger_count,luggage_count,door_count,price,pay_total_price,total_price;
    ImageView car_image,modify;
    LinearLayout cod_btn,pay_btn;
    Cars cars;
    String date,time,drop_date,drop_time;
    ExpandableListView expandableListView;
    List<String> expandableListTitle;
    List<String> expandableListDescription;
    HashMap<String, List<String>> expandableListDetail;
    ExpandableAdapter expandableListAdapter;
    Float total_cart_price = 0.0f;
    int extras = 0;
    int pp = 0;
    int op = 0;
    TextView extra,protection_package,other_protections;
    Float total;
    LinearLayout checkout_option;
    TextView login_btn,guest_btn,cancel_btn;
    TextView st_other,st_basic_price,st_extras,st_pp,st_op,st_total_price,st_pay_now_price,st_pickup_price;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_info);
        Session.forceRTLIfSupported(this);
        cars = (Cars) getIntent().getSerializableExtra("cars");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        drop_date = getIntent().getStringExtra("drop_date");
        drop_time = getIntent().getStringExtra("drop_time");
        back_btn = (ImageView) findViewById(R.id.back_btn);
        car_image = (ImageView) findViewById(R.id.car_image);
        pickup_date_time = (TextView) findViewById(R.id.pickup_date_time);
        dropoff_date_time = (TextView) findViewById(R.id.dropoff_date_time);
        modify_btn = (TextView) findViewById(R.id.modify_btn);
        car_title = (TextView) findViewById(R.id.car_title);
        passenger_count = (TextView) findViewById(R.id.passenger_count);
        luggage_count = (TextView) findViewById(R.id.luggage_count);
        door_count = (TextView) findViewById(R.id.door_count);
        price = (TextView) findViewById(R.id.price);
        pay_total_price = (TextView) findViewById(R.id.pay_total_price);
        total_price = (TextView) findViewById(R.id.total_price);
        modify = (ImageView) findViewById(R.id.modify);
        cod_btn = (LinearLayout) findViewById(R.id.cod_btn);
        pay_btn = (LinearLayout) findViewById(R.id.pay_btn);
        checkout_option = (LinearLayout) findViewById(R.id.checkout_option);
        extra = (TextView) findViewById(R.id.extras);
        protection_package = (TextView) findViewById(R.id.protection_package);
        other_protections = (TextView) findViewById(R.id.other_protections);
        login_btn = (TextView) findViewById(R.id.login_btn);
        guest_btn = (TextView) findViewById(R.id.guest_btn);
        cancel_btn = (TextView) findViewById(R.id.cancel_btn);

        st_other = (TextView) findViewById(R.id.st_other);
        st_basic_price = (TextView) findViewById(R.id.st_basic_price);
        st_extras = (TextView) findViewById(R.id.st_extras);
        st_pp = (TextView) findViewById(R.id.st_pp);
        st_op = (TextView) findViewById(R.id.st_op);
        st_total_price = (TextView) findViewById(R.id.st_total_price);
        st_pay_now_price = (TextView) findViewById(R.id.st_pay_now_price);
        st_pickup_price = (TextView) findViewById(R.id.st_pickup_price);

        st_other.setText(Session.GetWord(this,"Choose other protections"));
        st_basic_price.setText(Session.GetWord(this,"Basic Price"));
        st_extras.setText(Session.GetWord(this,"Extras"));
        st_pp.setText(Session.GetWord(this,"Protection Package"));
        st_op.setText(Session.GetWord(this,"Other protections"));
        st_total_price.setText(Session.GetWord(this,"Total Price"));
        st_pay_now_price.setText(Session.GetWord(this,"PAY NOW PRICE"));
        st_pickup_price.setText(Session.GetWord(this,"PAY AT PICK UP PRICE"));
        modify_btn.setText(Session.GetWord(this,"Modify"));

        Picasso.with(this).load(cars.models.get(0).images.get(0).url).placeholder(R.drawable.car_active).into(car_image);
        Log.e("image",cars.models.get(0).images.get(0).url);
        Log.e("size", String.valueOf(cars.models.get(0).images.size()));
        if (Session.GetLang(this).equals("en")){
            car_title.setText(cars.models.get(0).title);
        }else {
            car_title.setText(cars.models.get(0).title_ar);
        }

        passenger_count.setText(cars.models.get(0).passengers + " " + "Passengers");
        luggage_count.setText(cars.models.get(0).luggages + " " + "Luggages");
        door_count.setText(cars.models.get(0).doors + " " + "Doors");
        price.setText(cars.price + " KD ");
        pickup_date_time.setText(date+" at "+time);
        Log.e("pickdatetime",pickup_date_time.getText().toString());
        dropoff_date_time.setText(drop_date+" at "+drop_time);
        Log.e("dropdatetime",dropoff_date_time.getText().toString());
        total_cart_price = total_cart_price + Float.parseFloat(cars.price);
        total_price.setText(String.valueOf(total_cart_price)+" KD ");
        extra.setText(extras + " KD ");
        protection_package.setText(pp + " KD ");
        other_protections.setText(op + " KD ");
        total = total_cart_price + extras + pp +op;
        pay_total_price.setText(String.valueOf(total) + " KD ");



        guest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_btn.setTextColor(getResources().getColor(R.color.textcolor));
                guest_btn.setTextColor(getResources().getColor(R.color.background));
                checkout_option.setVisibility(View.GONE);
                checkout();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_btn.setTextColor(getResources().getColor(R.color.background));
                guest_btn.setTextColor(getResources().getColor(R.color.textcolor));
                checkout_option.setVisibility(View.GONE);
                Intent intent = new Intent(CarDetailsPage.this,MainActivity.class);
                intent.putExtra("act","1");
                startActivity(intent);
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout_option.setVisibility(View.GONE);
            }
        });


        cod_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Session.GetUserId(CarDetailsPage.this).equals("-1")) {
                    checkout_option.setVisibility(View.VISIBLE);
                }else {
                    checkout_option.setVisibility(View.GONE);
                   checkout();
                }
            }
        });

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Session.GetUserId(CarDetailsPage.this).equals("-1")) {
                    checkout_option.setVisibility(View.VISIBLE);
                }else {
                    checkout_option.setVisibility(View.GONE);
                   checkout();
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarDetailsPage.this.onBackPressed();
            }
        });

        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarDetailsPage.this,MainActivity.class);
                intent.putExtra("act","0");
                startActivity(intent);
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarDetailsPage.this,MainActivity.class);
                intent.putExtra("act","0");
                startActivity(intent);
            }
        });


        //Expandable ListView
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableAdapter(CarDetailsPage.this, expandableListTitle, expandableListDetail,cars);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(CarDetailsPage.this,
                        expandableListDetail.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });



//        (new Handler()).post(new Runnable() {
//
//            @Override
//            public void run() {
//                expandableListView.setIndicatorBounds(expandableListView.getWidth()- 90, expandableListView.getWidth());
//            }
//        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(CarDetailsPage.this,
                        expandableListDetail.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        CarDetailsPage.this,
                        expandableListDetail.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListDetail.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();

                return false;
            }
        });

        //





    }

    public void checkout(){
        Intent intent = new Intent(CarDetailsPage.this, CheckoutActivity.class);
        intent.putExtra("cars", cars);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        intent.putExtra("drop_date", drop_date);
        intent.putExtra("drop_time", drop_time);
        intent.putExtra("comp_title", cars.companies.get(0).title);
        intent.putExtra("total_price", String.valueOf(total));
        Log.e("total", total.toString());
        intent.putExtra("extras", extras);
        intent.putExtra("pp", pp);
        intent.putExtra("op", op);
        startActivity(intent);
    }

    public static class ExpandableListDataPump {
        public static HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

            List<String> selling = new ArrayList<String>();
            selling.add("Lorem Ipsum");


            List<String> vitamins = new ArrayList<String>();
            vitamins.add("Lorem Ipsum");


            expandableListDetail.put("What is included?", selling);
            expandableListDetail.put("Modification, Cancellation and Refund policy", vitamins);

            return expandableListDetail;
        }






    }
}
