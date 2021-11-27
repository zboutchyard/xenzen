package net.androidbootcamp.xenzen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Settings extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener myNavigationItemListener;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FloatingActionButton add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(myNavigationItemListener);
        bottomNavigationView.setSelectedItemId(R.id.miSettings);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        add = findViewById(R.id.fab);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, addEntry.class);
                startActivity(intent);
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.miHome:
                        Intent a = new Intent(Settings.this, MainMenu.class);
                        startActivity(a);
                        break;
                    case R.id.miJournal:
                        Intent b = new Intent(Settings.this, Journal.class);
                        startActivity(b);
                        break;
                    case R.id.miProfile:
                        Intent c = new Intent(Settings.this, Profile.class);
                        startActivity(c);
                        break;
                    case R.id.miSettings:
                        Intent d = new Intent(Settings.this, Settings.class);
                        startActivity(d);
                        break;
                    case R.id.fab:
                        Intent e = new Intent(Settings.this, addEntry.class);
                        startActivity(e);
                        break;

                }
                return false;
            }
        });
    }
}