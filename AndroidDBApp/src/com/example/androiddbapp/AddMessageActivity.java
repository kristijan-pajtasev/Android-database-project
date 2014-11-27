package com.example.androiddbapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by kristijan on 2/28/14.
 */
public class AddMessageActivity extends Activity {

    private final static String LOG = "com.example.androiddbapp.AddMessageActivity";

    EditText newMessageEditText;
    ProjectDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_message);

        dbHelper = new ProjectDBHelper(this);

        newMessageEditText = (EditText)findViewById(R.id.newMessageEditText);

    }

    public void addMessage(View view) {
        Log.d(LOG, "addMessage");

        String message = newMessageEditText.getText().toString();

        if(message != "") {
            Log.d(LOG, "MESSAGE: " + message);

            HashMap<String, String> row = new HashMap<String, String>();

            row.put("message", message);

            dbHelper.insertRow(row);

            Intent intent = new Intent(getApplication(), AndroidDBActivity.class);
            startActivity(intent);

        } else {
            Log.d(LOG, "EMPTY MESSAGE");

        }
    }
}
