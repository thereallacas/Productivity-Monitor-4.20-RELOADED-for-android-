package com.shark.lacas.productivitymonitor420;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by macbookpro on 26/11/16.
 */
public class ProductivityViewHolder extends RecyclerView.ViewHolder {
    public ProductivityViewHolder(View itemView){
        super(itemView);
        sexeImageView = (ImageView) itemView.findViewById(R.id.SEXEIMAGEVIEW);
        machineID = (TextView) itemView.findViewById(R.id.MACHINEIDTEXTVIEW);
        minuteTextView = (TextView) itemView.findViewById(R.id.MINUTETEXTVIEW);
        totalMoneyTextView = (TextView) itemView.findViewById(R.id.TOTALMONEYTEXTVIEW);
        timeView = (TextView) itemView.findViewById(R.id.TIMEVIEW);
    }
    ImageView sexeImageView;
    TextView machineID;
    TextView minuteTextView;
    TextView totalMoneyTextView;
    TextView timeView;


}
