package com.example.android.todo_list.Models;

import java.io.Serializable;

/**
 * Created by hp on 19-06-2018.
 */

public class TaskDetails implements Serializable{
    private String taskHeading;
    private String date;

    private String time;
    private String description;

    public String getTaskHeading() {
        return taskHeading;
    }

    public void setTaskHeading(String taskHeading) {
        this.taskHeading = taskHeading;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
