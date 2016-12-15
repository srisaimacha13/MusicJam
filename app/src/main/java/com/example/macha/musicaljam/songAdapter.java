package com.example.macha.musicaljam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by macha on 17/9/16.
 */
public class songAdapter extends BaseAdapter {

    private ArrayList<Song> songs;
    private LayoutInflater songsInf;

    public songAdapter(Context c,ArrayList<Song> theSongs){
        songs=theSongs;
        songsInf=LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //maping song to layout
        LinearLayout songLay = (LinearLayout) songsInf.inflate(R.layout.song,parent ,false);
        TextView songView = (TextView) songLay.findViewById(R.id.song_title);
        TextView songArtist = (TextView) songLay.findViewById(R.id.song_artist);

        Song currSong = songs.get(position);

        songView.setText(currSong.getTitle());
        songArtist.setText(currSong.getArtist());
        songLay.setTag(position);

        return songLay;
    }
}
