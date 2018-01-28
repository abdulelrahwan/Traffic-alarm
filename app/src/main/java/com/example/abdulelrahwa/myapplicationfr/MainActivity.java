package com.example.abdulelrahwa.myapplicationfr;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //int valueint = 0;

    private TextView tvData;
    EditText home;
    EditText destination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer music21 = MediaPlayer.create(this, R.raw.music);

        Button btnHit = (Button) findViewById(R.id.btnHit);

        tvData = (TextView) findViewById(R.id.tvJsonItem);
        final MainActivity mainclass = this;

        btnHit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View w) {
                // player = MediaPlayer.create(getApplicationContext(), R.raw.music);


                // music21.start();


                EditText home = (EditText) findViewById(R.id.homebox);
                EditText destination = (EditText) findViewById(R.id.editText2);

                String origins = home.getText().toString();
                origins = origins.replace(" ", "%20");
                String destinationvar = destination.getText().toString();
                destinationvar = destinationvar.replace(" ", "%20");


                String URLOriginal = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&key=AIzaSyC7ZCMYbXM7VEDeJFXsy57MEB7buQxOhso&origins=" + origins + "&destinations=" + destinationvar;

                JSONTask task = new JSONTask(mainclass);

                task.execute(URLOriginal);


            }
        });
    }

    public void setAlarm(int time) {
        final MediaPlayer music21 = MediaPlayer.create(this, R.raw.music);

        CountDownTimer timer = new CountDownTimer(time * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub

                music21.start();
            }
        };
        timer.start();

        EditText currentTime = (EditText) findViewById(R.id.editText6);
        EditText readyTime = (EditText) findViewById(R.id.editText3);
        EditText arrivalTime = (EditText) findViewById(R.id.editText4);


        int rTime = Integer.parseInt(readyTime.getText().toString());
        int aTime = Integer.parseInt(arrivalTime.getText().toString());

        Intent i = new Intent(MainActivity.this, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        //final MediaPlayer music21 = MediaPlayer.create(this, R.raw.music);

        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + aTime * 1000, pi);

    }

}












