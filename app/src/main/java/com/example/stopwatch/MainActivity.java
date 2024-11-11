package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button stopButton, startButton, resetButton;

    private TextView textView;

    boolean isRunning=false;
    private Handler handler=new Handler();

    private long startTime=0l;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if(isRunning){

                long elapsedTime = System.currentTimeMillis()-startTime;

                int seconds= (int)(elapsedTime/(1000))%60;
                int minutes=(int)((elapsedTime/(1000*60))%60);
                int hours=(int)((elapsedTime/(1000*60*60))%24);

                String time = String.format("%02d:%02d:%02d",hours,minutes,seconds);

                textView.setText(time);
                handler.postDelayed(this,1000);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       startButton=findViewById(R.id.startButton);
       stopButton=findViewById(R.id.stopButton);
       resetButton=findViewById(R.id.resetButton);
       textView=findViewById(R.id.textView);


       startButton.setOnClickListener(v->{
           if(!isRunning){

               startTime= System.currentTimeMillis();
               handler.post(runnable);
               isRunning=true;
           }
       });

       stopButton.setOnClickListener(v->{
           if(isRunning){
               handler.removeCallbacks(runnable);
               isRunning=false;
           }
       });

       resetButton.setOnClickListener(v->{

               handler.removeCallbacks(runnable);
               isRunning=false;
               textView.setText("00:00:00");

       });
    }
}