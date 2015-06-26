package com.javatechig.droid.ui;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.daimajia.easing.bounce.BounceEaseInOut;
import com.nineoldandroids.animation.AnimatorSet;

public class SeekBarActivity extends Activity {

    @InjectView(R.id.volume_bar)
    SeekBar volumeControl;
    @InjectView(R.id.snap_bar)
    SeekBar snapBarControl;

    private static final int SNAP_MIN = 0;
    private static final int SNAP_MIDDLE = 50;
    private static final int SNAP_MAX = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

        ButterKnife.inject(this);

        setVolumeControlListener();
        setSnapBarControl();
    }

    private void setVolumeControlListener() {
        volumeControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressChanged = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SeekBarActivity.this, "seek bar progress: " + progressChanged, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void setSnapBarControl() {
        snapBarControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {

                int progress = seekBar.getProgress();
                if (progress >= SNAP_MIN && progress <= 25)
                    setProgressAnimated(seekBar, progress, SNAP_MIN);
                if (progress > 25 && progress <= 75)
                    setProgressAnimated(seekBar, progress, SNAP_MIDDLE);
                if (progress > 75 && progress <= SNAP_MAX)
                    setProgressAnimated(seekBar, progress, SNAP_MAX);
            }
        });
    }

    private static void setProgressAnimated(final SeekBar seekBar, int from, int to) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(Glider.glide(Skill.QuadEaseOut, 150, com.nineoldandroids.animation.ValueAnimator.ofInt().ofFloat(from, to), new BounceEaseInOut.EasingListener() {
            @Override
            public void on(float t, float result, float v2, float v3, float v4) {
                seekBar.setProgress((int) result);
            }
        }));
        set.setDuration(150);
        set.start();
    }

    private static void setProgressAnimatedJdk(final SeekBar seekBar, int from, int to) {
        ValueAnimator anim = ValueAnimator.ofInt(from, to);
        anim.setDuration(100);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animProgress = (Integer) animation.getAnimatedValue();
                seekBar.setProgress(animProgress);
            }
        });
        anim.start();
    }
}
