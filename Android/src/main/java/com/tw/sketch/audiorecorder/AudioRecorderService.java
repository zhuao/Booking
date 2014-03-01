package com.tw.sketch.audiorecorder;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.widget.Toast;
import com.tw.sketch.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AudioRecorderService {

    private MediaRecorder mediaRecorder = null;
    private static SimpleDateFormat AUDIO_FILE_TIME_STAMP = new SimpleDateFormat("yyyyMMddHHmmssS");
    private String tempAudioFile;

    public AudioRecorderService() {

    }

    public void startRecording(Context context) {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
//            mediaRecorder.setAudioChannels(2);
//            mediaRecorder.setAudioSamplingRate(2);
            tempAudioFile = getTempAudioFile(context);
            mediaRecorder.setOutputFile(tempAudioFile);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            mediaRecorder.reset();
            mediaRecorder.release();
            throw new RuntimeException(e);
        }
    }

    public File stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;

        return new File(tempAudioFile);
    }

    public void release() {
        //TODO: To check whether recoreder is released.
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
        }
    }

    private String getTempAudioFile(Context context) throws IOException {
        String audioDirectory = getAudioFolder(context);

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File projectFolder = new File(audioDirectory);
            if (!projectFolder.exists()) {
                projectFolder.mkdir();
            }
            return projectFolder.getAbsolutePath() + "/" + buildAudioFileName();
        }
        Toast.makeText(context, "SD card is not ready.", Toast.LENGTH_SHORT).show();
        throw new RuntimeException("External storage error");
    }

    private String getAudioFolder(Context context) {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + context.getResources().getString(R.string.app_name);
    }

    private String buildAudioFileName() {
        return AUDIO_FILE_TIME_STAMP.format(new Date()) + ".3gp";
    }

    public void emptyFolder(Context context) {
        File file = new File(getAudioFolder(context));
        if (file.exists() && file.isDirectory()) {
            file.deleteOnExit();
        }
    }
}