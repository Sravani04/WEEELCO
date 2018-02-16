package com.example.yellowsoft.weeelco;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by yellowsoft on 9/2/18.
 */

public class CheckoutActivity extends Activity {
    EditText fname,lname,phone,pick_add,drop_add,flat,email,search;
    LinearLayout area_click,tap_ll,cod_ll,place_order,popup_view,drop_area_click;
    TextView area,car_title,passenger_count,luggage_count,door_count,price,total_price,drop_area,pick_date,pick_time,drop_date,drop_time,extras,pp,op;
    ImageView tap_img,cod_img,car_image,back_btn,close_btn;
    String pay_met="";
    String message;
    ArrayList<Areas> areasfrom_api;
    String area_id,drop_area_id;
    ListView listView;
    AreaAdapter adapter;
    Cars cars;
    int extra = 0;
    int pro_package = 0;
    int other_pro = 0;
    String date,time,dropoff_date,dropoff_time,company_title,final_price;
    ListView drop_areas_list;
    LinearLayout drop_popup_view;
    EditText search_area;
    ImageView drop_close_btn;
    TextView st_order_summary,st_pay_price_summery,st_pay_method,st_basic_price,st_extras,st_pp,st_op,st_total_price;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_screen);
        Session.forceRTLIfSupported(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        cars = (Cars) getIntent().getSerializableExtra("cars");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        dropoff_date = getIntent().getStringExtra("drop_date");
        dropoff_time = getIntent().getStringExtra("drop_time");
        company_title = getIntent().getStringExtra("comp_title");
        final_price = getIntent().getStringExtra("total_price");
        areasfrom_api = new ArrayList<>();
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        phone = (EditText) findViewById(R.id.phone);
        pick_add = (EditText) findViewById(R.id.pick_add);
        drop_add = (EditText) findViewById(R.id.drop_add);
        flat = (EditText) findViewById(R.id.flat);
        email = (EditText) findViewById(R.id.email);
        search  = (EditText) findViewById(R.id.search);
        search_area  = (EditText) findViewById(R.id.search_drop_area);
        area_click = (LinearLayout) findViewById(R.id.area_click);
        tap_ll = (LinearLayout) findViewById(R.id.tap_ll);
        cod_ll = (LinearLayout) findViewById(R.id.cod_ll);
        place_order = (LinearLayout) findViewById(R.id.place_order);
        popup_view = (LinearLayout) findViewById(R.id.popup_view);
        drop_popup_view = (LinearLayout) findViewById(R.id.drop_popup_view);
        drop_area_click = (LinearLayout) findViewById(R.id.drop_area_click);
        area = (TextView) findViewById(R.id.area);
        car_title = (TextView) findViewById(R.id.car_title);
        passenger_count = (TextView) findViewById(R.id.passenger_count);
        luggage_count = (TextView) findViewById(R.id.luggage_count);
        door_count = (TextView) findViewById(R.id.door_count);
        price = (TextView) findViewById(R.id.price);
        total_price = (TextView) findViewById(R.id.total_price);
        drop_area = (TextView) findViewById(R.id.drop_area);
        pick_time = (TextView) findViewById(R.id.pick_time);
        pick_date = (TextView) findViewById(R.id.pick_date);
        drop_date = (TextView) findViewById(R.id.drop_date);
        drop_time = (TextView) findViewById(R.id.drop_time);
        extras = (TextView) findViewById(R.id.extras);
        pp = (TextView) findViewById(R.id.pp);
        op = (TextView) findViewById(R.id.op);
        car_image = (ImageView) findViewById(R.id.car_image);
        tap_img = (ImageView) findViewById(R.id.tap_img);
        cod_img = (ImageView) findViewById(R.id.cod_img);
        close_btn = (ImageView) findViewById(R.id.close_btn);
        drop_close_btn = (ImageView) findViewById(R.id.drop_close_btn);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        listView = (ListView) findViewById(R.id.areas_list);
        drop_areas_list = (ListView) findViewById(R.id.drop_areas_list);
        adapter = new AreaAdapter(this,areasfrom_api,CheckoutActivity.this);
        listView.setAdapter(adapter);
        drop_areas_list.setAdapter(adapter);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckoutActivity.this.onBackPressed();
            }
        });


        st_order_summary = (TextView) findViewById(R.id.st_order_summary);
        st_pay_price_summery = (TextView) findViewById(R.id.st_pay_price_summery);
        st_pay_method = (TextView) findViewById(R.id.st_pay_method);
        st_basic_price = (TextView) findViewById(R.id.st_basic_price);
        st_extras = (TextView) findViewById(R.id.st_extras);
        st_pp = (TextView) findViewById(R.id.st_pp);
        st_op = (TextView) findViewById(R.id.st_op);
        st_total_price = (TextView) findViewById(R.id.st_total_price);

        st_order_summary.setText(Session.GetWord(this,"ORDER SUMMARY"));
        st_pay_price_summery.setText(Session.GetWord(this,"PRICE SUMMARY"));
        st_pay_method.setText(Session.GetWord(this,"Payment Method"));
        st_basic_price.setText(Session.GetWord(this,"Basic Price"));
        st_extras.setText(Session.GetWord(this,"Extras"));
        st_pp.setText(Session.GetWord(this,"Protection Package"));
        st_op.setText(Session.GetWord(this,"Other protections"));
        st_total_price.setText(Session.GetWord(this,"Total Price"));
        fname.setHint(Session.GetWord(this,"First Name"));
        lname.setHint(Session.GetWord(this,"Last Name"));
        phone.setHint(Session.GetWord(this,"Phone"));
        pick_add.setHint(Session.GetWord(this,"PickUpAddres"));
        area.setHint(Session.GetWord(this,"PickUp Area"));
        pick_date.setHint(Session.GetWord(this,"PickUp Date"));
        pick_time.setHint(Session.GetWord(this,"PickUp Time"));
        drop_add.setHint(Session.GetWord(this,"Drop Address"));
        drop_area.setHint(Session.GetWord(this,"DropOff Area"));
        drop_date.setHint(Session.GetWord(this,"DropOff Date"));
        drop_time.setHint(Session.GetWord(this,"DropOff Time"));
        flat.setHint(Session.GetWord(this,"Flat"));
        email.setHint(Session.GetWord(this,"Email"));

        area.setHint("Select Pickup Area");
        drop_area.setHint("Select Dropoff Area");
             if (Session.GetLang(this).equals("en")) {
                 car_title.setText(cars.models.get(0).title);
             }else {
                 car_title.setText(cars.models.get(0).title_ar);
             }
            Picasso.with(this).load(cars.models.get(0).images.get(0).url).placeholder(R.drawable.car_inactive).into(car_image);
            price.setText(cars.price + " KD ");
            passenger_count.setText(cars.models.get(0).passengers + " " +"Passengers");
            luggage_count.setText(cars.models.get(0).luggages+" "+ "Luggages");
            door_count.setText(cars.models.get(0).doors+" "+ "Doors");

        pick_date.setText(date);
        pick_time.setText(time);
        drop_time.setText(dropoff_time);
        drop_date.setText(dropoff_date);
        total_price.setText(final_price + " KD ");
        extras.setText(extra + " KD ");
        pp.setText(pro_package+ " KD ");
        op.setText(other_pro + "KD ");

        pick_date.setTextColor(getResources().getColor(R.color.text));
        pick_time.setTextColor(getResources().getColor(R.color.text));
        drop_time.setTextColor(getResources().getColor(R.color.text));
        drop_date.setTextColor(getResources().getColor(R.color.text));




        tap_img.setImageResource(R.drawable.radio_off);
        cod_img.setImageResource(R.drawable.radio_off);
        cod_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tap_img.setImageResource(R.drawable.radio_off);
                cod_img.setImageResource(R.drawable.radio_on);
                pay_met = "Cash";
            }
        });
        tap_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tap_img.setImageResource(R.drawable.radio_on);
                cod_img.setImageResource(R.drawable.radio_off);
                pay_met = "Tap";
            }
        });


        area_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               popup_view.setVisibility(View.VISIBLE);

            }
        });

        drop_area_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drop_popup_view.setVisibility(View.VISIBLE);

            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup_view.setVisibility(View.GONE);
            }
        });


        drop_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drop_popup_view.setVisibility(View.GONE);
            }
        });

        pick_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcurrentDate=Calendar.getInstance();
                final int mYear = mcurrentDate.get(Calendar.YEAR);
                final int mMonth = mcurrentDate.get(Calendar.MONTH);
                final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(CheckoutActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        pick_date.setText(selectedday +"-"+(selectedmonth+1) +"-"+selectedyear);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });

        pick_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CheckoutActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        boolean isPM = (selectedHour >= 12);
                        pick_time.setText(String.format("%02d:%02d %s", (selectedHour == 12 || selectedHour == 0) ? 12 : selectedHour % 12, selectedMinute, isPM ? "PM" : "AM"));
                    }

                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        drop_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcurrentDate=Calendar.getInstance();
                final int mYear = mcurrentDate.get(Calendar.YEAR);
                final int mMonth = mcurrentDate.get(Calendar.MONTH);
                final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(CheckoutActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        drop_date.setText(selectedday +"-"+(selectedmonth+1) +"-"+selectedyear);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        drop_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CheckoutActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        boolean isPM = (selectedHour >= 12);
                        drop_time.setText(String.format("%02d:%02d %s", (selectedHour == 12 || selectedHour == 0) ? 12 : selectedHour % 12, selectedMinute, isPM ? "PM" : "AM"));
                    }

                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        search.setText("");
        search_area.setText("");
        search.setTextColor(getResources().getColor(R.color.text_color));

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(adapter!=null)
                    adapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname_string =fname.getText().toString();
                String lname_string = lname.getText().toString();
                String email_string = email.getText().toString();
                String phone_string = phone.getText().toString();
                String area_string = area_id;
                String pick_add_string = pick_add.getText().toString();
                String drop_add_string = drop_add.getText().toString();
                String pick_date_string = pick_date.getText().toString();
                String flat_string = flat.getText().toString();
                String pick_time_string = pick_time.getText().toString();
                String drop_time_string = drop_time.getText().toString();
                String drop_date_string = drop_date.getText().toString();
                String drop_area_string = drop_area_id;

                if (fname_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter First Name",Toast.LENGTH_SHORT).show();
                    fname.requestFocus();
                }else if (lname_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Last Name",Toast.LENGTH_SHORT).show();
                    lname.requestFocus();
                }else if (phone_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Phone",Toast.LENGTH_SHORT).show();
                    phone.requestFocus();
                }else if (area_string==""){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Area",Toast.LENGTH_SHORT).show();
                    area.requestFocus();
                }else if (pick_add_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Pickup Address",Toast.LENGTH_SHORT).show();
                    pick_add.requestFocus();
                }else if (drop_add_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Drop Address",Toast.LENGTH_SHORT).show();
                    drop_add.requestFocus();
                }else if (drop_area_string==""){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Drop Area",Toast.LENGTH_SHORT).show();
                    drop_area.requestFocus();
                } else if (pick_date_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Pickup Date",Toast.LENGTH_SHORT).show();
                    pick_date.requestFocus();
                }else if (pick_date_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Pickup Date",Toast.LENGTH_SHORT).show();
                    pick_date.requestFocus();
                }else if (pick_time_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter Pickup Time",Toast.LENGTH_SHORT).show();
                    pick_time.requestFocus();
                }else if (drop_date_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter DropOff Date",Toast.LENGTH_SHORT).show();
                    drop_date.requestFocus();
                }else if (drop_time_string.equals("")){
                    Toast.makeText(CheckoutActivity.this,"Please Enter DropOff Time",Toast.LENGTH_SHORT).show();
                    drop_time.requestFocus();
                }else if (email_string.equals("")) {
                    Toast.makeText(CheckoutActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }else{
                    if (pay_met.equals("Tap")){
                        Intent intent = new Intent(CheckoutActivity.this, PaymentPage.class);
                        intent.putExtra("amount", final_price);
                        startActivityForResult(intent, 1);
                    }else if (pay_met.equals("Cash")){
                        place_order();
                    }
                }
            }
        });

        get_areas();
        get_members();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {

                // do something with the result
                if (data != null && data.hasExtra("message")) {
                    message = data.getExtras().getString("message");
                    Log.e("toast", message);
                    if (message.equals("success")){
                        place_order();
                    }else if (message.equals("failure")){
                        Toast.makeText(CheckoutActivity.this,"Please Try Again",Toast.LENGTH_SHORT).show();
                    }
                }

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // some stuff that will happen if there's no result
            }
        }
    }

    public void get_members(){
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();

        Ion.with(this)
                .load(Session.SERVERURL+"members.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            JsonObject jsonObject = result.get(0).getAsJsonObject();
                            fname.setText(jsonObject.get("fname").getAsString());
                            email.setText(jsonObject.get("email").getAsString());
                            phone.setText(jsonObject.get("phone").getAsString());
                            lname.setText(jsonObject.get("lname").getAsString());
                            flat.setText(jsonObject.get("flat").getAsString());

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }


    public void get_areas(){
        final KProgressHUD hud = KProgressHUD.create(CheckoutActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"areas.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            Log.e("country",result.toString());
                            for (int i = 0; i < result.size(); i++) {
                                Areas area = new Areas(result.get(i).getAsJsonObject(),CheckoutActivity.this,"0");
                                areasfrom_api.add(area);

                                for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {

                                    Areas sub_category = new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), CheckoutActivity.this, "1");
                                    areasfrom_api.add(sub_category);

                                }
                            }

                            adapter.notifyDataSetChanged();


                        }catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });

    }



    public void place_order(){

        final KProgressHUD hud = KProgressHUD.create(CheckoutActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("member_id",Session.GetUserId(CheckoutActivity.this));
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("pick_address",pick_add.getText().toString());
        jsonObject1.addProperty("pick_area",area_id);
        jsonObject1.addProperty("pick_date",pick_date.getText().toString());
        jsonObject1.addProperty("pick_time",pick_time.getText().toString());
        jsonObject1.addProperty("drop_address",drop_add.getText().toString());
        jsonObject1.addProperty("drop_area",drop_area_id);
        jsonObject1.addProperty("drop_date",drop_date.getText().toString());
        jsonObject1.addProperty("drop_time",drop_time.getText().toString());
        jsonObject1.addProperty("flat",flat.getText().toString());
        jsonObject.add("address",jsonObject1);

        jsonObject.addProperty("name", fname.getText().toString()+" "+lname.getText().toString());
        jsonObject.addProperty("email",email.getText().toString());
        jsonObject.addProperty("company", company_title);
        jsonObject.addProperty("car",car_title.getText().toString());
        jsonObject.addProperty("price",price.getText().toString());
        jsonObject.addProperty("phone",phone.getText().toString());
        jsonObject.addProperty("type","1");
        jsonObject.addProperty("payment_method",pay_met);
        Log.e("reeeee",jsonObject.toString());

        Ion.with(this)
                .load(Session.SERVERURL + "place-order.php")
                .setBodyParameter("content",jsonObject.toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        hud.dismiss();
                        try {
                            if (result.get("status").getAsString().equals("Success")) {
                                Log.e("result",result.toString());
                                Log.e("invoice_id",result.get("invoice_id").getAsString());
                                Log.e("result", result.get("message").getAsString());
                                Toast.makeText(CheckoutActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CheckoutActivity.this, ThankyouScreen.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(CheckoutActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }


}
