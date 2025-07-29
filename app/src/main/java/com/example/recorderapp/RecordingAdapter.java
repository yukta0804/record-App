RecordingAdapter.java

package com.example.recorderapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecordingAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<File> files;

    public RecordingAdapter(Context context, ArrayList<File> files) {
        this.context = context;
        this.files = files;
    }

    @Override
    public int getCount() { return files.size(); }

    @Override
    public Object getItem(int i) { return files.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        File file = files.get(i);
        convertView = LayoutInflater.from(context).inflate(R.layout.recording_item, parent, false);
        TextView title = convertView.findViewById(R.id.recordTitle);
        Button play = convertView.findViewById(R.id.btnPlay);
        Button delete = convertView.findViewById(R.id.btnDelete);

        title.setText(file.getName());

        play.setOnClickListener(v -> {
            MediaPlayer player = new MediaPlayer();
            try {
                player.setDataSource(file.getAbsolutePath());
                player.prepare();
                player.start();
                Toast.makeText(context, "Playing...", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        delete.setOnClickListener(v -> {
            file.delete();
            files.remove(i);
            notifyDataSetChanged();
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
