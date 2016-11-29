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
import android.widget.Toast;

import java.util.Date;

import io.realm.Realm;

/**
 * Created by macbookpro on 26/11/16.
 */
public class AddNewRecordFragment extends AppCompatDialogFragment {
    public static final String TAG = "NewRecordDialogFragment";

    private EditText minutesEditText;
    private EditText totalEditText;
    private NumberPicker machinePicker;
    private RadioGroup sexeSelectGroup;
    private RadioButton ferfiradiobutton;
    private  RadioButton noiradiobutton;
    private int selectedSexeIndex;
    private Realm realm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof INewRecordDialogListener) {
            listener = (INewRecordDialogListener) activity; } else {
            throw new RuntimeException("Activity must implement the INewRecordDialogListener interface!");
        }
        realm = Realm.getDefaultInstance();

    }

    public interface INewRecordDialogListener {
        void onRecordCreated(ProductivityRecord newItem);
    }

    private INewRecordDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext()).setTitle(R.string.addnewrecord)
                .setIcon(R.drawable.rectangle).setView(getContentView()).setPositiveButton(R.string.add, new
                        DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (isValid()) {
                                    ProductivityRecord newRecord = getRecord();
                                    listener.onRecordCreated(newRecord);
                                }
                                else {
                                    Toast
                                            .makeText(getContext(), "Percek száma?", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        }).setNegativeButton(R.string.cancel, null).create();
    }

    private ProductivityRecord getRecord() {
        ProductivityRecord newRecord = new ProductivityRecord();
        newRecord.machine = machinePicker.getValue();
        newRecord.time = new Date();
        selectedSexeIndex = sexeSelectGroup.getCheckedRadioButtonId();
        if (selectedSexeIndex == R.id.RADIO_FERFI)
            newRecord.fn = "@+string/man";
        else
            newRecord.fn = "@+string/woman";
        newRecord.minute = Integer.parseInt(minutesEditText.getText().toString());
        newRecord.totalMoney = totalEditText.getText().length()>0 ? Integer.parseInt(totalEditText.getText().toString()) : newRecord.getPrice();

        Number next = realm.where(ProductivityRecord.class).max("ID");
        int ID = 0;
        if (next!=null)
            ID = next.intValue()+1;
        newRecord.ID = ID;
        return newRecord;
    }


    //TODO: SOPHISTICATED METHODS
    private boolean isValid() {
        return minutesEditText.getText().length() > 0;
    }


    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_record,null);
        totalEditText = (EditText) contentView.findViewById(R.id.SUMTEXTINPUT);
        minutesEditText = (EditText) contentView.findViewById(R.id.MINUTETEXTINPUT);
        machinePicker = (NumberPicker) contentView.findViewById(R.id.MACHINEPICKER);
        machinePicker.setMaxValue(5);
        machinePicker.setMinValue(1);
        sexeSelectGroup = (RadioGroup) contentView.findViewById(R.id.SEXEGROUPPE);
        ferfiradiobutton = (RadioButton) contentView.findViewById(R.id.RADIO_FERFI);
        noiradiobutton = (RadioButton) contentView.findViewById(R.id.RADIO_NO);
        sexeSelectGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedSexeIndex = i;
            }
        });
        return contentView;
    }


    @Override
    public void onStop() {
        super.onStop();
        realm.close();
    }
}