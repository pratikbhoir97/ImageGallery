package com.example.galleryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullScreenImageActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        String url = getIntent().getStringExtra("reular");
        imageView = findViewById(R.id.main_image);

        Glide.with(this).load(url).fitCenter().into(imageView);
    }
}