package com.example.allinonedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity<Final> extends AppCompatActivity {

    private TextView mainHeading;
    private ImageView imageAllInOne;
    private ImageView imageFacebook;
    private ImageView imageInstagram;
    private ImageView imageYoutube;
    private ImageView imageTwitter;
    private TextView textCreator;
    final String creator = "#2 Product of Golden and Co.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainHeading = (TextView) findViewById(R.id.mainHeading);
        imageAllInOne = (ImageView) findViewById(R.id.imageAllInOne);
        imageFacebook = (ImageView) findViewById(R.id.imageFacebook);
        imageInstagram = (ImageView) findViewById(R.id.imageInstagram);
        imageYoutube = (ImageView) findViewById(R.id.imageYoutube);
        imageTwitter = (ImageView) findViewById(R.id.imageTwitter);
        textCreator = (TextView) findViewById(R.id.textCreator);
        textCreator.setText(creator);

//        //External storage permission for saving file
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
//        {
//            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
//            {
//                Log.d("permission","Permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
//                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                requestPermissions(permissions , 1);
//            }
//        }

        //Move from main to another activity
        imageFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this , SecondActivity.class);
                startActivity(i2);
            }
        });

        imageInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MainActivity.this , ThirdActivity.class);
                startActivity(i3);
            }
        });

        imageYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(MainActivity.this , FourthActivity.class);
                startActivity(i4);
            }
        });

        imageTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(MainActivity.this , FifthActivity.class);
                startActivity(i5);
            }
        });

    }
}