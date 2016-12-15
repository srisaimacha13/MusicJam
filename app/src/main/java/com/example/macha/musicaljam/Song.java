package com.example.macha.musicaljam;

/**
 * Created by macha on 17/9/16.
 */
public class Song {
    private long id;
    private String title;
    private String artist;
//    constructor for instance variables

    public Song(long songID,String songTitle,String songArtist){
        id=songID;
        title=songTitle;
        artist=songArtist;
    }

//    get methods for instance variables

    public long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getArtist(){
        return artist;
    }


}

