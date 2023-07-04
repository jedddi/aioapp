package com.example.aioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.aioapp.Adapter.todoadapter;
import com.example.aioapp.Model.todomodel;
import com.example.aioapp.utils.databasehandler;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import com.example.aioapp.Adapter.todoadapter;
import com.example.aioapp.Model.todomodel;
import com.example.aioapp.utils.databasehandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Todolist extends AppCompatActivity implements DialogCloseListener{



    private HorizontalCalendar horizontalCalendar;
    private Button add_list_button1;
    private BottomSheetDialog add_list_bottomsheet;
    private Button backButton;

    private ImageView imageView;
    private todoadapter taskAdapter;
    private List<todomodel> taskList;
    private databasehandler db;
    private RecyclerView tasksRecyclerView;
    private ImageButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list_page);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_Dashboard = new Intent(Todolist.this, Dashboard_activity.class);
                startActivity(back_Dashboard);
            }
        });


// Dialog Code
//        ImageButton add_list_button1=findViewById(R.id.addlistbtn);
//        add_list_button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                add_list_bottomsheet=new BottomSheetDialog(Todolist.this, R.style.bottom_sheet_theme);
//                View sheetview= LayoutInflater.from(getApplicationContext()).inflate(R.layout.add_list_bottomsheet, null);
//
//                sheetview.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        add_list_bottomsheet.dismiss();
//                    }
//                });
//
//                add_list_bottomsheet.setContentView(sheetview);
//                add_list_bottomsheet.show();
//
//            }
//        });

//Calendar Code
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
            }
        });

        //For adding and Deleting task
        //kani nalang ang kulang if mu work nani goods na

        db = new databasehandler(Todolist.this);
        db.openDatabase();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.taskrecycler);
        tasksRecyclerView.setHasFixedSize(true);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new todoadapter(db,Todolist.this);
        tasksRecyclerView.setAdapter(taskAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(taskAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.addlistbtn);
        taskList = db.getAllTask();
        Collections.reverse(taskList);
        taskAdapter.setTasks(taskList);

        todomodel task = new todomodel();
        task.setTask("Test Task");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AddTask.newInstance().show(getSupportFragmentManager(), AddTask.TAG);
            }
        });
    }
     @Override
    public void handleDialogClose(DialogInterface dialog){
     taskList = db.getAllTask();
     Collections.reverse(taskList);
     taskAdapter.setTasks(taskList);
     taskAdapter.notifyDataSetChanged();

    }

}