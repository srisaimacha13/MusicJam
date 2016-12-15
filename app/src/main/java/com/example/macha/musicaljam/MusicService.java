package com.example.macha.musicaljam;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import static android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

/**
 * Created by macha on 18/9/16.
 */
public class MusicService extends Service implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,MediaPlayer.OnCompletionListener {

    private MediaPlayer player;
    private ArrayList<Song> songs;
    private int songPosn;



    public void onCreate(){
        super.onCreate();
        songPosn=0;
        player=new MediaPlayer();

        initMusicPlayer();
    }

    public void initMusicPlayer(){
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        player.setOnPreparedListener(this);
        player.setOnErrorListener(this);
        player.setOnCompletionListener(this);
    }

    public void setList(ArrayList<Song> thisSongs){
        songs = thisSongs;
    }

    public class MusicBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }

    }

    private final IBinder musicBinder = new MusicBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }

    //if service unbounds to unbind

//    @Override
    public boolean onUnBind(Intent intent){
        player.stop();
        player.release();
        return false;
    }
    public void playSong(){
        player.reset();
//get song
        Song playSong = songs.get(songPosn);

        long currsong = playSong.getId();
        //set Uri
        Uri trackUri = ContentUris.withAppendedId(EXTERNAL_CONTENT_URI,currsong);
        try{
            player.setDataSource(getApplicationContext(),trackUri);
        } catch (Exception e) {
            Log.e("MusicService","Error setting data source",e);
        }

        player.prepareAsync();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
    public void setSong(int songIndex){
        songPosn=songIndex;
    }


}
