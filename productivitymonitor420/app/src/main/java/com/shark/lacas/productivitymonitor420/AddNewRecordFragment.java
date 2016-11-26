package com.shark.lacas.productivitymonitor420;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by macbookpro on 26/11/16.
 */
public class AddNewRecordFragment extends AppCompatDialogFragment {
    public static final String TAG = "NewRecordDialogFragment";

    private EditText minutesEditText;
    private EditText totalEditText;
    private NumberPicker machinePicker;
    private RadioGroup sexeSelectGroup;
    private RadioButton selectedSexe;

    public interface INewRecordDialogListener {
        void onRecordCreated(ProductivityRecord newRecord);
    }

    private INewRecordDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof INewRecordDialogListener) {
            listener = (INewRecordDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the INewRecordDialogListener interface!");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext()).setTitle(R.string.addnewrecord)
                .setIcon(R.drawable.rectangle).setView(getContentView()).setPositiveButton(R.string.add, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        }).setNegativeButton(R.string.cancel, null).create();
    }

    private View getContentView() {
        View contentview = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_record,null);
        totalEditText = 
    }
}