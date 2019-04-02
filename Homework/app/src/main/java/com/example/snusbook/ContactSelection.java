package com.example.snusbook;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ContactSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_selection);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        Spinner contacts_spinner = (Spinner)findViewById(R.id.contact_spinner);
        List<String> list = new ArrayList<>();
        String[] str = getResources().getStringArray(R.array.Contacts);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, str);
        contacts_spinner.setAdapter(adapter);
    }

    public void onAcceptContact(View view) {
        Spinner contacts_spinner = (Spinner)findViewById(R.id.contact_spinner);
        int button_id = contacts_spinner.getSelectedItemPosition();
        if(button_id < 0) return;
        Intent result = new Intent();
        result.putExtra("CONTACT_ID", button_id);
        setResult(1, result);
        finish();
    }

    public void onCancelContact(View view) {
        finish();
    }
}
