package com.sajjad.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sajjad.sqlite.SQLManagement.SampleModel;
import com.sajjad.sqlite.SQLManagement.SampleSQLiteHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView sample;
    EditText sampleName;
    SampleSQLiteHelper sampleSQLiteHelper;
    List<SampleModel> sampleModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sample = findViewById(R.id.Sample);
        sampleName = findViewById(R.id.sampleName);

        sampleSQLiteHelper = new SampleSQLiteHelper(this);

    }

    public void GetAllData(View view) {
        sampleModels = sampleSQLiteHelper.GetAllSamples();
        sample.setText(sampleModels.get(0).getSampleTemp()+" "+sampleModels.get(0).getSampleName());
    }

    public void UpdateSample(View view) {
        sampleSQLiteHelper.UpdateData(1, sampleName.getText().toString(), "1");
    }
}