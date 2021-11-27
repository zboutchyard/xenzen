package net.androidbootcamp.xenzen;

import static android.content.ContentValues.TAG;
import static com.google.firebase.database.FirebaseDatabase.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addEntry extends AppCompatActivity {
    net.androidbootcamp.xenzen.databinding.ActivityRegisterBinding binding;
    FirebaseFirestore fStore;
    FloatingActionButton save;
    EditText txtTitle;
    EditText txtBody;
    String entryID;


    DatabaseReference entryDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

 /*       save = findViewById(R.id.fabSave);
        txtTitle = findViewById(R.id.textTitle);
        txtBody = findViewById(R.id.textBody);
        fStore = FirebaseFirestore.getInstance();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitle.getText().toString();
                String body = txtBody.getText().toString();

                storeDataToFirebase(title, body);
            }
        });
    }

    private void storeDataToFirebase(String title, String body) {


                    DocumentReference documentReference = fStore.collection("entries").document(entryID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("title", title);
                    user.put("body", body);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: user Profile is created for " + entryID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                        }
                    });


                    startActivity(new Intent(getApplicationContext(), Journal.class));


                }
*/

    }
}



