package com.example.calculapreo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView precoFinal;
    private EditText precoProduto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        precoProduto = findViewById(R.id.precoProduto);
        precoFinal = findViewById(R.id.precoFInal);
    }

    public void calcular(View view){
        precoFinal.setText(precoProduto.getText());
    }
}