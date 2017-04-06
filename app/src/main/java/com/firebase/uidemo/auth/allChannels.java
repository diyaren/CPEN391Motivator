package com.firebase.uidemo.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.uidemo.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class allChannels extends AppCompatActivity {

    ArrayList<String> channels = new ArrayList<String>();
    ArrayList<String> channelID = new ArrayList<String>();

    HashMap<String, String> channelInfo = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_channels);

        addtoList();

        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, channels);

        ListView listView =  (ListView)findViewById(R.id.allChannelsList);
        listView.setAdapter(adapter);

        Log.i("s","asda");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent2 = new Intent(allChannels.this, displayChannel.class);
                String message = channelID.get(position);
                intent2.putExtra("EXTRA_MESSAGE2", message);
                startActivity(intent2);
            }
        });


    }

    private void addtoList() {
        channelInfo = youtubeActivity.maping;
        for (String aChannel : channelInfo.keySet()) {
            channels.add(aChannel);
        }

        for (String aChannel : channelInfo.values()) {
            channelID.add(aChannel);
            Log.i("asd",aChannel);
        }

    }
}
