package com.example.android.todo_list;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.todo_list.Adapters.AdapterForTasks;
import com.example.android.todo_list.DataBase.TaskDatabase;
import com.example.android.todo_list.Models.TaskDetails;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TaskDatabase td = new TaskDatabase(this);
    ArrayList<TaskDetails> list = new ArrayList<>();
    FloatingActionButton myfab;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        td.openRead();

        list = td.getAllTasks();
        td.closeRead();
        if(list.size()==0){
            Log.e("error","In If condition");
            setContentView(R.layout.activity_main);
        }
        else if(list.size()>0)
        {
            Log.e("error","in else if");
            setContentView(R.layout.listview_main);
            lv = findViewById(R.id.listview);
            lv.setAdapter(new AdapterForTasks(this,list));
            for(int i=0;i<list.size();i++){
                View v = findViewById(i);
                TextView t = v.findViewById(R.id.task_heading);
                final String heading = t.getText().toString().trim();
                Button done = v.findViewById(R.id.donebtn);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        td.openWrite();
                        td.delete(heading);
                        td.closeWrite();
                        Intent in = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(in);
                    }
                });
            }
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView  t = view.findViewById(R.id.task_heading);
                    String title = t.getText().toString().trim();
                    td.openRead();
                    TaskDetails obj = td.read(title);
                    Intent intent = new Intent(MainActivity.this,SeeTaskActivity.class);
                    intent.putExtra("details",obj);
                    startActivity(intent);
                }
            });
        }
        myfab = findViewById(R.id.fab);
        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,AddTaskActivity.class);
                startActivity(in);
            }
        });
    }

}
