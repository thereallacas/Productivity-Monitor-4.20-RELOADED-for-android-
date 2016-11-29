package com.shark.lacas.productivitymonitor420;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by macbookpro on 26/11/16.
 */
public class ProductivityRecord extends RealmObject {

    private static final int MINUTEPRICE = 80;

    public int minute;
    public int totalMoney;
    public int machine;
    public String fn;
    public Date time;
    private int price;
    @PrimaryKey
    public int ID;

    public int getPrice(){
        return minute*MINUTEPRICE;
    }
}
