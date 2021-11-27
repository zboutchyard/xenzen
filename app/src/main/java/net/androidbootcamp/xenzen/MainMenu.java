package net.androidbootcamp.xenzen;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainMenu extends AppCompatActivity {


    Button happyPlay;
    Button sadPlay;
    Button angryPlay;
    Button contentPlay;
    MediaPlayer sad;
    MediaPlayer Angry;
    MediaPlayer Content;
    int value;
    BottomNavigationView bottomNavigationView;
    CoordinatorLayout main_container;
    Fragment active;
    FirebaseFirestore fStore;
    FloatingActionButton add;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        add = findViewById(R.id.fab);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, addEntry.class);
                startActivity(intent);
            }
        });


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId()){
                    case R.id.miHome:
                        Intent a = new Intent(MainMenu.this, MainMenu.class);
                        startActivity(a);
                        break;
                    case R.id.miJournal:
                        Intent b = new Intent(MainMenu.this, Journal.class);
                        startActivity(b);
                        break;
                    case R.id.miSettings:
                        Intent d = new Intent(MainMenu.this, Settings.class);
                        startActivity(d);
                        break;
                    case R.id.fab:
                        Intent e = new Intent(MainMenu.this, addEntry.class);
                        startActivity(e);
                        break;

                }
                return false;
            }
        });




                bottomNavigationView.setBackground(null);




        happyPlay = findViewById(R.id.happyButton);
        final MediaPlayer happyMedia = MediaPlayer.create(this, R.raw.happy);
        happyPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (happyMedia.isPlaying()) {
                    happyMedia.pause();
                    happyPlay.setBackgroundResource(R.drawable.gradientmood);
                } else {
                    happyMedia.start();
                    happyPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_outline_24);
                }


            }
        });


        sadPlay = findViewById(R.id.sadButton);
        final MediaPlayer sadMedia = MediaPlayer.create(this, R.raw.sad);
        sadPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                value = 2;
                if (sadMedia.isPlaying()) {
                    sadMedia.pause();
                    sadPlay.setBackgroundResource(R.drawable.gradientmood);
                } else {
                    sadMedia.start();
                    sadPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_outline_24);
                }

            }

        });

        angryPlay = findViewById(R.id.angryButton);
        {
            final MediaPlayer angryMedia = MediaPlayer.create(this, R.raw.angry);
            angryPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    value = 3;
                    if (angryMedia.isPlaying()) {
                        angryMedia.pause();
                        angryPlay.setBackgroundResource(R.drawable.gradientmood);
                    } else {
                        angryMedia.start();
                        angryPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_outline_24);
                    }
                }
            });


            contentPlay = findViewById(R.id.contentButton);
            {
                final MediaPlayer contentMedia = MediaPlayer.create(this, R.raw.content);
                contentPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        value = 4;
                        if (contentMedia.isPlaying()) {
                            contentMedia.pause();
                            contentPlay.setBackgroundResource(R.drawable.gradientmood);
                        } else {
                            contentMedia.start();
                            contentPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_outline_24);
                        }
                    }
                });




            }


        }










        }
    }
