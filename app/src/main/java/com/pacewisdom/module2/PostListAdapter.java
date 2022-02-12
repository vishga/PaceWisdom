package com.pacewisdom.module2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacewisdom.R;
import com.pacewisdom.module1.Contact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListHolder> {

    public List<PosModel> results = new ArrayList<>();
    Context context;

    public PostListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PostListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);

        return new PostListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListHolder holder, int position) {
        PosModel posModel = results.get(position);

        holder.title_tv.setText(posModel.getTitle());


        Picasso.get()
                .load(posModel.getThumbnailUrl())
                .resize(150, 150)
                .centerCrop()
                .into(holder.thumb);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<PosModel> results) {

        this.results = results;
        notifyDataSetChanged();
    }


   public class PostListHolder extends RecyclerView.ViewHolder {

        private TextView title_tv;
        ImageView thumb;

        public PostListHolder(@NonNull View itemView) {
            super(itemView);

            title_tv = itemView.findViewById(R.id.title_tv);
            thumb = itemView.findViewById(R.id.thumb);
        }
    }
}
