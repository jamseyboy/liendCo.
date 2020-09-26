package com.lalringsan.liendonews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lalringsan.liendonews.user_mgmt.firstPage;
import com.lalringsan.liendonews.user_mgmt.signup;

public class MainActivity extends AppCompatActivity {
    Button regBtn,loginBtn;
    TextInputLayout emailField,passwordField;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser.isEmailVerified()) {
            Toast.makeText(MainActivity.this,"Welcome User",Toast.LENGTH_SHORT).show();
            goTofirstPage();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();

        emailField=findViewById(R.id.email_login);
        passwordField=findViewById(R.id.password_login);

        regBtn= findViewById(R.id.register_button);
        loginBtn=findViewById(R.id.login_button);
        onStart();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean stats=validateEmail();
                if(stats){
                    Toast.makeText(MainActivity.this,"Email Validated",Toast.LENGTH_SHORT).show();
                    login();
                }

            }
        });
    }

    public void register(){
        Intent intent=new Intent(this, signup.class);
        startActivity(intent);
    }

    private void goTofirstPage(){
        Intent firstPageIntent=new Intent(this, firstPage.class);
        startActivity(firstPageIntent);
    }


    public void login(){
        try{
            String validPassword=passwordField.getEditText().getText().toString().trim();
            String validEmail=emailField.getEditText().getText().toString().trim();
            Toast.makeText(MainActivity.this,validEmail+""+validPassword, Toast.LENGTH_SHORT).show();

            mAuth.signInWithEmailAndPassword(validEmail,validPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG,"signInWithEmail:success");
                                FirebaseUser user= mAuth.getCurrentUser();
                                goTofirstPage();
                            }
                            else{
                                Log.w(TAG, "signInWithEmail:failure",task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validateEmail(){
        String email=emailField.getEditText().getText().toString().trim();
        if(email.isEmpty()){
            emailField.setError("Field can't be empty");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailField.setError("Enter valid email Address");
            return false;
        }
        else{
            return true;
        }

    }
}