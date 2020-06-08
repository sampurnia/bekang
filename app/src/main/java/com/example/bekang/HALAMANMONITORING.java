package com.example.bekang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bekang.firebaseauth.HALAMANLOGIN;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
TOLONG DONG INI DIJADIIN FRAGMENT TIAP TRUCK
COBAIN 4 DULU AJA GAES
 */



public class HALAMANMONITORING extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;

    AnimationDrawable animationDrawable;
    AnimationDrawable animationDrawable1;
    AnimationDrawable animationDrawable2;
    private FirebaseAuth firebaseAuth;
    TextView a,b,c;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanmonitoring);
        firebaseAuth = FirebaseAuth.getInstance();
        a=(TextView)findViewById(R.id.tvWater);
        b=(TextView)findViewById(R.id.tvTemp);
        c=(TextView)findViewById(R.id.tvFuel);


        imageView=(ImageView) findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.battery);
        animationDrawable =(AnimationDrawable) imageView.getBackground();

        imageView1=(ImageView) findViewById(R.id.imageView11);
        imageView1.setBackgroundResource(R.drawable.batteryred);
        animationDrawable1 =(AnimationDrawable) imageView1.getBackground();

        imageView2=(ImageView) findViewById(R.id.imageView12);
        imageView2.setBackgroundResource(R.drawable.batterygreen);
        animationDrawable2 =(AnimationDrawable) imageView2.getBackground();


        reff= FirebaseDatabase.getInstance().getReference().child("mobil1").child("sensor");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fuel = dataSnapshot.child("fuel").getValue().toString();
                String temp = dataSnapshot.child("temp").getValue().toString();
                String water = dataSnapshot.child("water").getValue().toString();
                a.setText(fuel);
                b.setText(temp);
                c.setText(water);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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
        reff= FirebaseDatabase.getInstance().getReference().child("mobil1").child("sensor");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fuel = dataSnapshot.child("fuel").getValue().toString();
                String temp = dataSnapshot.child("temp").getValue().toString();
                String water = dataSnapshot.child("water").getValue().toString();
                a.setText(fuel);
                b.setText(temp);
                c.setText(water);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
