package com.example.moneysavingstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Hooks
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtnLogin, regBtnSignup;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //Form Error Handling and Validation
    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if(val.isEmpty()) {
            regName.setError("Field cannot be empty.");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();

        if(val.isEmpty()) {
            regUsername.setError("Field cannot be empty.");
            return false;
        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();

        if(val.isEmpty()) {
            regEmail.setError("Field cannot be empty.");
            return false;
        }
        else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if(val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty.");
            return false;
        }
        else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();

        if(val.isEmpty()) {
            regPassword.setError("Field cannot be empty.");
            return false;
        }
        else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id. phoneNo);
        regPassword = findViewById(R.id.password);
        regBtnSignup = findViewById(R.id.btnSignup);
        regBtnLogin = findViewById(R.id.btnGoLogin);

        // Calls Login activity if the user changes their mind about signing up
        regBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        //Save data in Firebase
        regBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance("https://doineedit-f0389-default-rtdb.asia-southeast1.firebasedatabase.app/");
                reference = rootNode.getReference("users");

                //Form Validation Requirements. User not created unless all requirements are met.
                if(!validateName() |!validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername())
                {
                    return;
                }

                //Get all the values
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);

                reference.child(username).setValue(helperClass);

                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Signed Up successful." , Toast.LENGTH_SHORT ).show();
            }
        });
    }
}