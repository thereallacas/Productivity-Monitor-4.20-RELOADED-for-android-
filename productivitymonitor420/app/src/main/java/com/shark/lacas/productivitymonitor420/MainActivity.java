package com.shark.lacas.productivitymonitor420;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends RealmBaseActivity implements AddNewRecordFragment.INewRecordDialogListener {

    private RealmRecyclerView recyclerView;
    private ProductivityAdapter productivityAdapter;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Bevétel");
        setSupportActionBar(toolbar);

        realm =  Realm.getInstance(getRealmConfig());
        RealmResults<ProductivityRecord> records= realm.where(ProductivityRecord.class).findAll();
        productivityAdapter = new ProductivityAdapter(this, records, true, true);
        recyclerView = (RealmRecyclerView) findViewById(R.id.realm_recycler_view);
        recyclerView.setAdapter(productivityAdapter);

        Log.d("", "path: " + realm.getPath());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new AddNewRecordFragment().show(getSupportFragmentManager(),AddNewRecordFragment.TAG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_statistics) {
            startStatisticsActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void startStatisticsActivity() {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRecordCreated(ProductivityRecord newRecord) {
        addRecord(newRecord);
    }

    private void addRecord(ProductivityRecord newRecord) {
        realm.beginTransaction();
        realm.copyToRealm(newRecord);
        realm.commitTransaction();
    }
}
