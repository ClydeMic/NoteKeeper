package com.rumi.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class BusinessNotesActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_notes2);

        EditText editText = findViewById(R.id.edit_note);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);
        //check the validity of noteId
        if(noteId != -1){

            editText.setText(BusinessNotes.notes.get(noteId));
        } else{
            //Create an empty note
            BusinessNotes.notes.add(" ");
            //noteId to access it
            noteId = BusinessNotes.notes.size() -1;
            //update ArrayAdapter to display it
            BusinessNotes.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Update notes ArrayList from the CreativeIdeas Activity
                BusinessNotes.notes.set(noteId,String.valueOf(charSequence));
                //Update ListView via the ArrayAdapter
                BusinessNotes.arrayAdapter.notifyDataSetChanged();

                //MODE_PRIVATE only our app can access the information
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.rumi.notekeeper", Context.MODE_PRIVATE);

                //Create string from array list
                HashSet<String> set3 = new HashSet(BusinessNotes.notes);

                //allows to get diff variable types
                sharedPreferences.edit().putStringSet("notes",set3).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}

