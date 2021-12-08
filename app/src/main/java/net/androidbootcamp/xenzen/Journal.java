package net.androidbootcamp.xenzen;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class Journal extends AppCompatActivity implements FirestoreAdapter.OnListItemClick {

    BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener myNavigationItemListener;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private RecyclerView titles;
    private FirestoreAdapter adapter;
    FloatingActionButton add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(myNavigationItemListener);
        bottomNavigationView.setSelectedItemId(R.id.miJournal);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        titles = findViewById(R.id.titleList);

        add = findViewById(R.id.fab);






        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.miHome:
                        Intent a = new Intent(Journal.this, MainMenu.class);
                        startActivity(a);
                        break;
                    case R.id.miJournal:
                        Intent b = new Intent(Journal.this, Journal.class);
                        startActivity(b);
                        break;
                    case R.id.miProfile:
                        Intent c = new Intent(Journal.this, Profile.class);
                        startActivity(c);
                        break;
                    case R.id.miSettings:
                        Intent d = new Intent(Journal.this, Settings.class);
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

        //Query
        Query query = fStore.collection("entries");



        //RecyclerOptions
        FirestoreRecyclerOptions<JournalModel> options = new FirestoreRecyclerOptions.Builder<JournalModel>()
                .setLifecycleOwner(this)
                .setQuery(query, JournalModel.class)
                .build();

        RecyclerView recyclerView = findViewById(R.id.titleList);

        adapter = new FirestoreAdapter(options, this);


        titles.setHasFixedSize(true);
        titles.setLayoutManager(new LinearLayoutManager(this));
        titles.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

    }




    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();

    }

    @Override
    public void onItemClick(JournalModel snapshot, int position) {
        Log.d("ITEM_CLICK", "CLICKED AN ITEM: " + position + " and the ID :" + snapshot.getTitle());
        snapshot.getBody();
        Intent intent = new Intent(getApplicationContext(), Entries.class);
        intent.putExtra("title", snapshot.getTitle());
        intent.putExtra("body", snapshot.getBody());
        startActivity(intent);


    }
}
