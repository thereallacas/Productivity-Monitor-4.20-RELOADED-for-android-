package com.shark.lacas.productivitymonitor420;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by macbookpro on 26/11/16.
 */
public class ProductivityAdapter extends RecyclerView.Adapter<ProductivityViewHolder>{
    public final List<ProductivityRecord> records;

    public ProductivityAdapter() {
        records = new ArrayList<>();
    }


    @Override
    public ProductivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        ProductivityViewHolder viewHolder = new ProductivityViewHolder(recordView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductivityViewHolder holder, int position) {
        ProductivityRecord record = records.get(position);
        holder.timeView.setText(DateFormat.getDateTimeInstance().format(record.time));
        holder.totalMoneyTextView.setText(String.valueOf(record.totalMoney));
        holder.minuteTextView.setText(String.valueOf(record.minute));
        holder.machineID.setText(getMachineImageResource(record.machine));
        holder.sexeImageView.setImageResource(getSexeImageResource(record.fn));
    }

    private @DrawableRes int getSexeImageResource(ProductivityRecord.FN fn) {
        int ret;
        switch (fn) {
            case FERFI:
                ret = R.drawable.male_icon;
                break;
            case NO:
                ret = R.drawable.female_icon;
                break;
            default:
                ret = 0;
        }
        return ret;
    }


    //DONT BE SURPRISED ITS FOR FURTHER DEVELOPMENT OKAY????!!
    private int getMachineImageResource(ProductivityRecord.Machine machine) {
        int ret;
        switch (machine) {
            case FIRST:
                ret = 1;
                break;
            case SECOND:
                ret = 2;
                break;
            case THIRD:
                ret = 3;
                break;
            case FOURTH:
                ret = 3;
                break;
            case FIFTH:
                ret = 3;
                break;
            default:
                ret = 0;
        }
        return ret;
    }

    public void addItem(ProductivityRecord record) {
        records.add(record);
        notifyItemInserted(records.size() - 1);
    }

    public void update(List<ProductivityRecord> productivityRecords) {
        records.clear();
        records.addAll(productivityRecords);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return records.size();
    }

}