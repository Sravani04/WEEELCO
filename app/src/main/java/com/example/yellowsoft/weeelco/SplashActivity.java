package com.example.yellowsoft.weeelco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by yellowsoft on 8/2/18.
 */

public class SplashActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        get_words();
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                intent.putExtra("act","0");
//                startActivity(intent);
//                finish();
//            }
//        }, 1500);
    }


    public void get_words(){
        Ion.with(this)
                .load(Session.SERVERURL+"words-json-android.php")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {
                            Session.SetEnWords(SplashActivity.this, result.get("en").getAsJsonObject().toString());
                            Session.SetArWords(SplashActivity.this, result.get("ar").getAsJsonObject().toString());
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            intent.putExtra("act","0");
                            startActivity(intent);
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
