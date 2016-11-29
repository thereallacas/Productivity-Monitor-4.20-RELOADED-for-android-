package com.shark.lacas.productivitymonitor420;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by macbookpro on 26/11/16.
 */
public class ProductivityAdapter extends RealmBasedRecyclerViewAdapter<ProductivityRecord, ProductivityViewHolder> {

    private static final int[] COLORS = new int[] {
            Color.argb(255, 28, 160, 170),
            Color.argb(255, 99, 161, 247),
            Color.argb(255, 13, 79, 139),
            Color.argb(255, 89, 113, 173),
            Color.argb(255, 200, 213, 219),
            Color.argb(255, 200, 213, 219),
            Color.argb(255, 200, 213, 219)

    };

    public ProductivityAdapter(Context context, RealmResults<ProductivityRecord> realmResults, boolean automaticUpdate, boolean animateResults) {
        super(context, realmResults, automaticUpdate, animateResults);
    }


    @Override
    public ProductivityViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        View recordView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record, viewGroup, false);
        ProductivityViewHolder viewHolder = new ProductivityViewHolder(recordView);
        return viewHolder;
    }

    @Override
    public void onBindRealmViewHolder(ProductivityViewHolder productivityViewHolder, int i) {
        final ProductivityRecord record = realmResults.get(i);

        productivityViewHolder.timeView.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(record.time));
        productivityViewHolder.totalMoneyTextView.setText(String.valueOf(record.totalMoney));
        productivityViewHolder.minuteTextView.setText(String.valueOf(record.minute));
        productivityViewHolder.machineID.setText(String.valueOf(record.machine));
        productivityViewHolder.sexeImageView.setImageResource(getSexeImageResource(record.fn));
        productivityViewHolder.itemView.setBackgroundColor(COLORS[record.machine]);

    }

    private @DrawableRes int getSexeImageResource(String fn) {
        int ret;
        switch (fn) {
            case "@+string/man":
                ret = R.drawable.male_icon;
                break;
            case "@+string/woman":
                ret = R.drawable.female_icon;
                break;
            default:
                ret = 0;
        }
        return ret;
    }

}