package com.example.calculapreo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView finalPriceTextView;
    private TextView freteValueTextView;
    private EditText productPrice;
    private EditText pesoTextView;
    private CheckBox checkBoxGift;
    private CheckBox checkBoxExpress;
    private RadioGroup radioGroupPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productPrice = findViewById(R.id.productPrice);
        finalPriceTextView = findViewById(R.id.finalPriceTextView);
        checkBoxGift = findViewById(R.id.checkBoxGift);
        checkBoxExpress = findViewById(R.id.checkBoxExpress);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate(v);
            }
        };
        checkBoxGift.setOnClickListener(listener);
        checkBoxExpress.setOnClickListener(listener);
        radioGroupPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculate(group);
            }
        });

    }

    public void calculateFrete(View view){
        freteValueTextView = findViewById(R.id.freteValue);
        pesoTextView = findViewById(R.id.pesoInput);

        Double peso = 0.0;
        peso = Double.parseDouble(pesoTextView.getText().toString());

        Locale locale = new Locale("pt", "BR");
        freteValueTextView.setText(NumberFormat.getCurrencyInstance(locale).format(10+(4*peso)));
    }

    private double calculateFreteToFinalValue(){
        freteValueTextView = findViewById(R.id.freteValue);
        pesoTextView = findViewById(R.id.pesoInput);

        Double peso = 0.0;
        peso = Double.parseDouble(pesoTextView.getText().toString());
        return 10+(4*peso);
    }

    public void calculate(View view){
        Double price = 0.0;
        try {
            price = Double.parseDouble(productPrice.getText().toString());
        }catch (Exception e){
            productPrice.requestFocus();
        }
        Double finalPrice = price;

        if(checkBoxGift.isChecked()){
            finalPrice += 5.00;
        }
        if(checkBoxExpress.isChecked()){
            finalPrice += 12.00;
        }

        switch (radioGroupPayment.getCheckedRadioButtonId()){
            case R.id.radioButton1Card:
                finalPrice += 0.03*price;
                break;
            case R.id.radioButton3Card:
                finalPrice += 0.06*price;
                break;
            case R.id.radioButton6Card:
                finalPrice += 0.09*price;
                break;
        }

        Locale locale = new Locale("pt", "BR");
        finalPrice += calculateFreteToFinalValue();

        finalPriceTextView.setText(NumberFormat.getCurrencyInstance(locale).format(finalPrice));
    }
}