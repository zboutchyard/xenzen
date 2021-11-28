package net.androidbootcamp.xenzen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button loginButton;
    private TextView register;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.registerBtn);
        loginButton.setOnClickListener(this);
        register = findViewById(R.id.textView5);
        register.setOnClickListener(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView5:
                startActivity(new Intent(getApplicationContext(), register.class));
                break;
            case R.id.registerBtn:
                userLogin();
                break;
        }
    }

    private void userLogin() {

        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextUsername.setError("Email is required!");
            editTextUsername.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextUsername.setError("Please enter a valid email!");
            editTextUsername.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, MainMenu.class));
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed, Please check your credentials and try again! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
