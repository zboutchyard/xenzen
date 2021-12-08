package net.androidbootcamp.xenzen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class resetPassword extends AppCompatActivity implements View.OnClickListener {

    Button submit;
    EditText pass;
    EditText confirm;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        submit = findViewById(R.id.buttonResetPassword);
        pass = findViewById(R.id.editPassword);
        confirm = findViewById(R.id.editConfirm);
        auth = FirebaseAuth.getInstance();
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonResetPassword:
                change();
                break;
        }
    }



    public void change(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
                user.updatePassword(pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Password Change Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            }
        }
    }


