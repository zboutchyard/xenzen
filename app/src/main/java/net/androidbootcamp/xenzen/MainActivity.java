package net.androidbootcamp.xenzen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, register.class));
            }


        });

        Button button = findViewById(R.id.registerButton);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intentRegister = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intentRegister);
            }
        });



    }
}