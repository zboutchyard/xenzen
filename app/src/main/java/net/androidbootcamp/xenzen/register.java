package net.androidbootcamp.xenzen;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    net.androidbootcamp.xenzen.databinding.ActivityRegisterBinding binding;
    FirebaseAuth fAuth;
    ProgressDialog dialog;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding = net.androidbootcamp.xenzen.databinding.ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        EditText confirmPassword = findViewById(R.id.editTextConfirmPassword);
        EditText password = findViewById(R.id.editTextPassword);
        EditText userName = findViewById(R.id.editTextUsername);
        EditText eMail = findViewById(R.id.editTextEmailAddress);
        EditText phoneNumber = findViewById(R.id.editTextPhone);
        Button registerButton = findViewById(R.id.registerButton);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userName.equals("")) {
                    Toast.makeText(register.this, "Username field vacant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if initial password is vacant
                else if(password.equals("")) {
                    Toast.makeText(register.this, "Password field vacant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                else if(!password.equals(confirmPassword)) {
                    Toast.makeText(register.this, "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }

                //CHECK PHONE NUMBER
                else if(phoneNumber.equals("")) {
                    Toast.makeText(register.this, "Phone Number field vacant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if email field is vacant
                else if(eMail.equals("")) {
                    Toast.makeText(register.this, "Email field vacant", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }));



        fAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(register.this);

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.editTextEmailAddress.getText().toString();
                String phone = binding.editTextPhone.getText().toString();
                String userName = binding.editTextUsername.getText().toString();
                String password = binding.editTextPassword.getText().toString();


                storeDataToFirebase(email, phone, userName, password);
            }
        });



    }



    private void storeDataToFirebase(String email, String phoneNumber, String userName, String password){




        dialog.setMessage("Please Wait...");

        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {

                    dialog.setMessage("Account Successfully Created");
                    Toast.makeText(register.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("email", email);
                    user.put("phoneNumber", phoneNumber);
                    user.put("userName", userName);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                        }
                    });


                    startActivity(new Intent(getApplicationContext(), MainMenu.class));


                }



            }
        });


    }

}