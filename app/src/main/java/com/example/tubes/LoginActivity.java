package com.example.tubes;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private SharedPreferences preferences;
    public static final int mode = Activity.MODE_PRIVATE;
    private String email ="";
    private String password ="";

    FirebaseAuth mFirebaseAuth;
    private String CHANNEL_ID = "Channel 1";
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    TextInputLayout email_layout, pass_layout;
    private TextInputEditText input_email;
    private TextInputEditText input_password;
    private MaterialButton btn_login;
    private TextView link_signup;
    MaterialCheckBox rememberMe;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_login);
        loadPreferences();
        mFirebaseAuth = FirebaseAuth.getInstance();
        email_layout = findViewById(R.id.email);
        pass_layout = findViewById(R.id.password);
        input_email = (TextInputEditText) findViewById(R.id.input_emailR);
        input_password = (TextInputEditText) findViewById(R.id.input_passwordR);
        input_email.setText(email);
        input_password.setText(password);
        rememberMe = findViewById(R.id.rememberMe);
        btn_login = (MaterialButton) findViewById(R.id.btn_login);
        link_signup = (TextView) findViewById(R.id.link_signup);



        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        link_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }



    private void loadPreferences(){
        String name = "profilr";
        preferences = getSharedPreferences(name, mode);
        if(preferences!=null)
        {
            email = preferences.getString("email", "");
            password = preferences.getString("password", "");
        }
    }

    private void savePreferences(){
        SharedPreferences.Editor editor = preferences.edit();
        if(!input_email.getText().toString().isEmpty() && !input_password.getText().toString().isEmpty())
        {
            editor.putString("email", input_email.getText().toString());
            editor.putString("password", input_password.getText().toString());
            editor.apply();
        }
        else
        {
            Toast.makeText(this, "Fill correctly", Toast.LENGTH_SHORT).show();
        }
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        btn_login.setEnabled(false);



        String input1 = input_email.getText().toString();
        String input2 = input_password.getText().toString();

        if (rememberMe.isChecked()){
            savePreferences();
        }


        mFirebaseAuth.signInWithEmailAndPassword(input1,input2).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "SignIn Unccessfull, Please Try Again", Toast.LENGTH_SHORT).show();
                    btn_login.setEnabled(true);
                }else{
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    // On complete call either onLoginSuccess or onLoginFailed
                                    onLoginSuccess();
                                    // onLoginFailed();
                                    progressDialog.dismiss();
                                }
                            }, 2000);

                    createNotificationChannel();
                    addNotification();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            }
        });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        btn_login.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_login.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("enter a valid email address");
            valid = false;
        } else if (password.isEmpty() || password.length() < 6 || password.length() > 15) {
            input_password.setError("between 6 and 15 alphanumeric characters");
            valid = false;
        } else {
            input_password.setError(null);
            input_email.setError(null);

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
                .setContentTitle("Halo")
                .setContentText("Selamat Datang di The Queen Hotel")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this,LoginActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }



}
