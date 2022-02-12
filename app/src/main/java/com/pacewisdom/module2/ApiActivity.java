package com.pacewisdom.module2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pacewisdom.R;
import com.pacewisdom.module1.ContactActivity;
import com.pacewisdom.module1.ContactListAdapter;

import java.util.ArrayList;

public class ApiActivity extends AppCompatActivity {

    PostViewModel postViewModel;
    PostListAdapter adapter;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        postViewModel = new PostViewModel(this);
        initRecyclerView();

        postViewModel.getPostData().observe(this, new Observer<ArrayList<PosModel>>() {
            @Override
            public void onChanged(ArrayList<PosModel> posModels) {

                adapter.setResults(posModels);
                recycleOnClick(posModels);
            }
        });
    }

    private void initRecyclerView() {
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(ApiActivity.this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        adapter = new PostListAdapter(ApiActivity.this);
        recycler.setAdapter(adapter);
    }

    private void recycleOnClick(ArrayList<PosModel> list) {

        recycler.addOnItemTouchListener(new RecyclerOnClickListener(getApplicationContext(),
                recycler, new RecyclerOnClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(ApiActivity.this, DetailsActivity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("album_id", list.get(position).getAlbumId());
                intent.putExtra("thumb", list.get(position).getThumbnailUrl());
                intent.putExtra("title", list.get(position).getTitle());
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
}