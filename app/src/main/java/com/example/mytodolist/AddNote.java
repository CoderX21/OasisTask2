package com.example.mytodolist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_baseline_add_task_24);
        actionBar.setTitle("    KEEP NOTES");

        Button save=findViewById(R.id.savebtn);
        EditText titleinput=findViewById(R.id.titleinput);
        EditText descinput=findViewById(R.id.descinput);

        Realm.init(getApplicationContext());
        Realm realm=Realm.getDefaultInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleinput.getText().toString();
                String desc=descinput.getText().toString();
                long createdTime=System.currentTimeMillis();

                realm.beginTransaction();
                Note note=realm.createObject(Note.class);
                note.setTitle(title);
                note.setDescription(desc);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"note saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}