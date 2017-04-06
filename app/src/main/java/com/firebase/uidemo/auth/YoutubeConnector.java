package com.firebase.uidemo.auth;

import android.content.Context;
import android.util.Log;

import com.firebase.uidemo.R;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Navjashan on 05/04/2017.
 */

public class YoutubeConnector {
    private YouTube youtube;
    private YouTube.Search.List query;

    public static HashMap<String, String> motChannels = new HashMap<String, String>();

    public static final String KEY = "AIzaSyDhVlgtQftVr2sY68Vt5h07tuPoVmi9ATA";

    public YoutubeConnector(Context context) {
        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {
            }
        }).setApplicationName(context.getString(R.string.app_name)).build();


        try {
            query = youtube.search().list("id, snippet");
            query.setKey(KEY);
            //    query.setChannelId("UCaKZDEMDdQc8t6GzFj1_TDw");
            query.setMaxResults((long)50);
            query.setType("video");
            query.setFields("items(id/videoId, snippet/title,snippet/channelTitle, snippet/channelId, snippet/description,snippet/thumbnails/default/url)");
        } catch (IOException e) {
            Log.d("YC", "Could not initialize: " + e.getMessage());
        }
    }

    public YoutubeConnector(Context context, String channelID) {
        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {
            }
        }).setApplicationName(context.getString(R.string.app_name)).build();


        try {
            query = youtube.search().list("id, snippet");
            query.setKey(KEY);
            query.setChannelId(channelID);
            query.setMaxResults((long)50);
            query.setType("video");
            query.setFields("items(id/videoId, snippet/title,snippet/channelTitle, snippet/channelId, snippet/description,snippet/thumbnails/default/url)");
        } catch (IOException e) {
            Log.d("YC", "Could not initialize: " + e.getMessage());
        }
    }


    public static HashMap getChannels(){
        return motChannels;
    }

    public static void clearList(){
        motChannels.clear();
    }

    public List<VideoItem> search(String keywords) {
        query.setQ(keywords);
        try {
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();
            List<VideoItem> items = new ArrayList<VideoItem>();

            for (SearchResult result : results) {
                VideoItem item = new VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setChannelTitle(result.getSnippet().getChannelTitle());
                item.setChannelID(result.getSnippet().getChannelId());

                if(!motChannels.containsKey(result.getSnippet().getChannelTitle())){
                    motChannels.put(result.getSnippet().getChannelTitle(),result.getSnippet().getChannelId());
                }

                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }

            return items;
        } catch (IOException e) {
            Log.d("YC", "Could not search: " + e);
            return null;
        }
    }
}