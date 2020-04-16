package com.mys3.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by VICKY on 10-Mar-18.
 */

public class HistoryActivity extends AppCompatActivity{

    private static final String TAG = "HistoryActivity";

    private TextView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);

        listView = (TextView) findViewById(R.id.ss);

        Intent incoming = getIntent();
        String history = incoming.getStringExtra("history");

        listView.setText(history);

    }
}
