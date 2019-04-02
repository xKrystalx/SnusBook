package com.example.snusbook;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MediaPlayer soundsPlayer;
    int sound_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Playing...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                onPlayButtonClicked();
            }
        });
        TextView first_contact_name = findViewById(R.id.contact_name);
        TypedArray contacts = getResources().obtainTypedArray(R.array.Contacts);
        TypedArray images = getResources().obtainTypedArray(R.array.Images);
        first_contact_name.setText(contacts.getText(0));

        ImageView first_contact_image = findViewById(R.id.contact_image);
        first_contact_image.setImageResource(images.getResourceId(1, 0));
        images.recycle();
        contacts.recycle();

        TypedArray sounds = getResources().obtainTypedArray(R.array.Sounds);
        sound_id = getResources().getIdentifier(sounds.getString(0), "raw", getPackageName());
        soundsPlayer = MediaPlayer.create(getApplicationContext(), sound_id);
        sounds.recycle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPlayButtonClicked() {
        if(soundsPlayer.isPlaying()){
            soundsPlayer.stop();
        }
        else {
            soundsPlayer = MediaPlayer.create(getApplicationContext(), sound_id);
            soundsPlayer.start();
        }
    }

    public void onContactChangeButtonClicked(View view) {
        Intent intentContacts = new Intent(getApplicationContext(), ContactSelection.class);
        startActivityForResult(intentContacts, 1);
        if(soundsPlayer.isPlaying())soundsPlayer.stop();
    }

    public void onContactSoundChangeButtonClicked(View view) {
        Intent intentSounds = new Intent(getApplicationContext(), SoundSelection.class);
        startActivityForResult(intentSounds, 2);
        if(soundsPlayer.isPlaying())soundsPlayer.stop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                if(data == null) break;
                int id = data.getIntExtra("CONTACT_ID", 0);
                TypedArray contacts = getResources().obtainTypedArray(R.array.Contacts);
                TypedArray images = getResources().obtainTypedArray(R.array.Images);
                TextView name = findViewById(R.id.contact_name);
                name.setText(contacts.getText(id));
                ImageView image = findViewById(R.id.contact_image);
                Random r = new Random();
                image.setImageResource(images.getResourceId(r.nextInt(images.length() - 1), 0));
                contacts.recycle();
                images.recycle();
                break;
            }
            case 2: {
                if(data == null) break;
                int id = data.getIntExtra("SOUND_ID", 0);
                TypedArray sounds = getResources().obtainTypedArray(R.array.Sounds);
                sound_id = getResources().getIdentifier(sounds.getString(id), "raw", getPackageName());
                soundsPlayer = MediaPlayer.create(getApplicationContext(), sound_id);
                sounds.recycle();
                break;
            }
        }
    }
}