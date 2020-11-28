package com.example.bullrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dummy_data extends AppCompatActivity {
    TextView tvname, tvuser, tvmobile, tvgmail, tvpassword;
    EditText gmailput,passput;
    Button showbtn ,gobtn;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_data);
        tvname = findViewById(R.id.namev);
        tvuser = findViewById(R.id.usernamev);
        tvmobile = findViewById(R.id.mobilev);
        tvgmail = findViewById(R.id.gmailv);
        tvpassword = findViewById(R.id.passwordv);
        showbtn=findViewById(R.id.showbtn);
        gobtn=findViewById(R.id.gobtn);
        gmailput=findViewById(R.id.writemail);
        passput=findViewById(R.id.writepass);


        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=getIntent();
                String putmail=intent.getStringExtra("emailid");
                tvgmail.setText(putmail);

                /*final String typedgmail=gmailput.getText().toString();
                final String typedpass=passput.getText().toString();

                reff=FirebaseDatabase.getInstance().getReference().child("users").child(typedgmail);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       String putname=dataSnapshot.child("name").getValue().toString();
                        String putuser=dataSnapshot.child("username").getValue().toString();
                        String putmobileno=dataSnapshot.child("phonno").getValue().toString();
                        String putgmail=dataSnapshot.child("email").getValue().toString();
                        String putpassword=dataSnapshot.child("pass").getValue().toString();
                        tvname.setText(putname);
                        tvuser.setText(putuser);
                        tvmobile.setText(putmobileno);
                        tvgmail.setText(putgmail);
                        tvpassword.setText(putpassword);
                        if(putgmail.equals(typedgmail) && putpassword.equals(typedpass))
                        {
                            Intent intent=new Intent(dummy_data.this,UserProfile.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(dummy_data.this, "not matched", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

            }
        });
    }

}