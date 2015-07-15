package me.zhuao.android.sample.audiorecorder;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;

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

    public static String buildAudioFileName() {
        return AUDIO_FILE_TIME_STAMP.format(new Date()) + ".3gp";
    }

    public static String getTempAudioFile(Context context) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File audioFolder = AudioFolderOperator.getAudioDirectory(context);
            return audioFolder.getAbsolutePath() + "/" + buildAudioFileName();
        }
        throw new RuntimeException("External storage error");
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

}
