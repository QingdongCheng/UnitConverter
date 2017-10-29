package edu.purdue.qingdong.unitconverterv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThermometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);
    }

    public void onLength(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onWeight(View v) {


        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);

    }
    public void onThermometer(View v) {

    }
    public void onHelp(View v) {

        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
}
