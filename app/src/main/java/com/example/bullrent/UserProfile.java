package com.example.bullrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    TextView namefull,usernamefull;
    EditText name_user, mail_user, mobile_user, pass_user;
    //hold data
    String name_get,pass_get,email_get,phn_get,put_name;
    Button update_user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference= FirebaseDatabase.getInstance().getReference("users");
        //text
        namefull=findViewById(R.id.userfullnametop);
        usernamefull=findViewById(R.id.userusernametop);

        //btn
        update_user = findViewById(R.id.userUpdate);

        //edit
        name_user = findViewById(R.id.userupdatenamedown);
        mail_user = findViewById(R.id.userupdgmail);
        mobile_user = findViewById(R.id.userupdmobile);
        pass_user = findViewById(R.id.userupdpassword);


        showAllUserData();

    }

    private void showAllUserData() {
        Intent intent = getIntent();

         name_get = intent.getStringExtra("username");
         email_get = intent.getStringExtra("emailid");
         phn_get = intent.getStringExtra("phoneNum");
         pass_get = intent.getStringExtra("password");
         put_name=intent.getStringExtra("namefull");

        name_user.setText(name_get);
        mail_user.setText(email_get);
        mobile_user.setText(phn_get);
        pass_user.setText(pass_get);
        namefull.setText(put_name);
        usernamefull.setText(name_get);

    }
    public void update(View view){
        if(isNameChanged() || isPasswordChanged())
        {
            Toast.makeText(this, "Data Has Been Changed", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Data is same not  Changed", Toast.LENGTH_SHORT).show();
    }

    private boolean isNameChanged() {
        if(!name_get.equals(name_user.getText().toString())){
            reference.child(email_get).child("name").setValue(name_user.getText().toString());
            name_get=name_user.getText().toString();
            return true;
        }else{
            return false;
        }

    }
    private boolean isPasswordChanged() {
        if(!pass_get.equals(pass_user.getText().toString()))
        {
            reference.child(email_get).child("pass").setValue(pass_user.getText().toString());
            pass_get=pass_user.getText().toString();
            return true;
        }else{
            return false;
        }
    }
}