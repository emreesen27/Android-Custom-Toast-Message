package com.emreesen.sntoastexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.emreesen.sntoast.SnToast;
import com.emreesen.sntoast.Type;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void normalToast(View view) {
        Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.example);
        new SnToast.Standard()
                .context(MainActivity.this)
                .type(Type.SUCCESS)
                .message("Success !")
                //.typeface(type) Optional Default: sans-serif-condensed
                //.cancelable(false or true) Optional Default: False
                // .iconSize(int size) Optional Default: 34dp
                // .textSize(int size) Optional Default 18sp
                // .animation(false or true) Optional Default: True
                // .duration(int ms) Optional Default: 3000ms
                .build();
    }

    public void customToast(View view) {
        Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.example);
        new SnToast.Custom()
                .context(MainActivity.this)
                .backgroundColor(R.color.teal_200)
                .textColor(R.color.white)
                .icon(R.drawable.ic_launcher_foreground)
                .message("Custom !!!")
                .typeface(type)
                //.typeface(type) Optional Default: sans-serif-condensed
                //.cancelable(false or true) Optional Default: False
                // .iconSize(int size) Optional Default: 34dp
                // .textSize(int size) Optional Default 18sp
                // .animation(false or true) Optional Default: True
                // .duration(int ms) Optional Default: 3000ms
                .build();
    }
}