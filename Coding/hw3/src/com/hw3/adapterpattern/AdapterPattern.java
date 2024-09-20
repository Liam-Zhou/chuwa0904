package com.hw3.adapterpattern;

public class AdapterPattern {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "a.mp3");
        audioPlayer.play("mp4", "b.mp4");
        audioPlayer.play("vlc", "c.vlc");
        audioPlayer.play("avi", "d.avi");
    }
}
