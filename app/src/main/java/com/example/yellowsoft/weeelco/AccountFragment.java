package com.example.yellowsoft.weeelco;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by yellowsoft on 7/2/18.
 */

public class AccountFragment extends Fragment {
    LinearLayout login_screen;
    TextView login_btn,signin_btn,fp_btn,name,email,phone;
    ViewFlipper flipper;
    EditText login_email,login_password,user_fname,user_lname,email_text,password_text,user_phone,old_pass,new_pass,confirm_pass,forgot_email;
    LinearLayout login_ll,create_acc_ll,password_ll,logout_ll,change_pass_popup,forgot_pass_popup,submit_ll,submit_btn,bookings_btn;
    ImageView edit_profile,changepw_close_btn,forgot_close;
    MainActivity mainActivity;
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String write;
    String lname,block,street,house,flat,floor;
    TextView st_login;
    public static AccountFragment newInstance(int someInt) {
        AccountFragment myFragment = new AccountFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.account_screen,container,false);
        Session.forceRTLIfSupported(getActivity());
        mainActivity = (MainActivity) getActivity();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        login_email = (EditText) view.findViewById(R.id.login_email);
        login_password = (EditText) view.findViewById(R.id.login_password);
        user_fname = (EditText) view.findViewById(R.id.user_fname);
        user_lname = (EditText) view.findViewById(R.id.user_lname);
        email_text = (EditText) view.findViewById(R.id.email_text);
        password_text = (EditText) view.findViewById(R.id.password_text);
        user_phone = (EditText) view.findViewById(R.id.user_phone);
        old_pass = (EditText) view.findViewById(R.id.old_pass);
        new_pass = (EditText) view.findViewById(R.id.new_pass);
        confirm_pass = (EditText) view.findViewById(R.id.confirm_pass);
        forgot_email = (EditText) view.findViewById(R.id.forgot_email);
        login_screen = (LinearLayout) view.findViewById(R.id.login_screen);
        login_ll = (LinearLayout) view.findViewById(R.id.login_ll);
        create_acc_ll = (LinearLayout) view.findViewById(R.id.create_acc_ll);
        password_ll = (LinearLayout) view.findViewById(R.id.password_ll);
        logout_ll = (LinearLayout) view.findViewById(R.id.logout_ll);
        change_pass_popup = (LinearLayout) view.findViewById(R.id.change_pass_popup);
        forgot_pass_popup = (LinearLayout) view.findViewById(R.id.forgot_pass_popup);
        bookings_btn = (LinearLayout) view.findViewById(R.id.bookings_btn);
        submit_ll = (LinearLayout) view.findViewById(R.id.submit_ll);
        submit_btn = (LinearLayout) view.findViewById(R.id.submit_btn);
        login_btn = (TextView) view.findViewById(R.id.login_btn);
        signin_btn = (TextView) view.findViewById(R.id.signin_btn);
        fp_btn = (TextView) view.findViewById(R.id.fp_btn);
        name = (TextView) view.findViewById(R.id.name);
        email = (TextView) view.findViewById(R.id.email);
        phone = (TextView) view.findViewById(R.id.phone);
        edit_profile = (ImageView) view.findViewById(R.id.edit_profile);
        changepw_close_btn = (ImageView) view.findViewById(R.id.close_btn);
        forgot_close = (ImageView) view.findViewById(R.id.forgot_close);
        flipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);


        st_login = (TextView) view.findViewById(R.id.st_login);


        login_btn.setText(Session.GetWord(getActivity(),"Login"));
        signin_btn.setText(Session.GetWord(getActivity(),"SignUp"));
        fp_btn.setText(Session.GetWord(getActivity(),"Forgot Password?"));
        st_login.setText(Session.GetWord(getActivity(),"Login"));




        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_btn.setTextColor(getResources().getColor(R.color.background));
                signin_btn.setTextColor(Color.parseColor("#aa000000"));
                flipper.setDisplayedChild(0);
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin_btn.setTextColor(getResources().getColor(R.color.background));
                login_btn.setTextColor(Color.parseColor("#aa000000"));
                flipper.setDisplayedChild(1);
            }
        });

        bookings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BookingsOrdersActivity.class);
                startActivity(intent);
            }
        });


        login_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });

        create_acc_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        fp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgot_pass_popup.setVisibility(View.VISIBLE);
            }
        });


        forgot_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgot_pass_popup.setVisibility(View.GONE);
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgot_pass();
            }
        });

        password_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_pass_popup.setVisibility(View.VISIBLE);
            }
        });

        if (Session.GetUserId(getActivity()).equals("-1")){
            mainActivity.toolbar.setVisibility(View.GONE);
            login_screen.setVisibility(View.VISIBLE);
        }else {
            mainActivity.toolbar.setVisibility(View.VISIBLE);
            login_screen.setVisibility(View.GONE);
        }

        logout_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session.SetUserId(getActivity(),"-1");
                mainActivity.mViewPager.setCurrentItem(0);
            }
        });

        submit_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_password();
            }
        });
        changepw_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_pass_popup.setVisibility(View.GONE);
            }
        });

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditProfile.class);
                intent.putExtra("fname",name.getText().toString());
                intent.putExtra("lname",lname);
                intent.putExtra("phone",phone.getText().toString());
                intent.putExtra("block",block);
                intent.putExtra("street",street);
                intent.putExtra("house",house);
                intent.putExtra("flat",flat);
                intent.putExtra("floor",floor);
                startActivity(intent);
            }
        });


        get_members();
        return view;
    }

    public void get_members(){
        final KProgressHUD hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();

        Ion.with(getContext())
                .load(Session.SERVERURL+"members.php")
                .setBodyParameter("member_id",Session.GetUserId(getContext()))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            JsonObject jsonObject = result.get(0).getAsJsonObject();
                            name.setText(jsonObject.get("fname").getAsString());
                            email.setText(jsonObject.get("email").getAsString());
                            phone.setText(jsonObject.get("phone").getAsString());
                            lname = jsonObject.get("lname").getAsString();
                            block = jsonObject.get("block").getAsString();
                            street = jsonObject.get("street").getAsString();
                            house = jsonObject.get("house").getAsString();
                            flat = jsonObject.get("flat").getAsString();
                            floor = jsonObject.get("floor").getAsString();

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void change_password(){


        String oldpass_string = old_pass.getText().toString();
        String newpass_string = new_pass.getText().toString();
        String confirmpass_string = confirm_pass.getText().toString();
        if (oldpass_string.equals("")){
            Toast.makeText(getContext(),"Please Enter Old Password",Toast.LENGTH_SHORT).show();
            old_pass.requestFocus();
        }else if (newpass_string.equals("")){
            Toast.makeText(getContext(),"Please Enter New Password",Toast.LENGTH_SHORT).show();
            new_pass.requestFocus();
        }else if (confirm_pass.equals("")){
            Toast.makeText(getContext(),"Please Enter Confirm Password",Toast.LENGTH_SHORT).show();
            confirm_pass.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(getContext())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(getContext())
                    .load(Session.SERVERURL + "change-password.php")
                    .setBodyParameter("member_id", Session.GetUserId(getContext()))
                    .setBodyParameter("opassword", oldpass_string)
                    .setBodyParameter("password",newpass_string)
                    .setBodyParameter("cpassword",confirmpass_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                if (result.get("status").getAsString().equals("Success")){
                                    Toast.makeText(getContext(),result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                    getActivity().onBackPressed();
                                }else {
                                    Toast.makeText(getContext(),result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }

                        }
                    });

        }
    }


    public void login(){
        String email_string = login_email.getText().toString();
        String password_string = login_password.getText().toString();
        if (email_string.equals("")){
            Toast.makeText(getActivity(),"Please Enter Your Email",Toast.LENGTH_SHORT).show();
            login_email.requestFocus();
        }else if (password_string.equals("")){
            Toast.makeText(getActivity(),"Please Enter Your Password",Toast.LENGTH_SHORT).show();
            login_password.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(getActivity())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVERURL + "login.php")
                    .setBodyParameter("email", email_string)
                    .setBodyParameter("password", password_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                Log.e("loginresult", result.toString());
                                if (result.get("status").getAsString().equals("Success")) {
                                    Session.SetUserId(getActivity(), result.get("member_id").getAsString());
                                    Log.e("member_id", result.get("member_id").toString());
                                    //Toast.makeText(getActivity(),result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.putExtra("act","1");
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getActivity(), result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                        }
                    });
        }
    }


    public void register(){

        String fname_string = user_fname.getText().toString();
        String lname_string = user_lname.getText().toString();
        String email_string = email.getText().toString();
        String password_string = password_text.getText().toString();
        String phone_string = user_phone.getText().toString();
        if (fname_string.equals("")){
            Toast.makeText(getActivity(),"Please Enter Your First Name",Toast.LENGTH_SHORT).show();
            user_fname.requestFocus();
        }else if (lname_string.equals("")){
            Toast.makeText(getActivity(),"Please Enter Your Last Name",Toast.LENGTH_SHORT).show();
            user_lname.requestFocus();
        }else if (email_string.equals("")){
            Toast.makeText(getActivity(),"Please Enter Your Email",Toast.LENGTH_SHORT).show();
            email_text.requestFocus();
        }else if (password_string.equals("")){
            Toast.makeText(getActivity(),"Please Enter Your Password",Toast.LENGTH_SHORT).show();
            password_text.requestFocus();
        }else if (phone_string.equals("")){
            Toast.makeText(getActivity(),"Please Enter Your Phone",Toast.LENGTH_SHORT).show();
            user_phone.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(getActivity())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVERURL + "member.php")
                    .setBodyParameter("fname", fname_string)
                    .setBodyParameter("lname", lname_string)
                    .setBodyParameter("email", email_string)
                    .setBodyParameter("password", password_string)
                    .setBodyParameter("phone", phone_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                Log.e("loginresult", result.toString());
                                if (result.get("status").getAsString().equals("Success")) {
                                    Toast.makeText(getActivity(), result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                    flipper.setDisplayedChild(0);
                                } else {
                                    Toast.makeText(getActivity(), result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                        }
                    });
        }
    }


    public void forgot_pass(){
        String email_string = forgot_email.getText().toString();
        if (email_string.equals("")){
            Toast.makeText(getActivity(), "Please Enter email", Toast.LENGTH_SHORT).show();
        }else if (!email_string.matches(emailPattern)){
            Toast.makeText(getActivity(), "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
        }else {
            final KProgressHUD hud = KProgressHUD.create(getActivity())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVERURL + "forget-password.php")
                    .setBodyParameter("email", email_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                if (result.get("status").getAsString().equals("Success")) {
                                    Toast.makeText(getActivity(), result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                    getActivity().finish();
                                } else {
                                    Toast.makeText(getActivity(), result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
        }
    }
}

