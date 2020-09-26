package com.lalringsan.liendonews.user_mgmt;

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

import com.lalringsan.liendonews.R;



public class signup extends AppCompatActivity {
    private static final String TAG = "signup";
   private Button submitBtn, backBtn;
    private FirebaseAuth mAuth;
    TextInputLayout emailField,passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        submitBtn =findViewById(R.id.signupSubmitBtn);
        backBtn =findViewById(R.id.signupBackBtn);
        emailField=findViewById(R.id.signupEmail);
        passwordField=findViewById(R.id.signupPassword);

        mAuth =FirebaseAuth.getInstance();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validEmail = validateEmail();
                String validPassword=passwordField.getEditText().getText().toString().trim();
                if(validEmail!=null) {
                    mAuth.createUserWithEmailAndPassword(validEmail, validPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signup.this, "Authentication Sucessful",
                                    Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"User Sign up Successful");
                            FirebaseUser user=mAuth.getCurrentUser();
                            goTofirstPage();
                            //update ui
                        }else{
                            System.out.println("not empty");
                            Log.w(TAG,"Sign up failed",task.getException());
                            Toast.makeText(signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        }
                    });

                }
                else{
                    Toast.makeText(signup.this, "Error",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String validateEmail(){
        String email=emailField.getEditText().getText().toString().trim();
        if(email.isEmpty()){
            emailField.setError("Field can't be empty");
            return null;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailField.setError("Enter valid email Address");
            return null;
        }
        else{
            return email;
        }

    }

    private void goTofirstPage(){
        Intent firstPageIntent=new Intent(this,firstPage.class);
        startActivity(firstPageIntent);
    }


    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser.isEmailVerified())
        {
            goTofirstPage();
        }


    }

}