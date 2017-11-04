package edu.purdue.qingdong.unitconverterv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnLength;
    Button btnWeight;
    Button btnThermometer;
    Button btnHelp;
    static Spinner spinner;
    static TextView mmText;
    static TextView cmText;
    static TextView mText;
    static TextView kmText;
    static TextView ftText;
    static TextView ydText;
    static TextView mileText;
    static TextView inchText;
    static EditText inputLength;
//    private static final int INCH = 0;
//    private static final int MM = 0;
//    private static final int CM = 0;
//    private static final int M = 0;
//    private static final int KM = 0;
//    private static final int FT = 0;
//    private static final int YD = 0;
//    private static final int MILE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLength = (Button) findViewById(R.id.btnLength);
        btnWeight = (Button) findViewById(R.id.btnWeight);
        btnThermometer = (Button) findViewById(R.id.btnThermometer);
        btnHelp = (Button) findViewById(R.id.btnHelp);
        spinner = (Spinner) findViewById(R.id.spinner);
        inputLength = (EditText) findViewById(R.id.inputLength);
        inputLength.setText("1");
        mmText = (TextView) findViewById(R.id.mmText);
        cmText = (TextView) findViewById(R.id.cmText);
        mText = (TextView) findViewById(R.id.mText);
        kmText = (TextView) findViewById(R.id.kmText);
        ftText = (TextView) findViewById(R.id.ftText);
        ydText = (TextView) findViewById(R.id.ydText);
        mileText = (TextView) findViewById(R.id.mileText);
        inchText = (TextView) findViewById(R.id.inchText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.length_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        inputLength.addTextChangedListener(watcher);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String input = inputLength.getText().toString();
                displayLength(input, position);

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public static void displayLength(String length, int iCurrentSelection) {
        double inch = 0.0;
        double mm = 0.0;
        double cm = 0.0;
        double m = 0.0;
        double km = 0.0;
        double ft = 0.0;
        double mile = 0.0;
        double yd = 0.0;
        double d = 0.0;

        if (!length.equals("")) {
            d = Double.parseDouble(length);
        }

        switch (iCurrentSelection) {
            case 0:
                // inch
                inch = d;
                mm = 25.4 * d;
                cm = 2.54 * d;
                m = 0.0254 * d;
                km = m / 1000;
                ft = d / 12;
                yd = inch / 36;
                mile = (d / 12) / 5280;
                break;
            case 1://mm
                mm = d;
                cm = mm / 10;
                m = mm / 100;
                km = m / 1000;
                inch = mm / 25.4;
                ft = inch / 12;
                yd = ft / 3;
                mile = yd / 1760;

                break;

            case 2://cm

                cm = d;
                mm = cm * 10;
                m = cm / 100;
                km = m / 1000;
                inch = mm / 25.4;
                ft = inch / 12;
                yd = ft / 3;
                mile = yd / 1760;
                break;

            case 3://m

                m = d;
                mm = m * 1000;
                cm = mm / 10;
                km = m / 1000;
                inch = mm / 25.4;
                ft = inch / 12;
                yd = ft / 3;
                mile = yd / 1760;
                break;

            case 4://km

                km = d;
                m = km * 1000;
                mm = m * 1000;
                cm = mm / 10;
                inch = mm / 25.4;
                ft = inch / 12;
                yd = ft / 3;
                mile = yd / 1760;

                break;
            case 5://ft

                ft = d;
                inch = ft * 12;
                m = 0.0254 * inch;
                km = m / 1000;
                mm = m * 1000;
                cm = mm / 10;
                yd = ft / 3;
                mile = yd / 1760;

                break;
            case 6://yd

                yd = d;
                ft = yd * 3;
                inch = ft * 12;
                m = 0.0254 * inch;
                km = m / 1000;
                mm = m * 1000;
                cm = mm / 10;
                mile = yd / 1760;

                break;
            case 7://mile

                mile = d;
                yd = mile * 1760;
                ft = yd * 3;
                inch = ft * 12;
                m = 0.0254 * inch;
                km = m / 1000;
                mm = m * 1000;
                cm = mm / 10;
                break;

        }

        //convert double to string with 4 decimals
//        String mmStr = String.format("%.10f",mm);
//        String cmStr = String.format("%.10f",cm);
//        String mStr = String.format("%.10f",m);
//        String kmStr = String.format("%.10f",km);
//        String ydStr = String.format("%.10f",yd);
//        String inchStr = String.format("%.10f",inch);
//        String mileStr = String.format("%.10f",mile);
//        String ftStr = String.format("%.10f",ft);

        mmText.setText(mm + "");
        cmText.setText(cm + "");
        mText.setText(m + "");
        kmText.setText(km + "");
        ydText.setText(yd + "");
        inchText.setText(inch + "");
        ftText.setText(ft + "");
        ydText.setText(yd + "");
        mileText.setText(mile + "");
    }

    TextWatcher watcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {

            String length = inputLength.getText().toString();
            int iCurrentSelection = spinner.getSelectedItemPosition();
            displayLength(length, iCurrentSelection);
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    };


    public void onWeight(View v) {
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);


    }
    public void onThermometer(View v) {

        Intent intent = new Intent(this, ThermometerActivity.class);
        startActivity(intent);
    }
    public void onHelp(View v) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);

    }
}
