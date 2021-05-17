package com.innodeed.todo_app;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.innodeed.todo_app.Model.ToDoModel;
import com.innodeed.todo_app.Utils.DataBaseHelper;

public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "AddNewTask";

    // Widgets

    private EditText mEditText;
    private Button mSaveButton;

    private DataBaseHelper myDb;

    public static AddNewTask newInstance(){
        return new AddNewTask();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_newtask ,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText =view.findViewById(R.id.edittext);
        mSaveButton=view.findViewById(R.id.button_save);

        myDb =new DataBaseHelper(getActivity());

        boolean isUpdate=false;
        Bundle bundle=getArguments();

        if (bundle!=null){  // checking there is data in bundle or not
            isUpdate=true;  // if there is data in bundle we need to update the data or task
            //now retrieving date from a bundle
            String task =bundle.getString("task");
            mEditText.setText(task);// setting editText as task

            if(task.length()>0){
                mSaveButton.setEnabled(false);// here checking the length of the tsk if it is gr8r than 0
                                               // we will disable the button for a moment
            }
            // here what we are trying to do is ,disabling the SAVE button when user is not typing
            // anything and then again enabling save button when he starts writing by using addTextChangedListner
            mEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                      if(s.toString().equals("")){
                         mSaveButton.setEnabled(false);
                         mSaveButton.setBackgroundColor(Color.GRAY);

                      }else{

                          mSaveButton.setEnabled(true);
                          mSaveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                      }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            boolean finalIsUpdate = isUpdate;
            mSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Retrieving data from editText
                    String text =mEditText.getText().toString();
                    // now checking if user want to update it or just check it
                    if (finalIsUpdate) {
                        myDb.updateTask(bundle.getInt("id"),text);
                        // if he wants to update we call updateTask() method and pass id stored in bundle

                    }else { //  if he does not want to update the task we call setTask() as pass text 0
                        ToDoModel item= new ToDoModel();
                        item.setTask(text);
                        item.setStatus(0);
                        myDb.insertTask(item);
                    }
                    dismiss();// dismissed the fragment here
                    // now we'll take care of dismiss method i.e. when this fragment would be dismissed
                    // recycler View should be refreshed and its data should be refreshed
                }
            });
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity =getActivity();// collecting the context
        //now we want to communicate b/w fragment and activity so wce should use interface for this
        // so creating new interface named OnDialogCloseListner.java
        if (activity instanceof OnDialogCloseListner){
            ((OnDialogCloseListner)activity).onDialogClose(dialog);

        }
    }
}
