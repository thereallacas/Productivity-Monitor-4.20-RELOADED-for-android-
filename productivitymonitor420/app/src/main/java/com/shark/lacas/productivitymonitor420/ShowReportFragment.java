package com.shark.lacas.productivitymonitor420;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by macbookpro on 28/11/16.
 */
public class ShowReportFragment extends AppCompatDialogFragment {

    public static final String TAG = "NewRecordDialogFragment";

    public Date startdate;
    public Date enddate;
    private Realm realm;
    private TextView numberOfCustomers;
    private GraphView graph;
    private TextView income;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        Bundle setDate = this.getArguments();
        Long startDateLONG = setDate.getLong("STARTDATE");
        Long endDateLONG = setDate.getLong("ENDDATE");
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(startDateLONG);
        startdate = c.getTime();
        c.setTimeInMillis(endDateLONG);
        enddate = c.getTime();
        System.out.println("STARTDATE"+startdate.toString());
        System.out.println("ENDDATE"+enddate.toString());
        realm = Realm.getDefaultInstance();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext()).setTitle(R.string.total_stat)
                .setIcon(R.drawable.ic_assignment).setView(getContentView()).setPositiveButton(R.string.ok, null)
                .setNegativeButton(R.string.cancel, null).create();
    }

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_show_report,null);
        graph = (GraphView) contentView.findViewById(R.id.GRAPH);
        income = (TextView) contentView.findViewById(R.id.INCOMETEXTVIEW);
        numberOfCustomers = (TextView) contentView.findViewById(R.id.NUMBEROFCUSTOMERS);

        RealmQuery<ProductivityRecord> queryForDatDay = realm.where(ProductivityRecord.class).between("time",startdate,enddate);
        numberOfCustomers.setText(String.valueOf(queryForDatDay.count()));
        income.setText(String.valueOf(queryForDatDay.sum("totalMoney"))+" Ft");

        double v1 = realm.where(ProductivityRecord.class).between("time",startdate,enddate).equalTo("machine", 1).count();
        double v2 = realm.where(ProductivityRecord.class).between("time",startdate,enddate).equalTo("machine", 2).count();
        double v3 = realm.where(ProductivityRecord.class).between("time",startdate,enddate).equalTo("machine", 3).count();
        double v4 = realm.where(ProductivityRecord.class).between("time",startdate,enddate).equalTo("machine", 4).count();
        double v5 = realm.where(ProductivityRecord.class).between("time",startdate,enddate).equalTo("machine", 5).count();

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, v1),
                new DataPoint(2, v2),
                new DataPoint(3, v3),
                new DataPoint(4, v4),
                new DataPoint(5, v5)
        });
        graph.removeAllSeries();
        graph.addSeries(series);

// styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return ProductivityAdapter.COLORS[(int)data.getX()-1];
            }
        });

        series.setSpacing(10);

        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getActivity(), (int)dataPoint.getX()+getString(R.string.sol)+(int)dataPoint.getY()+getString(R.string.customer), Toast.LENGTH_SHORT).show();
            }
        });


        return contentView;
    }


}
