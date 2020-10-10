package com.example.tubes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.tubes.Model.UserHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private String CHANNEL_ID = "Channel 1";
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    TextInputLayout name, user, email, number, password;

    TextInputEditText input_username;
    TextInputEditText input_name;
    TextInputEditText input_email;
    TextInputEditText input_number;
    TextInputEditText input_password;
    MaterialButton btn_signup;
    TextView link_login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_signup);
        mFirebaseAuth = FirebaseAuth.getInstance();
        name = (TextInputLayout) findViewById(R.id.name);
        email = (TextInputLayout) findViewById(R.id.email);
        number = (TextInputLayout) findViewById(R.id.number);
        user = (TextInputLayout) findViewById(R.id.username);
        password = (TextInputLayout) findViewById(R.id.password);

        input_name = (TextInputEditText) findViewById(R.id.input_name);
        input_email = (TextInputEditText) findViewById(R.id.input_emailR);
        input_number = (TextInputEditText) findViewById(R.id.input_number);
        input_username = (TextInputEditText) findViewById(R.id.input_username);
        input_password = (TextInputEditText) findViewById(R.id.input_passwordR);
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



        String name_layout = name.getEditText().getText().toString();
        String user_layout = user.getEditText().getText().toString();
        String email_layout = email.getEditText().getText().toString();
        String number_layout = number.getEditText().getText().toString();
        String password_layout = password.getEditText().getText().toString();







        String name = input_name.getText().toString();
        String mobile = input_number.getText().toString();
        String user = input_username.getText().toString();
        String email = this.input_email.getText().toString();
        String password = this.input_password.getText().toString();

        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()) {
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    Toast.makeText(getApplicationContext(), "SignUp Unccessfull, Please Try Again"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    btn_signup.setEnabled(true);
                }
                else{
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("users");
                    UserHelper helper = new UserHelper(name_layout, user_layout, email_layout, number_layout);

                    reference.child(user_layout).setValue(helper);
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
                    createNotificationChannel();
                    addNotification();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });



    }



    public void onSignupSuccess() {

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_signup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = input_name.getText().toString();
        String email = this.input_email.getText().toString();
        String mobile = input_number.getText().toString();
        String user = input_username.getText().toString();
        String password = this.input_password.getText().toString();


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
            this.input_email.setError("enter a valid email address");
            valid = false;
        } else {
            this.input_email.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            input_number.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            input_number.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 15) {
            this.input_password.setError("between 6 and 10 alphanumeric characters");
            valid = false;
        } else {
            this.input_password.setError(null);
        }


        return valid;
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void addNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.notif)
                .setContentTitle("Selamat!")
                .setContentText("Register Berhasil")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this,SignupActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}