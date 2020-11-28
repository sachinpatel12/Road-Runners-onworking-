package com.example.bullrent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static int SPLASH_SCREEN=3000;

    ImageView splashImg;
    TextView splashText1;
    Animation top_animation,bottom_animatiom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hide status bar
        getSupportActionBar().hide();
        //hide notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        top_animation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_animatiom=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        splashImg=findViewById(R.id.splashImage);
        splashText1=findViewById(R.id.splashText);


        splashImg.setAnimation(top_animation);
        splashText1.setAnimation(bottom_animatiom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //animation after splash activity
                //Call next screen

                Intent intent=new Intent(MainActivity.this,Login.class);

                // Attach all the elements those you want to animate in design
                Pair[]pairs=new Pair[2];
                pairs[0]=new Pair<View, String>(splashImg,"logo_image");
                pairs[1]=new Pair<View, String>(splashText1,"logo_text");
                //wrap the call in API level 21 or higher
                if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                    finish();
                }

            }
        },SPLASH_SCREEN);

        //startActivity(new Intent(getApplicationContext(),Login.class));
        //finish();//back button ke bad wapas splash me nahi aayega

    }


    }
