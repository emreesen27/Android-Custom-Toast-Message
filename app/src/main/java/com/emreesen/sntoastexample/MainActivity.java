package com.emreesen.sntoastexample;

import androidx.appcompat.app.AppCompatActivity;

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
        new SnToast.Standard()
                .context(MainActivity.this)
                .type(Type.SUCCESS)
                .message("Success !")
                // .animation(false or true) optional
                // .duration(int ms) optional
                .build();
    }

    public void customToast(View view) {
        new SnToast.Custom()
                .context(MainActivity.this)
                .backgroundColor(R.color.teal_200)
                .textColor(R.color.white)
                .icon(R.drawable.ic_launcher_foreground)
                // .animation(false or true) optional
                // .duration(int ms) optional
                .message("Custom !!!")
                .build();
    }
}