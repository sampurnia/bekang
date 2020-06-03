package com.example.bekang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class SensorListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_dummydata);
        new FirebaseDatabaseHelper().readSensors(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Sensor> dummydata, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, SensorListActivity.this ,
                        dummydata, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
