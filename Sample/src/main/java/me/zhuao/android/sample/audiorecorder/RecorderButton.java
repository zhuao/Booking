package me.zhuao.android.sample.audiorecorder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import me.zhuao.android.sample.activity.AudioRecorderActivity;

import java.util.LinkedList;
import java.util.List;

public class RecorderButton extends Button {

    private boolean recordStarting = false;
    private List<OnClickListener> clickListeners = new LinkedList<OnClickListener>();

    public RecorderButton(Context context) {
        super(context);
        attachAudioRecorder();
    }

    public RecorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        attachAudioRecorder();
    }

    public RecorderButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        attachAudioRecorder();
    }

    private void attachAudioRecorder() {
        clickListeners.add(new AudioRecordListener());
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (OnClickListener clickListener : clickListeners) {
                    clickListener.onClick(v);
                }
            }
        });
    }

    public void appendClickAction(OnClickListener onClickListener) {
        this.clickListeners.add(onClickListener);
    }

    private class AudioRecordListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (!recordStarting) {
                Toast.makeText(getContext(), "started", Toast.LENGTH_SHORT).show();
                setText("Stop Record");
                AudioRecorderActivity.audioRecorderService.startRecording(getContext());
            } else {
                setText("Start To Record");
                Toast.makeText(getContext(), "Stopped", Toast.LENGTH_SHORT).show();
                AudioRecorderActivity.audioRecorderService.stopRecording();
            }
            recordStarting = !recordStarting;
        }
    }

//    private void attachAudioRecorder() {
//        setOnLongClickListener(new OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(getContext(), "started", Toast.LENGTH_SHORT).show();
//                AudioRecorderActivity.audioRecorderService.startRecording(getContext());
//                return false;
//            }
//        });
//
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    Toast.makeText(getContext(), "stopped", Toast.LENGTH_SHORT).show();
//                    AudioRecorderActivity.audioFile = AudioRecorderActivity.audioRecorderService.stopRecording();
//                }
//                return false;
//            }
//        });
//    }
}
