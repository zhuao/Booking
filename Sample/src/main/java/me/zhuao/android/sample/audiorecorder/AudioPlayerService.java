package me.zhuao.android.sample.audiorecorder;

import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class AudioPlayerService {
    private MediaPlayer mediaPlayer;

    public AudioPlayerService() {
        mediaPlayer = new MediaPlayer();
    }

    public void stopAudio() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void playAudio(File audioFile) {
        try {
            mediaPlayer.setDataSource(audioFile.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            mediaPlayer.release();
        }
    }
}
