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

    public static final int[] COLORS = new int[] {
            Color.argb(255, 255, 51, 88), //gaypink
            Color.argb(255, 51, 104, 255), //blu
            Color.argb(255, 255, 252, 51), //yello
            Color.argb(255, 255, 116, 51), //orangé
            Color.argb(255, 255, 51, 88), //gaypink
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
        productivityViewHolder.itemView.setBackgroundColor(COLORS[record.machine-1]);

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