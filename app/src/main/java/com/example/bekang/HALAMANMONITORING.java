package com.example.bekang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class HALAMANMONITORING extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;

    AnimationDrawable animationDrawable;
    AnimationDrawable animationDrawable1;
    AnimationDrawable animationDrawable2;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanmonitoring);
        firebaseAuth = FirebaseAuth.getInstance();

        imageView=(ImageView) findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.battery);
        animationDrawable =(AnimationDrawable) imageView.getBackground();

        imageView1=(ImageView) findViewById(R.id.imageView11);
        imageView1.setBackgroundResource(R.drawable.batteryred);
        animationDrawable1 =(AnimationDrawable) imageView1.getBackground();

        imageView2=(ImageView) findViewById(R.id.imageView12);
        imageView2.setBackgroundResource(R.drawable.batterygreen);
        animationDrawable2 =(AnimationDrawable) imageView2.getBackground();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animationDrawable.start();
        animationDrawable1.start();
        animationDrawable2.start();
    }
    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(HALAMANMONITORING.this, HALAMANLOGIN.class));
    }

    private void Refresh() {
        Intent intent = new Intent(HALAMANMONITORING.this, HALAMANMONITORING.class);
        startActivity(intent);
        finish();
    }

    private void List() {
        Intent intent = new Intent(HALAMANMONITORING.this, HALAMANSTATUS.class);
        startActivity(intent);
        finish();
    }

    private void Info() {
        Intent intent = new Intent(HALAMANMONITORING.this, HALAMANINFO.class);
        startActivity(intent);
        finish();
    }

    private void Navigation() {
        Intent intent = new Intent(HALAMANMONITORING.this, HALAMANUTAMA.class);
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
        switch (item.getItemId()) {
            case R.id.LogoutMenu:
                Logout();
                break;
            case R.id.RefreshMenu:
                Refresh();
                break;
            case R.id.ListMenu:
                List();
                break;
            case R.id.InfoMenu:
                Info();
                break;
            case R.id.NavigationMenu:
                Navigation();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
