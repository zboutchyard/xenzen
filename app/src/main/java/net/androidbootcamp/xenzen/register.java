package net.androidbootcamp.xenzen;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity implements  View.OnClickListener{

    net.androidbootcamp.xenzen.databinding.ActivityRegisterBinding binding;
    FirebaseAuth mAuth;
    ProgressDialog dialog;
    String userID;
    EditText eMail;
    EditText userName;
    EditText pWord;
    EditText confirm;
    EditText phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        confirm = findViewById(R.id.editTextConfirmPassword);
         pWord = findViewById(R.id.editTextPassword);
         userName = findViewById(R.id.editTextUsername);
         eMail = findViewById(R.id.editTextEmailAddress);
         phoneNumber = findViewById(R.id.editTextPhone);
        Button registerButton = findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerBtn:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = eMail.getText().toString().trim();
        String confirmPassword = confirm.toString().trim();
        String username = userName.getText().toString().trim();
        String phone = phoneNumber.getText().toString().trim();
        String password = pWord.getText().toString().trim();

        if(username.isEmpty()){
            userName.setError("Username Field is Empty!");
            userName.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            phoneNumber.setError("Phone Number Field is Empty!");
            phoneNumber.requestFocus();
            return;
        }
        if(email.isEmpty()){
            eMail.setError("Email Field is Empty!");
            eMail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            eMail.setError("Please provide valid email!");
            eMail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            pWord.setError("Password Field is Empty!");
            pWord.requestFocus();
            return;
        }
        if(confirmPassword.isEmpty())
        {
            confirm.setError("You must confirm your password!");
            pWord.requestFocus();
            return;
        }
        if(password.length() < 6){
            pWord.setError("Password must be at least 6 characters!");
            pWord.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(phone, username, email );
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(register.this, MainActivity.class);
                                        startActivity(intent);

                                    }else{
                                        Toast.makeText(register.this, "Failed to register! try again!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(register.this, "Failed to register! try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}