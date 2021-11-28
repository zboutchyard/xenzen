package net.androidbootcamp.xenzen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


    public class Profile extends AppCompatActivity {

        BottomNavigationView bottomNavigationView;
        private BottomNavigationView.OnNavigationItemSelectedListener myNavigationItemListener;
        FirebaseAuth fAuth;
        FirebaseFirestore fStore;
        FloatingActionButton add;
        private FirebaseUser user;
        private DatabaseReference reference;
        private String userID;
        private Button logout;

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
            logout = findViewById(R.id.btnLogout);

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(Profile.this, MainActivity.class));
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
                            Intent e = new Intent(getApplicationContext(), AddEntry.class);
                            startActivity(e);
                            break;

                    }
                    return false;
                }
            });




            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddEntry.class);
                    startActivity(intent);
                }
            });


            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("users");
            userID = user.getUid();
            final TextView userNameText = findViewById(R.id.txtUsername);
            final TextView emailText = findViewById(R.id.txtEmail);
            final TextView phoneText = findViewById(R.id.txtPhone);

            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    if(userProfile != null){
                        String userName = userProfile.username;
                        String email = userProfile.email;
                        String phone = userProfile.phone;
                        userNameText.setText(userName);
                        emailText.setText(email);
                        phoneText.setText(phone);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Profile.this, "Something Wrong Happened!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

