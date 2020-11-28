package com.example.bullrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText regName, regUserName, regMobile, regGmail, regPass;
    Button registered, alreadyAcc;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        registered = findViewById(R.id.register);
        alreadyAcc = findViewById(R.id.alreadyhave);
        regName = findViewById(R.id.sfullname);
        regUserName = findViewById(R.id.suserid);
        regGmail = findViewById(R.id.sgmail);
        regMobile = findViewById(R.id.smobile);
        regPass = findViewById(R.id.spassid);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        alreadyAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);

                startActivity(intent);
                finish();
            }
        });

    }

    private boolean validateName() {
        String val = regName.getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field can not be empty");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String usernameval = regUserName.getText().toString();
        String noWhiteSpace ="\\A\\w{4,20}\\z";
        if (usernameval.isEmpty()) {
            regUserName.setError("Field can not be empty");
            return false;
        } else if(usernameval.length()>15) {
            regUserName.setError("username to long");
            return true;
        }
        else if(!usernameval.matches(noWhiteSpace)) {
            regUserName.setError("Remove white spaces");

            return true;
        }
        else
        {
            regUserName.setError(null);
            return true;
        }

    }
    private boolean validatePhoneno() {
        String valnum = regMobile.getText().toString();
        if (valnum.isEmpty()) {
            regName.setError("Field can not be empty");
            return false;
        } else if (valnum.length() > 10 && valnum.length() < 10) {
            regName.setError("enter valid Phone Number");
            return false;
        } else {
            regMobile.setError(null);
            return true;
        }
    }
    private boolean validateGmail() {
        String valgmail = regGmail.getText().toString();


        if (valgmail.isEmpty()) {
            regGmail.setError("Field can not be empty");
            return false;
        }

        else {
            regGmail.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String valpass = regPass.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
               // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{5,}" +               //at least 4 characters
                "$";
        if (valpass.isEmpty()) {
            regPass.setError("Field can not be empty");
            return false;
        }
        else if(valpass.length()<5){
            Toast.makeText(this, "password to sort", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if(!valpass.matches(passwordVal))
        {
            regPass.setError("password is to weak");
            return true;
        }
        else {
            regPass.setError(null);
            return true;
        }
    }


    public void registerUser(View view) {
        if (!validateName() || !validatePassword() || !validateGmail() || !validatePhoneno() || !validateUsername()) {
            return;
        } else {
            String name = regName.getText().toString();
            String username = regUserName.getText().toString();
            String email = regGmail.getText().toString();
            String phonno = regMobile.getText().toString();
            String password = regPass.getText().toString();

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");

            UserHelperClass userHelperClass = new UserHelperClass(name, username, email, phonno, password);
            reference.child(email).setValue(userHelperClass);//child har user ka alag se ref banayega

            Intent intent=new Intent(getApplicationContext(),Login.class);
            Toast.makeText(this, "registered", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
    }
}