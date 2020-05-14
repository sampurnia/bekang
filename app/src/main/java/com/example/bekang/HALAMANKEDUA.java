package com.example.bekang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class HALAMANKEDUA extends AppCompatActivity {
    private int SLEEP_TIMER = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        setContentView(R.layout.activity_halamankedua);
        getSupportActionBar().hide();
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

    }

private class LogoLauncher extends Thread {
    public void run (){
        try {
            sleep(1000*SLEEP_TIMER);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Intent intent = new Intent(HALAMANKEDUA.this, HALAMANLOGIN.class);
        startActivity(intent);
        HALAMANKEDUA.this.finish();


    }
}
}