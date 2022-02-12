package com.pacewisdom.module2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.pacewisdom.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class DetailsActivity extends AppCompatActivity {

    ImageView thumb_img;
    TextView tv_id,tv_albumId,tv_title;
    public String id="",title="",album_id="",thumb="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        thumb_img = findViewById(R.id.thumb_img);
        tv_id = findViewById(R.id.tv_id);
        tv_albumId = findViewById(R.id.tv_albumId);
        tv_title = findViewById(R.id.tv_title);

        if (getIntent()!=null) {
            id = getIntent().getStringExtra("id");
            album_id = getIntent().getStringExtra("album_id");
            thumb = getIntent().getStringExtra("thumb");
            title = getIntent().getStringExtra("title");
        }

        tv_id.setText("ID: "+id);
        tv_albumId.setText("Album ID:"+album_id);
        tv_title.setText("Title: "+title);

        Picasso.get()
                .load(thumb)
                .resize(150, 150)
                .centerCrop()
                .into(thumb_img);
    }
}