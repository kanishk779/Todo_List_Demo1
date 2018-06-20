package com.example.android.todo_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.todo_list.DataBase.TaskDatabase;
import com.example.android.todo_list.Models.TaskDetails;

public class AddTaskActivity extends AppCompatActivity {
    EditText heading,date,time,description;
    String data_heading,data_date,data_time,data_description;
    Button save;
    TaskDatabase db = new TaskDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        heading = findViewById(R.id.edit_heading);
        date = findViewById(R.id.edit_date);
        time = findViewById(R.id.edit_time);
        description = findViewById(R.id.edit_description);
        save = findViewById(R.id.savebtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid())
                {
                    TaskDetails td = new TaskDetails();
                    td.setDescription(data_description);
                    td.setTime(data_time);
                    td.setDate(data_date);
                    td.setTaskHeading(data_heading);
                    db.openWrite();
                    long i = db.insert(td);
                    db.closeWrite();
                    if(i>1)
                    {
                        Toast.makeText(AddTaskActivity.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(AddTaskActivity.this,MainActivity.class);
                        startActivity(in);
                    }
                    else
                        Toast.makeText(AddTaskActivity.this, "Did Not Save", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AddTaskActivity.this, "Fill All The Neccessary Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isValid()
    {
        data_heading = heading.getText().toString();
        data_date = date.getText().toString();
        data_time = time.getText().toString();
        data_description = description.getText().toString().trim();
        if(TextUtils.isEmpty(data_date)||TextUtils.isEmpty(data_description)||TextUtils.isEmpty(data_heading)||TextUtils.isEmpty(data_time))
        {
            return false;
        }
        return true;
    }
}
