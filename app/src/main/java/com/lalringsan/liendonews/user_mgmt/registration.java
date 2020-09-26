package com.lalringsan.liendonews.user_mgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lalringsan.liendonews.R;

public class registration extends AppCompatActivity {

    TextInputLayout nameField, emailField, phoneNumberField, addressField;
    Button nextBtn,backBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        nameField =findViewById(R.id.name);
        emailField =findViewById(R.id.email);
        phoneNumberField =findViewById(R.id.phoneNumber);
        addressField =findViewById(R.id.address);

        nextBtn=findViewById(R.id.next_password);
        backBtn=findViewById(R.id.back_reg);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");

                String userName= nameField.getEditText().getText().toString();
                String userEmail= emailField.getEditText().getText().toString();
                String userPhoneNumber= phoneNumberField.getEditText().getText().toString();
                String userAddress= addressField.getEditText().getText().toString();

                userDto userdto= new userDto(userName,userEmail,userPhoneNumber,userAddress);

                reference.child(userPhoneNumber).setValue(userdto);
            }
        });

    }
}