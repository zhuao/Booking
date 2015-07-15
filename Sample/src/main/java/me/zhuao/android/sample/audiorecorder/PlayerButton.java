package me.zhuao.android.sample.audiorecorder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import me.zhuao.android.sample.activity.AudioRecorderActivity;

public class PlayerButton extends Button {
    private boolean startPlaying = false;

    public PlayerButton(Context context) {
        super(context);
        attachClickAction();
    }

    public PlayerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        attachClickAction();
    }

    public PlayerButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        attachClickAction();
    }

    public void attachClickAction() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!startPlaying) {
                    AudioRecorderActivity.audioPlayerService.playAudio(AudioRecorderActivity.audioFile);
                    setText("Stop");
                } else {
                    setText("Play");
                    AudioRecorderActivity.audioPlayerService.stopAudio();
                }
                startPlaying = !startPlaying;

            }
        });
    }
}
