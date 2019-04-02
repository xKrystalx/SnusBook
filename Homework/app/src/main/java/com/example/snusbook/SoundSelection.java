package com.example.snusbook;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class SoundSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_selection);

        TypedArray sounds = getResources().obtainTypedArray(R.array.Sounds);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        int size = sounds.length();
        RadioGroup sounds_group = (RadioGroup)findViewById(R.id.sounds_group);
        sounds_group.removeAllViews();
        for(int i = 0; i < sounds.length(); ++i){
            RadioButton sound_button = new RadioButton(getApplicationContext());
            sound_button.setId(i);
            sound_button.setText(sounds.getText(i));
            sound_button.setLayoutParams(param);
            sounds_group.addView(sound_button);
        }
        sounds.recycle();
    }

    public void onAcceptSound(View view) {
        RadioGroup sounds_group = findViewById(R.id.sounds_group);
        int button_id = sounds_group.getCheckedRadioButtonId();
        if(button_id < 0) return;
        Intent result = new Intent();
        result.putExtra("SOUND_ID", button_id);
        setResult(2, result);
        finish();
    }

    public void onCancelSound(View view) {
        finish();
    }
}
