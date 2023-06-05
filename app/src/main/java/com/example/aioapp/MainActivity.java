package com.example.aioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class MainActivity extends AppCompatActivity {



    private HorizontalCalendar horizontalCalendar;
    private Button add_list_button1;
    private BottomSheetDialog add_list_bottomsheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list_page);

// Dialog Code
        ImageButton add_list_button1=findViewById(R.id.addlistbtn);
        add_list_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_list_bottomsheet=new BottomSheetDialog(MainActivity.this, R.style.bottom_sheet_theme);
                View sheetview= LayoutInflater.from(getApplicationContext()).inflate(R.layout.add_list_bottomsheet, null);

                sheetview.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        add_list_bottomsheet.dismiss();
                    }
                });

                add_list_bottomsheet.setContentView(sheetview);
                add_list_bottomsheet.show();

            }
        });
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
    }


}