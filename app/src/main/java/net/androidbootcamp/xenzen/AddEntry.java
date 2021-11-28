package net.androidbootcamp.xenzen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddEntry extends AppCompatActivity {

    EditText txtTitle;
    EditText txtBody;
    FloatingActionButton save;
    private FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    private DatabaseReference reference = rootNode.getReference().child("entries");
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        txtTitle = findViewById(R.id.txtTitle);
        txtBody = findViewById(R.id.txtBody);
        save = findViewById(R.id.fabSave);
        db = FirebaseFirestore.getInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CollectionReference dbEntries = db.collection("entries");
                String title = txtTitle.getText().toString();
                String body = txtBody.getText().toString();
                JournalModel journalModel = new JournalModel(title, body);
                dbEntries.add(journalModel);


                Intent intent = new Intent(getApplicationContext(), Journal.class);
                startActivity(intent);
            }
        });


    }

}