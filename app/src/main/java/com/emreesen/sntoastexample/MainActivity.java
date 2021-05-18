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
        new SnToast.Standard().
                context(MainActivity.this)
                .type(Type.SUCCESS)
                .message("Success !")
                .build();
    }

    public void customToast(View view) {
    }
}