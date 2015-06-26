package com.javatechig.droid.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class SeekBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

        SeekBar volumeControl = (SeekBar) findViewById(R.id.volume_bar);

        volumeControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SeekBarActivity.this, "seek bar progress: " + progressChanged, Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }
}
