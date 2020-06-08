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
import android.widget.Toast;

import com.example.bekang.firebaseauth.HALAMANLOGIN;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HALAMANSTATUS extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button durlapOn;
    private Button durlapMain;
    private Button durlapOFF;
    DatabaseReference refstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanstatus);
        firebaseAuth=FirebaseAuth.getInstance();

        durlapOn = (Button) findViewById(R.id.btnDurlap);
        durlapMain = (Button) findViewById(R.id.btnDurlap2);
        durlapOFF = (Button) findViewById(R.id.btnDurlap3);




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
                            Toast.makeText(HALAMANSTATUS.this, "4", Toast.LENGTH_LONG).show();
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
        durlapOn.setBackgroundResource(R.drawable.bggreen);
        AnimationDrawable animationON = (AnimationDrawable) durlapOn.getBackground();
        durlapOFF.setVisibility(View.GONE);
        durlapMain.setVisibility(View.GONE);
        durlapOn.setVisibility(View.VISIBLE);
        animationON.start();
        Toast.makeText(HALAMANSTATUS.this, "ONLINE", Toast.LENGTH_LONG).show();

        durlapOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), HALAMANMONITORING.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void StatusMain(){
        durlapMain.setBackgroundResource(R.drawable.bgyellow);
        AnimationDrawable animationMAIN = (AnimationDrawable) durlapMain.getBackground();
        durlapMain.setVisibility(View.VISIBLE);
        durlapOFF.setVisibility(View.GONE);
        durlapOn.setVisibility(View.GONE);
        animationMAIN.start();
        Toast.makeText(HALAMANSTATUS.this, "MAINTENANCE", Toast.LENGTH_LONG).show();

        durlapMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), HALAMANMONITORING.class);
                startActivity(i);
                finish();
            }
        });

    }
    public void StatusOFF(){
        durlapOFF.setBackgroundResource(R.drawable.bgred);
        AnimationDrawable animationOFF = (AnimationDrawable) durlapOFF.getBackground();
        durlapOFF.setVisibility(View.VISIBLE);
        durlapMain.setVisibility(View.GONE);
        durlapOn.setVisibility(View.GONE);
        animationOFF.start();
        Toast.makeText(HALAMANSTATUS.this, "OFFLINE", Toast.LENGTH_LONG).show();

        durlapOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), HALAMANMONITORING.class);
                startActivity(i);
                finish();
            }
        });
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
