package com.rumi.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //get intent sent from the main activity
        Intent intent = getIntent();
        String displayMessage = intent.getStringExtra(MainActivity.EXTRA_NOTE_KEY);

        //create a text view variable to connect with the text view in the layout
        TextView noteDisplay = findViewById(R.id.displayNote);
        //setting the TextView with the message retrieved from MainActivity
        noteDisplay.setText(displayMessage);
    }
}
