package com.matthew.librariesexamplemaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.matthew.librariesexamplemaps.features.main.MainActivity;

/**
 * Created by Matthew on 15/10/2017.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }


}
