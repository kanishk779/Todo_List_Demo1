package com.example.android.todo_list.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.todo_list.Global.GlobalData;
import com.example.android.todo_list.Models.TaskDetails;
import com.example.android.todo_list.R;

import java.util.ArrayList;

/**
 * Created by hp on 19-06-2018.
 */

public class AdapterForTasks extends BaseAdapter {
    Context context;
    ArrayList<TaskDetails> list = new ArrayList<>();
    public AdapterForTasks(Context context,ArrayList<TaskDetails> list)
    {
        GlobalData.count=0;
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_task_mainscreen,null,false);
        TextView heading = v.findViewById(R.id.task_heading);
        TextView time = v.findViewById(R.id.task_time);
        TextView date = v.findViewById(R.id.task_date);
        TaskDetails td = list.get(position);
        heading.setText(td.getTaskHeading());
        time.setText(td.getTime());
        date.setText(td.getDate());
        //GlobalData.count+=1;
        Log.e("error", "value of position : "+ position);
        v.setId(0);
        return v;
    }
}
