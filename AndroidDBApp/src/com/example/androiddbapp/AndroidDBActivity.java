package com.example.androiddbapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.ListAdapter;

public class AndroidDBActivity extends ListActivity
{
    
    private static final String LOG = "com.example.androiddbapp.LOG_MESSAGE: ";
    
    Intent intent;
    EditText messageRow;
    TextView idTextView;
    TextView messageTextView;
    EditText newMessageEditText;
    
    ProjectDBHelper dbHelper = new ProjectDBHelper(this);
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // newMessageEditText = (EditText)findViewById(R.id.messageEditText);
        
        ArrayList<HashMap<String, String>> rows = dbHelper.getAll();
        
        if(rows.size() > 0) {
            ListView listView = getListView();
            
            listView.setOnItemClickListener(new OnItemClickListener() {
            
                @Override
                public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
                    Log.d(LOG, "ITEM CLICKED");

                    idTextView = (TextView)view.findViewById(R.id.id);
                    String idValue = idTextView.getText().toString();

                    messageTextView = (TextView)view.findViewById(R.id.message);
                    String messageValue = messageTextView.getText().toString();
                    
                    Intent intent = new Intent(getApplication(), UpdateMessageActivity.class);
                    intent.putExtra("id", idValue);
                    intent.putExtra("message", messageValue);
                    startActivity(intent);

                }
                
            });
            Log.d(LOG, "ROWS: " + rows.toString());
            ListAdapter adapter = new SimpleAdapter(AndroidDBActivity.this, rows, R.layout.message, 
                new String[]{"id", "message"},
                new int[]{R.id.id, R.id.message});
            
            setListAdapter(adapter);
        }
    }

    public void addNewMessage(View view) {
        Log.d(LOG, "addNewMessage");

        Intent intent = new Intent(this, AddMessageActivity.class);
        startActivity(intent);
    }
/*
    public void deleteRow(View view) {
        Log.d(LOG, "deleteMessage");
        View parentView = (View)view.getParent();
        TextView rowIdTextView = (TextView)parentView.findViewById(R.id.id);
        String rowId = rowIdTextView.getText().toString();
        Log.d(LOG, "ROW ID: " + rowId);
        dbHelper.deleteRow(rowId);
    }
    */
}
