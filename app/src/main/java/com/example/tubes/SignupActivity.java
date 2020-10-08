package com.example.tubes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    TextInputEditText input_username;
    TextInputEditText input_name;
    TextInputEditText email;
    TextInputEditText input_number;
    TextInputEditText password;
    MaterialButton btn_signup;
    TextView link_login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mFirebaseAuth = FirebaseAuth.getInstance();
        input_name = (TextInputEditText) findViewById(R.id.input_name);
        email = (TextInputEditText) findViewById(R.id.input_emailR);
        input_number = (TextInputEditText) findViewById(R.id.input_number);
        input_username = (TextInputEditText) findViewById(R.id.input_username);
        password = (TextInputEditText) findViewById(R.id.input_passwordR);
        btn_signup = (MaterialButton) findViewById(R.id.btn_signup);
        link_login = (TextView) findViewById(R.id.link_login);




        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }






        String name = input_name.getText().toString();
        String mobile = input_number.getText().toString();
        String user = input_username.getText().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()) {
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    Toast.makeText(getApplicationContext(), "SignUp Unccessfull, Please Try Again"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    btn_signup.setEnabled(true);
                }
                else{
                    final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Creating Account...");
                    progressDialog.show();

                    new Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    // On complete call either onSignupSuccess or onSignupFailed
                                    // depending on success
                                    onSignupSuccess();
                                    // onSignupFailed();
                                    progressDialog.dismiss();
                                }
                            }, 3000);
                }

            }
        });



    }



    public void onSignupSuccess() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_signup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = input_name.getText().toString();
        String email = this.email.getText().toString();
        String mobile = input_number.getText().toString();
        String user = input_username.getText().toString();
        String password = this.password.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            input_name.setError("at least 3 characters");
            valid = false;
        } else {
            input_name.setError(null);
        }

        if (user.isEmpty()) {
            input_username.setError("Enter Valid Username");
            valid = false;
        } else {
            input_username.setError(null);
        }


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.email.setError("enter a valid email address");
            valid = false;
        } else {
            this.email.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            input_number.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            input_number.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 15) {
            this.password.setError("between 6 and 10 alphanumeric characters");
            valid = false;
        } else {
            this.password.setError(null);
        }


        return valid;
    }
}