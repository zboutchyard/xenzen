package net.androidbootcamp.xenzen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


    public class Profile extends AppCompatActivity {

        BottomNavigationView bottomNavigationView;
        private BottomNavigationView.OnNavigationItemSelectedListener myNavigationItemListener;
        FirebaseAuth fAuth;
        FirebaseFirestore fStore;
        FloatingActionButton add;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);

            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            BottomNavigationView bottomNavigationView;
            bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setOnNavigationItemSelectedListener(myNavigationItemListener);
            bottomNavigationView.setSelectedItemId(R.id.miProfile);
            fAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();

            add = findViewById(R.id.fab);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Profile.this, addEntry.class);
                    startActivity(intent);
                }
            });


            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.miHome:
                            Intent a = new Intent(Profile.this, MainMenu.class);
                            startActivity(a);
                            break;
                        case R.id.miJournal:
                            Intent b = new Intent(Profile.this, Journal.class);
                            startActivity(b);
                            break;
                        case R.id.miProfile:
                            Intent c = new Intent(Profile.this, Profile.class);
                            startActivity(c);
                            break;
                        case R.id.miSettings:
                            Intent d = new Intent(Profile.this, Settings.class);
                            startActivity(d);
                            break;
                        case R.id.fab:
                            Intent e = new Intent(Profile.this, addEntry.class);
                            startActivity(e);
                            break;

                    }
                    return false;
                }
            });
        }
    }

