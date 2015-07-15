package me.zhuao.android.sample.audiorecorder;

import android.content.Context;
import android.os.Environment;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import me.zhuao.android.sample.R;


public class AudioFolderOperator {
    public static File getAudioDirectory(Context context) {
        File audioFolder = new File(getAudioFolder(context));
        try {
            FileUtils.forceMkdir(audioFolder);
        } catch (IOException e) {
            throw new RuntimeException("Can't create audio directory.");
        }
        return audioFolder;
    }

    public static void emptyFolder(Context context) {
        File file = new File(getAudioFolder(context));
        if (file.exists() && file.isDirectory()) {
            try {
                FileUtils.deleteDirectory(file);
            } catch (IOException e) {

            }
        }
    }

    public static List<RecorderFile> getLatestAudioFiles(Context context) {
        File audioDirectory = getAudioDirectory(context);
        Collection<File> files = FileUtils.listFiles(audioDirectory, new String[]{"3gp", "mp3", "ogg"}, true);
        List<RecorderFile> audios = new LinkedList<RecorderFile>();
        for (File file : files) {
            audios.add(new RecorderFile(file));
        }
        return audios;
    }

    private static String getAudioFolder(Context context) {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + context.getResources().getString(R.string.app_name) + "/Audio";
    }
}
