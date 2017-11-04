package edu.purdue.qingdong.unitconverterv10;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ThermometerActivity extends AppCompatActivity {

    EditText inputTemp;
    Spinner spinnerTemp;
    TextView Ctext;
    TextView Ftext;
    TextView Ktext;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);
        inputTemp = (EditText) findViewById(R.id.inputTemp);
        spinnerTemp = (Spinner) findViewById(R.id.spinnerTemp);
        Ctext = (TextView) findViewById(R.id.CText);
        Ftext = (TextView) findViewById(R.id.FText);
        Ktext = (TextView) findViewById(R.id.KText);
        inputTemp.setText("1");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.temperature_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerTemp.setAdapter(adapter);
        inputTemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ThermometerThread thermometerThread = new ThermometerThread();
                thermometerThread.start();
            }
        });
        spinnerTemp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ThermometerThread thermometerThread = new ThermometerThread();
                thermometerThread.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message meg){
                String[] tempStr = (String[]) meg.obj;
                Ctext.setText(tempStr[0]);
                Ftext.setText(tempStr[1]);
                Ktext.setText(tempStr[2]);
            }
        };
    }

    class ThermometerThread extends Thread {
        @Override
        public void run(){
            int position = spinnerTemp.getSelectedItemPosition();
            String temp = inputTemp.getText().toString();

            double celsius = 0.0;
            double fahrenheit = 0.0;
            double k = 0.0;
            double input = 0.0;

            if (!temp.equals("")) {
                input = Double.parseDouble(temp);
            }

            switch (position) {
                case 0://celsius
                    celsius = input;
                    fahrenheit = celsius * 9 / 5 + 32;
                    k = celsius + 273.15;
                    break;

                case 1:
                    // fahrenheit
                    fahrenheit = input;
                    celsius = (fahrenheit - 32) * 5 / 9;
                    k = celsius + 273.15;
                    break;

                case 2://k
                    k = input;
                    celsius = k - 273.15;
                    fahrenheit = celsius * 9 / 5 + 32;
                    break;

            }

//            String celsiusStr = String.format("%.4f", celsius);
//            String fahrenheitStr = String.format("%.4f", fahrenheit);
//            String kStr = String.format("%.4f", k);
            String[] tempStr = {"" + celsius, "" + fahrenheit, "" + k};
            Message meg = handler.obtainMessage();
            meg.obj = tempStr;
            handler.sendMessage(meg);
        }
    }
    public void onLength(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onWeight(View v) {
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);

    }

    public void onHelp(View v) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
}
