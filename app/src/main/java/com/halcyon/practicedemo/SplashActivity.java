package com.halcyon.practicedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void a(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void b(View v) {
        startActivity(new Intent(this, GridActivity.class));
    }

    public void c(View v) {
        startActivity(new Intent(this, StaggeredActivity.class));
    }

    public void d(View v) {
        startActivity(new Intent(this, WaterfallActivity.class));
    }
}
