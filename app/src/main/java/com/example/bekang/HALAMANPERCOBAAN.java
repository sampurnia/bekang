package com.example.bekang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bekang.firebaseauth.HALAMANDAFTAR;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HALAMANPERCOBAAN extends AppCompatActivity {

    DatabaseReference refstatus;
    ImageView imgStatusOFF;
    ImageView imgStatusOn;
    ImageView imgStatusMain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanpercobaan);
        imgStatusOFF=(ImageView) findViewById(R.id.imgOFF);
        imgStatusOn=(ImageView) findViewById(R.id.imgON);
        imgStatusMain=(ImageView) findViewById(R.id.imgMAIN);

        refstatus= FirebaseDatabase.getInstance().getReference().child("mobil1").child("status");
        refstatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String status;
                if(dataSnapshot.child("stat").getValue() != null) {
                    status = dataSnapshot.child("stat").getValue().toString();

                    switch (status){
                        case "1":
                            StatusOn();
                            break;
                        case "2":
                            StatusMain();
                            break;
                        case "3":
                            StatusOFF();
                            break;
                        default:
                            Toast.makeText(HALAMANPERCOBAAN.this, "4", Toast.LENGTH_LONG).show();
                            break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void StatusOn(){
        imgStatusOn.setBackgroundResource(R.drawable.bggreen);
        AnimationDrawable animationON = (AnimationDrawable) imgStatusOn.getBackground();
        imgStatusOFF.setVisibility(View.GONE);
        imgStatusOn.setVisibility(View.VISIBLE);
        imgStatusMain.setVisibility(View.GONE);
        animationON.start();
    }

    public void StatusMain(){
        imgStatusMain.setBackgroundResource(R.drawable.bgyellow);
        AnimationDrawable animationMAIN = (AnimationDrawable) imgStatusMain.getBackground();
        imgStatusMain.setVisibility(View.VISIBLE);
        imgStatusOn.setVisibility(View.GONE);
        imgStatusOFF.setVisibility(View.GONE);
        animationMAIN.start();
        Toast.makeText(HALAMANPERCOBAAN.this, "Maintenance", Toast.LENGTH_LONG).show();
    }
    public void StatusOFF(){
        imgStatusOFF.setBackgroundResource(R.drawable.bgred);
        AnimationDrawable animationOFF = (AnimationDrawable) imgStatusOFF.getBackground();
        imgStatusOFF.setVisibility(View.VISIBLE);
        imgStatusOn.setVisibility(View.GONE);
        imgStatusMain.setVisibility(View.GONE);
        animationOFF.start();
        Toast.makeText(HALAMANPERCOBAAN.this, "OFF", Toast.LENGTH_LONG).show();
    }
}
