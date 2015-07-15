package me.zhuao.android.sample.audiorecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import me.zhuao.android.sample.R;

public class AudioAdapter extends BaseAdapter {

    private final Context context;
    private List<RecorderFile> audioFiles = new LinkedList<RecorderFile>();

    public AudioAdapter(Context context, Collection<RecorderFile> collection) {
        this.context = context;
        audioFiles.addAll(collection);
    }

    @Override
    public int getCount() {
        return audioFiles.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AudioViewHolder audioViewHolder;
        if (convertView == null) {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.audio_item, null);

            audioViewHolder = new AudioViewHolder();
            audioViewHolder.fileName = (TextView) convertView.findViewById(R.id.audio_file_name);
            audioViewHolder.fileTime = (TextView) convertView.findViewById(R.id.audio_file_time);
            audioViewHolder.fileSize = (TextView) convertView.findViewById(R.id.audio_file_size);
            convertView.setTag(audioViewHolder);
        } else {
            audioViewHolder = (AudioViewHolder) convertView.getTag();
        }

        RecorderFile audioFile = audioFiles.get(position);
        audioViewHolder.fileName.setText(audioFile.getFileName());
        audioViewHolder.fileTime.setText(audioFile.getFileTime());
        audioViewHolder.fileSize.setText(audioFile.getFileSize());

        return convertView;
    }

    public void refreshData() {
        Collection<RecorderFile> collection = AudioFolderOperator.getLatestAudioFiles(context);
        audioFiles.clear();
        audioFiles.addAll(collection);
        notifyDataSetChanged();
    }

    private class AudioViewHolder {
        public TextView fileName;
        public TextView fileTime;
        public TextView fileSize;
    }
}
