package com.example.yellowsoft.weeelco;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yellowsoft on 12/2/18.
 */

public class BookingsOrderDetailPage  extends Activity{
    Orders orders;
    ImageView back_btn;
    TextView booking_id,date,payment_status,curr_status,payment_method,fname,phone,email,title,car_title,price,pick_address,pick_area,pick_date_time,drop_address,
            drop_area,drop_date_time;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_order_details);
        Session.forceRTLIfSupported(this);
        orders = (Orders) getIntent().getSerializableExtra("orders");
        back_btn = (ImageView) findViewById(R.id.back_btn);
        booking_id = (TextView) findViewById(R.id.booking_id);
        date = (TextView) findViewById(R.id.date);
        payment_status = (TextView) findViewById(R.id.payment_status);
        curr_status = (TextView) findViewById(R.id.curr_status);
        payment_method = (TextView) findViewById(R.id.payment_method);
        fname = (TextView) findViewById(R.id.fname);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        title = (TextView) findViewById(R.id.title);
        car_title = (TextView) findViewById(R.id.car_title);
        price = (TextView) findViewById(R.id.price);
        pick_address = (TextView) findViewById(R.id.pick_address);
        pick_area = (TextView) findViewById(R.id.pick_area);
        pick_date_time = (TextView) findViewById(R.id.pick_date_time);
        drop_address = (TextView) findViewById(R.id.drop_address);
        drop_area = (TextView) findViewById(R.id.drop_area);
        drop_date_time = (TextView) findViewById(R.id.drop_date_time);

        booking_id.setText(orders.id);
        date.setText(orders.date);
        payment_status.setText(orders.payment_status);
        curr_status.setText(orders.current_status);
        payment_method.setText(orders.payment_method);
        fname.setText(orders.name);
        phone.setText(orders.phone);
        email.setText(orders.email);
        if (Session.GetLang(this).equals("en")) {
            title.setText(orders.company_title);
            car_title.setText(orders.car_title);
            pick_area.setText(orders.pick_area_title);
            drop_area.setText(orders.drop_area_title);
        }else {
            title.setText(orders.company_title_ar);
            car_title.setText(orders.car_title_ar);
            pick_area.setText(orders.pick_area_title_ar);
            drop_area.setText(orders.drop_area_title_ar);
        }
        price.setText(orders.price);
        pick_address.setText(orders.pick_address);
        pick_date_time.setText(orders.pick_date + " " + orders.pick_time);
        drop_address.setText(orders.drop_address);
        drop_date_time.setText(orders.drop_date + " " +orders.drop_time);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingsOrderDetailPage.this.onBackPressed();
            }
        });



    }
}
