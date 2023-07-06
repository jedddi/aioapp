package com.example.aioapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aioapp.AddTask;
import com.example.aioapp.MainActivity;
import com.example.aioapp.Model.todomodel;
import com.example.aioapp.R;
import com.example.aioapp.Todolist;
import com.example.aioapp.utils.databasehandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class todoadapter<TodoList> extends RecyclerView.Adapter<todoadapter.ViewHolder> {

    private List<todomodel> todoList;
    private databasehandler db;

    private Todolist activity;

    public todoadapter(databasehandler db, Todolist activity){
        this.db = db;
        this.activity = activity;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklayout, parent, false);
        return new ViewHolder(itemView);
    }
    public void onBindViewHolder(ViewHolder holder, int position){
        final todomodel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(item.getId(), 1);
                }
                else{
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
        // Parse the date string to a LocalDate object
        LocalDate date;
        try {
            date = LocalDate.parse(item.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            holder.dateTextView.setText(item.getDate());
            return;
        }

        // Format the LocalDate object into the desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        String formattedDate = date.format(outputFormatter);

        holder.dateTextView.setText(formattedDate);
    }
    private boolean toBoolean(int n){
        return n!=0;
    }
    public Context getContext(){ return activity; }

    public void setTasks(List<todomodel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        todomodel item = todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }


    public void editItem(int position){
        todomodel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id",item.getId());
        bundle.putString("task",item.getTask());

        AddTask fragment = new AddTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), fragment.getTag());

    }
    @Override
    public int getItemCount(){ return todoList.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        TextView dateTextView;
        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
            dateTextView = view.findViewById(R.id.dateTextView);
        }
    }
}
