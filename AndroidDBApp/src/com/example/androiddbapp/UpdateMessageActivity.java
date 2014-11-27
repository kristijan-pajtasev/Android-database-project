package com.example.androiddbapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by kristijan on 3/1/14.
 */
public class UpdateMessageActivity extends Activity {

    private final static String LOG = "com.example.androiddbapp.UpdateMessageActivity";

    ProjectDBHelper dbHelper;
    TextView idTextView;
    EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_message);

        dbHelper = new ProjectDBHelper(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String message = intent.getStringExtra("message");

        idTextView = (TextView)findViewById(R.id.idTextView);
        messageEditText = (EditText)findViewById(R.id.messageEditText);

        idTextView.setText(id);
        messageEditText.setText(message);
    }

    public void updateMessage(View view) {
        Log.d(LOG, "addMessage");

        String message = messageEditText.getText().toString();
        String id = idTextView.getText().toString();

        if(message != "") {
            HashMap<String, String> messageRow = new HashMap<String, String>();
            messageRow.put("id", id);
            messageRow.put("message", message);

            dbHelper.updateRow(messageRow);
            Intent intent = new Intent(getApplication(), AndroidDBActivity.class);
            startActivity(intent);
        } else {

        }
    }

    public void deleteMessage(View view) {
        Log.d(LOG, "deleteMessage");
        String id = idTextView.getText().toString();
        dbHelper.deleteRow(id);

        Intent intent = new Intent(getApplication(), AndroidDBActivity.class);
        startActivity(intent);
    }
}
