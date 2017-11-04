package edu.purdue.qingdong.unitconverterv10;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class WeightActivity extends AppCompatActivity {

     EditText inputWeight;
    Spinner spinnerWeight;
    static TextView mgText;
    static TextView gText;
    static TextView kgText;
    static TextView tonText;
    static TextView ozText;
    static TextView lbText;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        inputWeight = (EditText) findViewById(R.id.inputWeight);
        spinnerWeight = (Spinner) findViewById(R.id.spinnerWeight);
        mgText = (TextView) findViewById(R.id.mgText);
        gText = (TextView) findViewById(R.id.gText);
        kgText = (TextView) findViewById(R.id.kgText);
        tonText = (TextView) findViewById(R.id.tonText);
        ozText = (TextView) findViewById(R.id.ozText);
        lbText = (TextView) findViewById(R.id.lbText);
        inputWeight.setText("1");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weight_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerWeight.setAdapter(adapter);
        inputWeight.addTextChangedListener(watcher);
        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                WeightThread weightThread = new WeightThread();
                weightThread.start();

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message meg) {
                String[] weightStr = (String[]) meg.obj;
                String gStr = weightStr[0];
                String mgStr = weightStr[1];
                String kgStr = weightStr[2];
                String tonStr = weightStr[3];
                String ozStr = weightStr[4];
                String lbStr = weightStr[5];
                gText.setText(gStr);
                mgText.setText(mgStr);
                kgText.setText(kgStr);
                tonText.setText(tonStr);
                ozText.setText(ozStr);
                lbText.setText(lbStr);
            }

        };

    }


    class WeightThread extends Thread {
        @Override
        public void run() {
            String weight = inputWeight.getText().toString();
            int iCurrentSelection = spinnerWeight.getSelectedItemPosition();
            double mg = 0.0;
            double g = 0.0;
            double kg = 0.0;
            double ton = 0.0;
            double oz = 0.0;
            double lb = 0.0;
            double input = 0.0;

            if (!weight.equals("")) {
                input = Double.parseDouble(weight);
            }

            switch (iCurrentSelection) {
                case 0://g
                    g = input;
                    mg = g * 1000;
                    kg = g / 1000;
                    ton = kg / 1000;
                    lb = g * 0.00220462;
                    oz = g * 0.035274;
                    break;

                case 1:
                    // mg
                    mg = input;
                    g = mg / 1000;
                    kg = g / 1000;
                    ton = kg / 1000;
                    lb = g * 0.00220462;
                    oz = g * 0.035274;
                    break;

                case 2://kg
                    kg = input;
                    g = kg * 1000;
                    mg = g * 1000;
                    ton = kg / 1000;
                    lb = g * 0.00220462;
                    oz = g * 0.035274;
                    break;

                case 3://ton
                    ton = input;
                    kg = ton * 1000;
                    g = kg * 1000;
                    mg = g * 1000;
                    lb = g * 0.00220462;
                    oz = g * 0.035274;
                    break;

                case 4://oz
                    oz = input;
                    kg = oz * 0.0283495;
                    g = kg * 1000;
                    mg = g / 1000;
                    ton = kg / 1000;
                    lb = oz * 0.0625;
                    break;
                case 5://lb
                    lb = input;
                    oz = lb * 16;
                    kg = oz * 0.0283495;
                    g = kg * 1000;
                    mg = g * 1000;
                    ton = kg / 1000;
                    break;

            }

            //convert double to string with 4 decimals
//            String mgStr = String.format("%.10f",mg);
//            String gStr = String.format("%.10f",g);
//            String kgStr = String.format("%.10f",kg);
//            String tonStr = String.format("%.10f",ton);
//            String ozStr = String.format("%.10f",oz);
//            String lbStr = String.format("%.10f",lb);

            String[] weightStr = {"" + g, "" + mg, "" + kg, "" + ton, "" + oz, "" + lb};
            Message msg = handler.obtainMessage();
            msg.obj = weightStr;
            handler.sendMessage(msg);
        }
    }

    TextWatcher watcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            WeightThread weightThread = new WeightThread();
            weightThread.start();

        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    };


    public void onLength(View v) {

        Intent intent = new Intent(this, MainActivity.class);
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
