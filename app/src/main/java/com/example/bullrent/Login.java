package com.example.bullrent;
//chanel coffee with tea bukls rent

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
     ImageView image;
     TextView wel,hint;
    EditText gemail,password;
    Button goSignin,login;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        goSignin=findViewById(R.id.logsignup);
        image=findViewById(R.id.logimage);
        wel=findViewById(R.id.logo_name);
       hint=findViewById(R.id.slogan_name);
        gemail=findViewById(R.id.loggmailid);
        login=findViewById(R.id.loggo);
        password=findViewById(R.id.logpassid);
        progress=findViewById(R.id.progressbar);
        progress.setVisibility(View.GONE);




        goSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,SignUp.class);
                Pair[] pairs=new Pair[7];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(wel, "logo_text");
                pairs[2] = new Pair<View, String>(hint, "downText");
                pairs[3] = new Pair<View, String>(gemail, "gemail_logo");
                pairs[4] = new Pair<View, String>(password, "pass_logo");
                pairs[5] = new Pair<View, String>(login, "go_logo");
                pairs[6] = new Pair<View, String>(goSignin, "user_signup_logo");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle());
            }
        }
    });
 }

   private boolean validateGmailid()
   {
       String mail=gemail.getText().toString();
       if(mail.isEmpty())
       {
           gemail.setError("enter gemail id");
           return false;
       }
       else
       {
           gemail.setError(null);
           return true;

       }

   }

    private boolean validatepassword()
    {
        String passr=password.getText().toString();
        if(passr.isEmpty())
        {
            password.setError("enter password ");
            return false;
        }
        else
        {
            password.setError(null);
            return true;

        }

    }
    public void logInUser(View view)
    {
        if(!validateGmailid() || !validatepassword())
        {
            return;
        }
        else
        {
            isUser();
        }
    }

    private void isUser() {
        progress.setVisibility(View.VISIBLE);

        final String userEnteredMail = gemail.getText().toString().trim();
        final String userEnteredPassword = password.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users").child(userEnteredMail);

        //Query checkUser=reference.orderByChild("email").equalTo(userEnteredMail);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    gemail.setError(null);
                    String passwordFromDB = dataSnapshot.child("pass").getValue().toString();
                    if(passwordFromDB.equals(userEnteredPassword))
                    {

                        gemail.setError(null);
                        String nameFromDB = dataSnapshot.child("name").getValue().toString();
                        String usernameFromDB = dataSnapshot.child("username").getValue().toString();
                        String phoneNoFromDB = dataSnapshot.child("phonno").getValue().toString();
                        String emailFromDB = dataSnapshot.child("email").getValue().toString();
                        progress.setVisibility(View.GONE);
                        //Toast.makeText(Login.this, nameFromDB, Toast.LENGTH_SHORT).show();
                        //String passwordFromDB = dataSnapshot.child(userEnteredMail).child("pass").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),UserProfile.class);
                        intent.putExtra("namefull", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("emailid", emailFromDB);
                        intent.putExtra("phoneNum", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                        finish();
                    }
                    //if password wrong
                    else {
                        progress.setVisibility(View.GONE);
                        password.setError("wrong password");
                        password.requestFocus();//pointin password

                    }
                }
                //if no user exixt of that mail
                else {
                    progress.setVisibility(View.GONE);
                    gemail.setError("no such user exist");
                    gemail.requestFocus();//pointing mail field
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        }
    }


