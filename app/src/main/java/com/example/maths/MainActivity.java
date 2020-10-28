package com.example.maths;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView expression, scores;
    Button yes, no;
    boolean f;
    int level,score, count = 0;
    int wait = 2;
    int where = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yes = findViewById(R.id.buttYes);
        no = findViewById(R.id.buttNo);
        expression = findViewById(R.id.expression);
        scores = findViewById(R.id.scores);
        f = true;
        level = 1;
        score = 0;
        game(level);
    }



    public void game(int lvl){
        scores.setText(score+"");
        String[] primer = exp(lvl);
        String s = primer[0];
        int result = Integer.parseInt(primer[1]);
        Random r = new Random();
        String topText = s.substring(0,s.indexOf("=")-1);
        String botText = s.substring(s.indexOf("="));
        expression.setText(topText + "\n" + botText);
        Double d = Math.random();
        if(d >= 0.5) {
            where = 1;//true
            expression.setText(topText + "\n" + botText);
        }
        else {
            where = 0;//false
            d = Math.random();
            if(d >= 0.5)
                result += r.nextInt(10)+1;
            else
                result -= r.nextInt(10)+1;
            expression.setText(topText + "\n= " + result);
        }
    }

    public static String[] exp(int level){
        int x,y,op;
        double result;
        Random r = new Random();
        String[] res = new String[2];
        char operation;
        x = (int) Math.round(Math.random()*10*level)+1;
        y = (int) Math.round(Math.random()*10*level)+1;
        op = r.nextInt(2);
        switch (op){
            case 0: operation = '+'; result = x + y;break;
            case 1: operation = '-'; result = x - y;break;
            //case 2: operation = '*'; result = x * y;break;
            //case 3: operation = '/'; result = x / y;break;
            default: operation = ']'; result = -1;break;
        }
        res[0] = x + " " + operation + " " + y + " = " + Math.round(result);
        res[1] = "" + Math.round(result);
        return res;
    }

    public void onYesClick(View view) {
        if(!f){
            level = 1;
            score = 0;
            game(level);
        }
        else if(where == 1){
            score += 1;
            game(++level);
        }
        else{
            f = false;
            expression.setText(score + "\nGame over");
        }
    }

    public void onNoClick(View view) {
        if(!f){
            level = 1;
            score = 0;
            game(level);
        }
        else if(where == 0){
            score += 1;
            game(++level);
        }
        else{
            f = false;
            expression.setText(score + "\nGame over");
        }
    }
}