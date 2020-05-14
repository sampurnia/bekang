package com.example.bekang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class HALAMANSTATUS extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button durlap;
    private Button durlap1;
    private AnimationDrawable animationDrawable;
    private AnimationDrawable animationDrawable1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanstatus);
        firebaseAuth=FirebaseAuth.getInstance();

        durlap = (Button) findViewById(R.id.btnDurlap);
        durlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), HALAMANMONITORING.class);
                startActivity(i);
                finish();
            }
        });
        durlap.setBackgroundResource(R.drawable.bggreen);
        animationDrawable = (AnimationDrawable)durlap.getBackground();

        durlap1 = (Button) findViewById(R.id.btnDurlap2);
        durlap1.setBackgroundResource(R.drawable.bggreen);
        animationDrawable1 = (AnimationDrawable)durlap1.getBackground();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animationDrawable.start();
        animationDrawable1.start();
    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(HALAMANSTATUS.this, HALAMANLOGIN.class));
    }
    private void Refresh(){
        Intent intent = new Intent(HALAMANSTATUS.this, HALAMANSTATUS.class);
        startActivity(intent);
        finish();
    }
    private void Info(){
        Intent intent = new Intent(HALAMANSTATUS.this, HALAMANINFO.class);
        startActivity(intent);
        finish();
    }
    private void Navigation(){
        Intent intent = new Intent(HALAMANSTATUS.this, HALAMANUTAMA.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.LogoutMenu :
                Logout();
                break;
            case R.id.RefreshMenu :
                Refresh();
                break;
            case R.id.InfoMenu :
                Info();
                break;
            case R.id.NavigationMenu :
                Navigation();
                break;
        }
        return super.onOptionsItemSelected(item); }

}
