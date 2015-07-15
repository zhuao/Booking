package me.zhuao.android.sample.audiorecorder;

import android.media.MediaMetadataRetriever;

import java.io.File;

public class RecorderFile {
    private static MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();

    private String fileSize;
    private String fileName;
    private Long fileTime;

    public RecorderFile(File audioFile) {
        mediaMetadataRetriever.setDataSource(audioFile.getAbsolutePath());

        this.fileName = audioFile.getName();
        this.fileTime = Long.valueOf(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
        this.fileSize = String.valueOf(audioFile.getUsableSpace());
        mediaMetadataRetriever.release();
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileSize() {
        return String.format("%fM", Float.valueOf(fileSize) / 1024 / 1024);
    }

    public String getFileTime() {
        return String.format("%dm%ds", fileTime / 100 / 60, fileTime / 100 % 60);
    }
}
