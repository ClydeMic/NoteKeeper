package com.rumi.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashSet;

public class CreativesEditor extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatives_editor);

        EditText editText = findViewById(R.id.edit_note);



        Intent intent = getIntent();
         noteId = intent.getIntExtra("noteId", -1);
        //check the validity of noteId
        if(noteId != -1){
            editText.setText(CreativeIdeas.notes.get(noteId));
        } else{
           //Create an empty note
            CreativeIdeas.notes.add(" ");
            //noteId to access it
            noteId = CreativeIdeas.notes.size() -1;
            //update ArrayAdapter to display it
            CreativeIdeas.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Update notes ArrayList from the CreativeIdeas Activity
                CreativeIdeas.notes.set(noteId,String.valueOf(charSequence));
                //Update ListView via the ArrayAdapter
                CreativeIdeas.arrayAdapter.notifyDataSetChanged();

                //MODE_PRIVATE only our app can access the information
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.rumi.notekeeper", Context.MODE_PRIVATE);

                //Create string from array list
                HashSet <String> set = new HashSet(CreativeIdeas.notes);

                //allows to get diff variable types
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
