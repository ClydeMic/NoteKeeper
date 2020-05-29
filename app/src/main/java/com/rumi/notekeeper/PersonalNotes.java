package com.rumi.notekeeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;

public class PersonalNotes extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_notes);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Implement an explicit intent for opening the add note activity
                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Implement an explicit intent for opening the Creatives Editor activity
                        Intent intent = new Intent(getApplicationContext(), PersonalEditor.class);
                        startActivity(intent);
                    }
                });
            }
        });

        //get intent sent from the main activity
        Intent intent = getIntent();
        String displayMessage = intent.getStringExtra(MainActivity.EXTRA_NOTE_KEY);

        //create a text view variable to connect with the text view in the layout
        TextView noteDisplay = findViewById(R.id.displayNote);
        //setting the TextView with the message retrieved from MainActivity
        noteDisplay.setText(displayMessage);


        ListView listView = findViewById(R.id.listView);

        //check sharedPreferences and if there is a set obtain the content from it.
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.rumi.notekeeper", Context.MODE_PRIVATE);

        HashSet<String> set1 = (HashSet<String>) sharedPreferences.getStringSet("notes",null);

        if(set1 == null){
            notes.add(" Buy Milk");
        } else {

            notes = new ArrayList(set1);
        }


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,notes);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int noteId, long l) {
                //Intent to go to the Editor Activity
                Intent intent = new Intent(getApplicationContext(),PersonalEditor.class);
                //let the new Activity 'know' which item was tapped
                intent.putExtra("noteId", noteId);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete = i;
                //Alert Dialog Builder to allow user to confirm deletion.
                new AlertDialog.Builder(PersonalNotes.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Confirm")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //remove selected
                                notes.remove(itemToDelete);
                                //update array adapter
                                arrayAdapter.notifyDataSetChanged();

                                //MODE_PRIVATE only our app can access the information
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.rumi.notekeeper", Context.MODE_PRIVATE);

                                //Create string from array list
                                HashSet<String> set1 = new HashSet(PersonalNotes.notes);

                                //allows to get diff variable types
                                sharedPreferences.edit().putStringSet("notes",set1).apply();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

                //Returning true overcomes the short click.
                return true;
            }
        });

    }
}
