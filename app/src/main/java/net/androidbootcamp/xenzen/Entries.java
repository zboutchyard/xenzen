package net.androidbootcamp.xenzen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class Entries extends AppCompatActivity {

    TextView titleEntry;
    TextView bodyEntry;

    DatabaseReference reference;

    public Entries(String title, String body) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);

        titleEntry = findViewById(R.id.titleEntry);
        bodyEntry = findViewById(R.id.bodyEntry);
        String entry = getIntent().getStringExtra("title");
        String body = getIntent().getStringExtra("body");
        titleEntry.setText(entry);
        bodyEntry.setText(body);



    }
}