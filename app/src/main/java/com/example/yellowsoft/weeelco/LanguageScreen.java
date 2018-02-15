package com.example.yellowsoft.weeelco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yellowsoft on 14/2/18.
 */

public class LanguageScreen extends Activity {
    TextView english_btn,arabic_btn;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_screen);
        english_btn = (TextView) findViewById(R.id.english_btn);
        arabic_btn = (TextView) findViewById(R.id.arabic_btn);

        english_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session.SetLang(LanguageScreen.this,"en");
                Intent intent = new Intent(LanguageScreen.this,MainActivity.class);
                intent.putExtra("act","0");
                startActivity(intent);
                finish();
            }
        });

        arabic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session.SetLang(LanguageScreen.this,"ar");
                Intent intent = new Intent(LanguageScreen.this,MainActivity.class);
                intent.putExtra("act","0");
                startActivity(intent);
                finish();
            }
        });
    }
}
