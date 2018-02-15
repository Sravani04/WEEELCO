package com.example.yellowsoft.weeelco;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.wdullaer.materialdatetimepicker.time.Timepoint;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by yellowsoft on 7/2/18.
 */

public class HomeFragment extends Fragment {
    TextView pickup_year,pickup_day,pickup_month,drop_year,drop_day,drop_month,pickup_time,drop_time,search_btn;
    CardView card1,card2;
    boolean CurentDate=true;
    String date,drop_date;
    LinearLayout date_click,time_click,drop_date_click,drop_time_click;
    int year;
    TextView st_pickup,st_dropoff,st_line,st_line2;

    public static HomeFragment newInstance(int someInt) {
        HomeFragment myFragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_screen,container,false);
        Session.forceRTLIfSupported(getActivity());
        pickup_year = (TextView) view.findViewById(R.id.pickup_year);
        pickup_day = (TextView) view.findViewById(R.id.pickup_day);
        pickup_month = (TextView) view.findViewById(R.id.pickup_month);
        pickup_time = (TextView) view.findViewById(R.id.pickup_time);
        drop_year = (TextView) view.findViewById(R.id.drop_year);
        drop_day = (TextView) view.findViewById(R.id.drop_day);
        drop_month = (TextView) view.findViewById(R.id.drop_month);
        drop_time = (TextView) view.findViewById(R.id.drop_time);
        search_btn = (TextView) view.findViewById(R.id.search_btn);
        card1 = (CardView) view.findViewById(R.id.card1);
        card2 = (CardView) view.findViewById(R.id.card2);
        date_click = (LinearLayout) view.findViewById(R.id.date_click);
        time_click  = (LinearLayout) view.findViewById(R.id.time_click);
        drop_date_click = (LinearLayout) view.findViewById(R.id.drop_date_click);
        drop_time_click  = (LinearLayout) view.findViewById(R.id.drop_time_click);

        st_pickup = (TextView) view.findViewById(R.id.st_pickup);
        st_dropoff = (TextView) view.findViewById(R.id.st_dropoff);
        st_line = (TextView) view.findViewById(R.id.st_line);
        st_line2 = (TextView) view.findViewById(R.id.st_line2);

        st_pickup.setText(Session.GetWord(getActivity(),"PICK-UP TIME"));
        st_dropoff.setText(Session.GetWord(getActivity(),"DROP OFF TIME"));
        search_btn.setText(Session.GetWord(getActivity(),"SEARCH"));

        date_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_date();
            }
        });

        time_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_time();
            }
        });

        drop_date_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_drop_date();
            }
        });

        drop_time_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_drop_time();
            }
        });



        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CarsActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("date",date);
                intent.putExtra("time",pickup_time.getText().toString());
                intent.putExtra("drop_date",drop_date);
                intent.putExtra("drop_time",drop_time.getText().toString());
                startActivity(intent);
            }
        });


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int selectedday = calendar.get(Calendar.DAY_OF_MONTH);
        String[] days = new String[] { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
        final String day = days[calendar.get(Calendar.DAY_OF_WEEK) -1];
        final String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
        String month = months[calendar.get(Calendar.MONTH)];
        String day1;
        if (selectedday<10){
            day1 = "0" + String.valueOf(selectedday);
        }else {
            day1 =  String.valueOf(selectedday);
        }

        String day2;
        if (selectedday<10){
            day2 = "0" + String.valueOf(selectedday+1);
        }else {
            day2 =  String.valueOf(selectedday+1);
        }

        pickup_month.setText(month);
        pickup_year.setText(day1);
        pickup_day.setText(day);
        drop_month.setText(month);
        drop_year.setText(day2);
        drop_day.setText(day);

        date = day1 +" "+(month)+" "+year;
        drop_date = day2 +" "+(month)+" "+year;

        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);
        pickup_time.setText(currentDateTimeString);
        drop_time.setText(currentDateTimeString);






        return view;
    }


    public void select_date(){
        final Calendar mcurrentDate= Calendar.getInstance();
        final int mYear = mcurrentDate.get(Calendar.YEAR);
        final int mMonth = mcurrentDate.get(Calendar.MONTH);
        final int mDay = mcurrentDate.get(Calendar.DAY_OF_WEEK);



        DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                year = selectedyear;
                String day1;
                if (selectedday <10){
                    day1 = "0" + String.valueOf(selectedday);
                }else {
                    day1 = String.valueOf(selectedday);
                }
                pickup_year.setText(day1);
                ///pickup_year.setText(Integer.toString(selectedyear).substring(2));
                Calendar mcurrentDate= Calendar.getInstance();
                mcurrentDate.set(selectedyear, selectedmonth,selectedday);
                int selectedday_temp = mcurrentDate.get(Calendar.DAY_OF_WEEK);
                final String[] days = new String[] { "Sun","Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
                String day = days[selectedday_temp-1];
                pickup_day.setText(day);
                Log.e("log",day);
                Log.e("number",String.valueOf(selectedday_temp));

                int selectedmonth_temp = mcurrentDate.get(Calendar.MONTH);
                final String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
                String month = months[selectedmonth_temp];
                pickup_month.setText(month);
                Log.e("log",month);
                Log.e("number",String.valueOf(selectedmonth_temp));
                date = day1 +" "+(month)+" "+selectedyear;
                Log.e("pickdate",date);


            }
        },mYear, mMonth, mDay);
        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();

    }



    public void  select_time(){
        ArrayList<Timepoint> timepoints = new ArrayList<>();
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                boolean isPM = (selectedHour >= 12);
                pickup_time.setText(String.format("%02d:%02d %s", (selectedHour == 12 || selectedHour == 0) ? 12 : selectedHour % 12, selectedMinute, isPM ? "PM" : "AM"));
            }

        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void select_drop_date(){
        final Calendar mcurrentDate= Calendar.getInstance();
        final int mYear = mcurrentDate.get(Calendar.YEAR);
        final int mMonth = mcurrentDate.get(Calendar.MONTH);
        final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                String day1;
                if (selectedday <10){
                    day1 = "0" + String.valueOf(selectedday);
                }else {
                    day1 = String.valueOf(selectedday);
                }
                drop_year.setText(day1);
                //drop_year.setText(Integer.toString(selectedyear).substring(2));
                Calendar mcurrentDate= Calendar.getInstance();
                mcurrentDate.set(selectedyear, selectedmonth,selectedday);
                int selectedday_temp = mcurrentDate.get(Calendar.DAY_OF_WEEK);
                final String[] days = new String[] { "Sun","Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
                String day = days[selectedday_temp-1];
                drop_day.setText(day);
                Log.e("log",day);
                Log.e("number",String.valueOf(selectedday_temp));

                int selectedmonth_temp = mcurrentDate.get(Calendar.MONTH);
                final String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
                String month = months[selectedmonth_temp];
                pickup_month.setText(month);
                Log.e("log",month);
                Log.e("number",String.valueOf(selectedmonth_temp));
               // date = day +" "+(month)+" "+selectedyear;
                Log.e("pickdate",date);
                drop_date = day1 +" "+(month)+" "+selectedyear;
                Log.e("dropdate",drop_date);

            }
        },mYear, mMonth, mDay);
        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();

    }

    public void select_drop_time(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                boolean isPM = (selectedHour >= 12);
                drop_time.setText(String.format("%02d:%02d %s", (selectedHour == 12 || selectedHour == 0) ? 12 : selectedHour % 12, selectedMinute, isPM ? "PM" : "AM"));
            }

        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
