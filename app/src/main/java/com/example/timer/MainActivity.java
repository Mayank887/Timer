package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity {
    Button start,reset;
    TextView tv3,tv4,tv5;
    EditText et1,et2,et3;
    CountDownTimer countDownTimer;
    Vibrator vibrator;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.editTextTextPersonName);
        et2=findViewById(R.id.editTextTextPersonName2);
        et3=findViewById(R.id.editTextTextPersonName3);
        tv3=findViewById(R.id.textView2);
        tv4=findViewById(R.id.textView4);
        tv5=findViewById(R.id.textView5);
        start=findViewById(R.id.button);

        reset=findViewById(R.id.button2);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                et1.setText("");
                et2.setText("");
                et3.setText("");

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();



            }
        });



    }
    public void startTimer(){
        if(et1.getText().toString().length()==0 || et2.getText().toString().length()==0 || et3.getText().toString().length()==0){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
            int hr=0;
             hr=Integer.parseInt(et1.getText().toString());
             int min=0;
             min=Integer.parseInt(et2.getText().toString());
            int sec=0;
             sec=Integer.parseInt(et3.getText().toString());
            int s=(hr*3600000)+(min*60000)+(sec*1000);
            int h=s/3600000;
            int m=(s%3600000)/60000;
            int sr=(s%3600000)%60000;


            countDownTimer=new CountDownTimer (s,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long h=millisUntilFinished/3600000;
                    long m=(millisUntilFinished%3600000)/60000;
                    long sr=(millisUntilFinished%3600000)%60000;
                    String input=""+h+" : "+m+" : "+sr/100;

                    tv4.setText(""+h);
                    tv3.setText("" + m);
                    tv5.setText(""+sr/1000);
                    Intent intent=new Intent(getApplicationContext(),MyService.class);
                    intent.putExtra("data",input);

                }

                @Override
                public void onFinish() {
                    tv3.setText("Time up!");
                    tv4.setText("");
                    tv5.setText("");
                    vibrator.vibrate(2000);



                }

            }.start();

        }

    }
    private void resetTimer(){
        countDownTimer.cancel();
        tv4.setText("");
        tv3.setText("" );
        tv5.setText("");




    }

    }
