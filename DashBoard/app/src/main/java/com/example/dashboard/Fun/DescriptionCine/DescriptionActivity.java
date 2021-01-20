package com.example.dashboard.Fun.DescriptionCine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dashboard.Fun.CineAdapter.MovieAdapter;
import com.example.dashboard.Fun.CineModel.MovieModelClass;
import com.example.dashboard.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DescriptionActivity extends AppCompatActivity {
    ImageView imageView;
    TextView name;
    TextView description;
    TextView rate;

    String textDescription;
    String textRate;
    String playername;
    String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        imageView=findViewById(R.id.image);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        rate = findViewById(R.id.rate);

        playername=getIntent().getStringExtra("name");
        textDescription=getIntent().getStringExtra("description");
        textRate=getIntent().getStringExtra("rate");
        image=getIntent().getStringExtra("image");
        name.setText(playername);
        description.setText(textDescription);
        rate.setText(textRate);


        Picasso.get().load(image).into(imageView);
    }

}