package net.androidbootcamp.xenzen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Add a timer task to close the splash screen
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //finish (close) the activity when the TimerTask fires
                finish();
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            }
        };
        //Create an instance of a timer that will start when the activity is created
        Timer opening = new Timer();
        //schedule the timer to fire the TimerTask after 5000 milliseconds (5 seconds_
        opening.schedule(task, 4000);
    }
}