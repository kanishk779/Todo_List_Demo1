package com.example.android.todo_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.todo_list.Models.TaskDetails;

public class SeeTaskActivity extends AppCompatActivity {
    TextView heading,date,time,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_task);
        Intent i = getIntent();
        TaskDetails t =(TaskDetails) i.getSerializableExtra("details");
        heading = findViewById(R.id.fill_heading);
        date = findViewById(R.id.fill_date);
        time = findViewById(R.id.fill_time);
        description = findViewById(R.id.fill_description);
        heading.setText(t.getTaskHeading());
        date.setText(t.getDate());
        time.setText(t.getTime());
        description.setText(t.getDescription());
    }
}
