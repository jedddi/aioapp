package com.example.aioapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.example.aioapp.Model.todomodel;
import com.example.aioapp.utils.databasehandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddTask extends BottomSheetDialogFragment {
    public static  final String TAG = "ActionBottomDialog";
    private EditText newTaskText;
    private Button newTaskSaveButton;
    private databasehandler db;

    public static AddTask newInstance(){
        return new AddTask();
    }
//    @Override
////    public void onCreate(Bundle savedInstanceState){
////        super.onCreate(savedInstanceState);
////        setStyle(STYLE_NORMAL, R.style.DialogStyle);
////    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_list_bottomsheet, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        newTaskText = getView().findViewById(R.id.task_name);
        newTaskSaveButton = getView().findViewById(R.id.add_task_button);
        db = new databasehandler(getActivity());
        db.openDatabase();

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle != null) {
            isUpdate = true;
            String task = bundle.getString("task");
            newTaskText.setText(task);
            if (task.length() > 0) {
                newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            }
        }
            newTaskText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                    if(s.toString().equals("")){
                        newTaskSaveButton.setEnabled(false);
                        newTaskSaveButton.setTextColor(Color.GRAY);
                    }
                    else{
                        newTaskSaveButton.setEnabled(true);
                        newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        boolean finalIsUpdate = isUpdate;
        newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = newTaskText.getText().toString();
                    if(finalIsUpdate){
                        db.updateTask(bundle.getInt("id"), text);
                    }
                    else{
                        todomodel task = new todomodel();
                        task.setTask(text);
                        task.setStatus(0);
                    }
                    dismiss();
                }
            });
            }
            @Override
    public void onDismiss(DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
            }
        }


