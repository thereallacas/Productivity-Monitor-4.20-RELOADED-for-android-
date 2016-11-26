package com.shark.lacas.productivitymonitor420;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by macbookpro on 26/11/16.
 */
public class ProductivityRecord extends SugarRecord {

    private static final int MINUTEPRICE = 80;

    public enum Machine{
        FIRST,SECOND,THIRD,FOURTH,FIFTH;

        public static Machine getByOrdinal(int ordinal){
            Machine ret = null;
            for (Machine m : Machine.values()){
                if (m.ordinal() == ordinal){
                    ret = m;
                    break;
                }
            }
            return ret;
        }
    }
    public enum FN{
        FERFI, NO;
        public static FN getByOrdinal(int ordinal){
            FN ret = null;
            for (FN fn : FN.values()){
                if (fn.ordinal() == ordinal){
                    ret = fn;
                    break;
                }
            }
            return ret;
        }
    }
    public int minute;
    public int totalMoney;
    public Machine machine;
    public FN fn;
    public Date time;
    private int price;

    public int getPrice(){
        return minute*MINUTEPRICE;
    }

}
