package com.example.hp.news_feed_task.network;

import com.example.hp.news_feed_task.model.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/api/index.php?task=selected_news&fbclid=IwAR01HMtV7CvEC1dapk7JIEEJ3ZpEymFPXz4Bb_AEJDfkjfubZOPB-u5kv3Y")
    Call<News> getTittle();

}
