package com.emreesen.sntoastexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.emreesen.sntoast.SnToast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void normalToast(View view) {
        SnToast.standard(MainActivity.this, getString(R.string.success), SnToast.ToastType.Success);
    }

    public void customToast(View view) {
        SnToast.custom(MainActivity.this,
                getString(R.string.custom),
                R.color.teal_200, R.color.white, R.drawable.ic_launcher_foreground);
    }
}