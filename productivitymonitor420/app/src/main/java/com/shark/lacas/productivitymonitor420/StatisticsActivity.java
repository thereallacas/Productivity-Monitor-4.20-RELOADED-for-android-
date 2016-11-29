package com.shark.lacas.productivitymonitor420;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.Realm;
import io.realm.RealmQuery;

public class StatisticsActivity extends AppCompatActivity {

    Button showReport;
    Button sendReport;
    DatePicker datePicker;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        showReport = (Button) findViewById(R.id.SHOWREPORTBUTTON);
        sendReport = (Button) findViewById(R.id.SENDREPORTBUTTON);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        realm = Realm.getDefaultInstance();
    }

    public void ONCLICKSENDREPORT(View v){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        Calendar c = Calendar.getInstance();
        c.set(year, month, day, 6, 0);
        Date startdate = c.getTime();
        c.set(year,month,day,22,0);
        Date enddate = c.getTime();

        sendEmail(startdate,enddate);

    }

    public void ONCLICKSHOWREPORT(View v){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        Calendar c = Calendar.getInstance();
        c.set(year, month, day, 6, 0);
        Long startdate = c.getTimeInMillis();
        c.set(year,month,day,22,0);
        Long enddate = c.getTimeInMillis();

        ShowReportFragment fragment = new ShowReportFragment();
        Bundle dateBundle = new Bundle();
        dateBundle.putLong("STARTDATE",startdate);
        dateBundle.putLong("ENDDATE", enddate);
        fragment.setArguments(dateBundle);
        fragment.show(getSupportFragmentManager(),ShowReportFragment.TAG);
    }

    protected void sendEmail(Date startDate, Date endDate) {
        Log.i("Send email", "");
        String[] TO = {"lacas.shark@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Report: "+endDate.toString());
        RealmQuery<ProductivityRecord> queryForDatDay = realm.where(ProductivityRecord.class).between("time",startDate,endDate);
        String numberOfCustomers = String.valueOf(queryForDatDay.count());
        String income = String.valueOf(queryForDatDay.sum("totalMoney"))+" Ft";
        emailIntent.putExtra(Intent.EXTRA_TEXT, endDate.toString()+" report: \n"+"Vendégek száma: "+numberOfCustomers+"\n"+
        "Teljes bevétel: ");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(StatisticsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
