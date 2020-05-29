package com.rumi.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String mNoteMessage;
    public static final String EXTRA_NOTE_KEY = "MY KEY FOR NOTE MESSAGE";

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
                //Implement an explicit intent for opening the add note activity
                Intent intent = new Intent(MainActivity.this,AddNote.class);
                intent.putExtra(EXTRA_NOTE_KEY,mNoteMessage);
                startActivity(intent);
            }
        });
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

    //Displaying toasts
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    public void ideasMessage(View view) {
        //display a toast message for creative ideas notes
        mNoteMessage = "Creative Ideas Notes";
        displayToast(mNoteMessage);

        //Implicit intent to open the ideas Activity
        Intent intent = new Intent(MainActivity.this,CreativeIdeas.class);
        intent.putExtra(EXTRA_NOTE_KEY,mNoteMessage);
        startActivity(intent);
    }

    public void personalMessage(View view) {
        //display a toast message for personal notes
        mNoteMessage = "Personal Notes";
        displayToast(mNoteMessage);

        //Implicit intent to open the personal Activity
        Intent intent = new Intent(MainActivity.this,PersonalNotes.class);
        intent.putExtra(EXTRA_NOTE_KEY,mNoteMessage);
        startActivity(intent);
    }

    public void shoppingMessage(View view) {
        //display a toast message for shopping lists
        mNoteMessage = "Shopping Lists";
        displayToast(mNoteMessage);

        //Implicit intent to open the shopping Activity
        Intent intent = new Intent(MainActivity.this,ShoppingLists.class);
        intent.putExtra(EXTRA_NOTE_KEY,mNoteMessage);
        startActivity(intent);
    }

    public void workMessage(View view) {
        //display a toast message for business notes
        mNoteMessage = "Business Notes";
        displayToast(mNoteMessage);

        //Implicit intent to open the Business Activity
        Intent intent = new Intent(MainActivity.this,BusinessNotes.class);
        intent.putExtra(EXTRA_NOTE_KEY,mNoteMessage);
        startActivity(intent);
    }
}
