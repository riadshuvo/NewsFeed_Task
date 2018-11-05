package com.example.hp.news_feed_task.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.news_feed_task.R;
import com.example.hp.news_feed_task.model.Item;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    List<Item> dataList = new ArrayList<>();
    Context context;

    public CustomAdapter(Context context, List<Item> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_item, viewGroup, false);
        CustomAdapter.CustomViewHolder hold = new CustomAdapter.CustomViewHolder(view);


        return (hold);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.textView.setText(dataList.get(position).getPostTitle());

        Picasso.Builder builder = new Picasso.Builder(context);


        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImgSrc())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        TextView tvLike;
        ImageButton iBtnLike;
        int likeCounter = 0;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsActivity.class);
                    intent.putExtra("URL", dataList.get(getAdapterPosition()).getPostLink());
                    context.startActivity(intent);
                }
            });


            textView = itemView.findViewById(R.id.title);

            tvLike = itemView.findViewById(R.id.counter);
            iBtnLike = itemView.findViewById(R.id.like);

            iBtnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "like clicked", Toast.LENGTH_SHORT).show();
                    likeCounter++;
                    tvLike.setText("" + likeCounter);
                }
            });

        }

    }


}
