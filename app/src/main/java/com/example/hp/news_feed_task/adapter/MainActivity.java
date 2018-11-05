package com.example.hp.news_feed_task.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.news_feed_task.R;
import com.example.hp.news_feed_task.model.Item;
import com.example.hp.news_feed_task.model.News;
import com.example.hp.news_feed_task.network.GetDataService;
import com.example.hp.news_feed_task.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private CustomAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        retrofit2.Call<News> call = service.getTittle();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(retrofit2.Call<News> call, Response<News> response) {

                generateDataList(response.body().getItems());

            }

            @Override
            public void onFailure(retrofit2.Call<News> call, Throwable t) {

            }
        });


    }

    private void generateDataList(List<Item> id) {

        recyclerView = findViewById(R.id.recycleview);

        adapter = new CustomAdapter(this, id);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
