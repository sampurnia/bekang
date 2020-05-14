package com.example.bekang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class HALAMANINFO extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamaninfo);
        firebaseAuth=FirebaseAuth.getInstance();
    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(HALAMANINFO.this, HALAMANLOGIN.class));
    }
    private void Refresh(){
        Intent intent = new Intent(HALAMANINFO.this, HALAMANINFO.class);
        startActivity(intent);
        finish();
    }
    private void List(){
        Intent intent = new Intent(HALAMANINFO.this, HALAMANSTATUS.class);
        startActivity(intent);
        finish();
    }
    private void Navigation(){
        Intent intent = new Intent(HALAMANINFO.this, HALAMANUTAMA.class);
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
            case R.id.ListMenu :
                List();
                break;
            case R.id.NavigationMenu :
                Navigation();
                break;
        }
        return super.onOptionsItemSelected(item); }
}