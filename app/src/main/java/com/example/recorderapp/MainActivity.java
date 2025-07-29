MainActivity.java

package com.example.recorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<File> recordings;
    private RecordingAdapter adapter;
    private File dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.recordingList);

        findViewById(R.id.btnNewRecord).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RecordActivity.class)));

        dir = getExternalFilesDir("Recordings");
        if (dir != null && dir.exists()) {
            File[] files = dir.listFiles();
            recordings = new ArrayList<>();
            if (files != null) {
                for (File f : files) recordings.add(f);
            }
            adapter = new RecordingAdapter(this, recordings);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No recordings found!", Toast.LENGTH_SHORT).show();
        }
    }
}
