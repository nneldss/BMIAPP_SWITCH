package com.demo.bmiapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText  height, weight;
    String  strheight, strweight, resultMessage;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCompute = (Button) findViewById(R.id.btnCompute);

        btnCompute.setOnClickListener(this);

    }

    public void onClick(View v){
        Toast.makeText(this, "Calculating BMI...", Toast.LENGTH_SHORT).show();
        ComputeResult();
    }

    public void ComputeResult(){
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        if(weight.getText().toString().isEmpty() || height.getText().toString().isEmpty()){
            strweight = "0";
            strheight = "0";
        }
        else{
            strweight = weight.getText().toString();
            strheight = height.getText().toString();
        }
        double rawweight = Double.parseDouble(strweight);
        double rawheight = Double.parseDouble(strheight);

        double result = 703 * (rawweight/(rawheight*rawheight));

        switch((int) result){
            case 19:
                result = 19;
                break;
            case 25:
                result = 25;
                break;
            case 30:
                result = 30;
                break;
        }



        resultMessage = (int) result + " " + "is the BMI";

        Bundle args = new Bundle();
        args.putString("result", resultMessage);

        Intent intent;


        if(result <= 19) {
            intent = new Intent(MainActivity.this, result1.class);
        }
        else if (result <= 25) {
            intent = new Intent(MainActivity.this, result2.class);
        }
        else {
            intent = new Intent(MainActivity.this, result3.class);
        }

        intent.putExtras(args);
        startActivity(intent);
        clearEditText();
    }

    public void clearEditText(){
        weight.getText().clear();
        height.getText().clear();
        weight.requestFocus();
    }

}