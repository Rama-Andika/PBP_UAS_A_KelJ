package com.example.tubes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tubes.model.UserHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    View view;
    private TextInputLayout layout_name, layout_username, layout_email,layout_number;
    private TextInputEditText input_name, input_username, input_email, input_number;

    String name, email, username, number;

    private static final String TAG = "ProfileActivity";

    CircleImageView profileImageView;
    MaterialButton updateProfileButton;
    MaterialButton btn_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );


        layout_name = findViewById(R.id.name);
        layout_email = findViewById(R.id.email);
        layout_username = findViewById(R.id.username);
        layout_number = findViewById(R.id.number);

        input_name = findViewById(R.id.input_name);
        input_email = findViewById(R.id.input_emailR);
        input_username = findViewById(R.id.input_username);
        input_number = findViewById(R.id.input_number);

        profileImageView = findViewById(R.id.profileImage);
        updateProfileButton = findViewById(R.id.btn_update);
        btn_back = findViewById(R.id.btn_back);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelper userHelper = snapshot.getValue(UserHelper.class);
                assert userHelper != null;
                input_name.setText(userHelper.getName());
                input_username.setText(userHelper.getUsername());
                input_number.setText(userHelper.getNumber());
                input_email.setText(userHelper.getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 10001);
        }

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,10001);
            }
        });


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            profileImageView.setImageBitmap(bitmap);
        }
    }


}



















