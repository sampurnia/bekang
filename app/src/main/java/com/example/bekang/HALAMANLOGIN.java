package com.example.bekang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HALAMANLOGIN extends AppCompatActivity {

    private EditText Name;
    private EditText Pasword;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanlogin);
        Name =(EditText)findViewById(R.id.etName);
        Pasword =(EditText)findViewById(R.id.etPasword);
        Info =(TextView)findViewById(R.id.tvInfo);
        Login =(Button)findViewById(R.id.btnlogin);
        Info.setText("NO OF ATTEMP");

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user =firebaseAuth.getCurrentUser();
        progressDialog= new ProgressDialog(this);


        if(user !=null) {
            finish();
            startActivity(new Intent(HALAMANLOGIN.this, HALAMANUTAMA.class));
        }

        userRegistration = (TextView)findViewById(R.id.tvRegister);
        forgotPassword = (TextView)findViewById(R.id.tvForgotPassword);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Pasword.getText().toString());
            }
        });
        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HALAMANLOGIN.this, HALAMANDAFTAR.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HALAMANLOGIN.this, HALAMANPASSWORD.class));
            }
        });


    }
    private void validate(String userName, String userPassword){

        progressDialog.setMessage("You Must Be Cute to Verifed");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               progressDialog.dismiss();
               checkEmailVerification();
           }
           else{
               Toast.makeText(HALAMANLOGIN.this, "Login Failed", Toast.LENGTH_SHORT).show();
               counter--;
               Info.setText("NO OF ATTEMP " + counter);
               progressDialog.dismiss();
               if (counter == 0){
                   Login.setEnabled(false);
               }
           }
            }
        });

    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser= firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            startActivity(new Intent(HALAMANLOGIN.this , HALAMANUTAMA.class));
        }
        else {
            Toast.makeText(HALAMANLOGIN.this, "Verify your Email please", Toast.LENGTH_SHORT);
            firebaseAuth.signOut();

        }
    }

}
