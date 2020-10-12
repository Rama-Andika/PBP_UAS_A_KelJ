package com.example.tubes;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.tubes.model.UserHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;


public class ProfileActivity extends AppCompatActivity {

    private String CHANNEL_ID = "Channel 1";

    private int REQUEST_IMAGE_CAPTURE = 100;
    private int RESULT_OK = -1;
    private int PERMISSION_CODE = 1001;
    private int IMAGE_PICK_CODE = 1001;

    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    private TextInputLayout layout_name, layout_username, layout_email,layout_number;
    private TextInputEditText input_name, input_username, input_email, input_number;

    String name, email, username, number;

    private static final String TAG = "ProfileActivity";

    public ImageView image_view;

    MaterialButton btn_update;
    MaterialButton btn_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        user = FirebaseAuth.getInstance().getCurrentUser();
        layout_name = findViewById(R.id.name);
        layout_email = findViewById(R.id.email);
        layout_username = findViewById(R.id.username);
        layout_number = findViewById(R.id.number);

        input_name = findViewById(R.id.input_name);
        input_email = findViewById(R.id.input_emailR);
        input_username = findViewById(R.id.input_username);
        input_number = findViewById(R.id.input_number);

        image_view = findViewById(R.id.image_profile);
        btn_update = findViewById(R.id.btn_update);
        btn_back = findViewById(R.id.btn_back);
        RelativeLayout image_layout = findViewById(R.id.image_layout);



        if (user != null) {
            if(user.getPhotoUrl() != null){
                Glide.with(ProfileActivity.this)
                        .load(user.getPhotoUrl())
                        .into(image_view);
            }
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
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

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(ProfileActivity.this);
                alert.setMessage("Update Data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()                 {

                            public void onClick(DialogInterface dialog, int which) {

                                updateData();

                            }
                        }).setNegativeButton("No", null);

                AlertDialog alert1 = alert.create();
                alert1.show();
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
            }, 101);
        }

        image_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture();
            }
        });




    }

    private void selectPicture() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    takePictureIntent();
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                            String[] permissions = { Manifest.permission.READ_EXTERNAL_STORAGE };
                            requestPermissions(permissions, PERMISSION_CODE);
                        }
                        else{
                            pickImageFromGallery();
                        }
                    }
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    private void takePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(ProfileActivity.this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            pickImageFromGallery();
        }
        else{
            Toast.makeText(ProfileActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(){
        databaseReference.child("name").setValue(layout_name.getEditText().getText().toString());
        databaseReference.child("username").setValue(layout_username.getEditText().getText().toString());
        databaseReference.child("email").setValue(layout_email.getEditText().getText().toString());
        databaseReference.child("number").setValue(layout_number.getEditText().getText().toString());
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            image_view.setImageBitmap(imageBitmap);
            uploadHandle(imageBitmap);
        }
        else if(requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK){
            Uri uri = data.getData();

            final ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            image_view.setImageURI(uri);

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final StorageReference ref = FirebaseStorage.getInstance().getReference()
                    .child("profileImages")
                    .child(uid + ".jpeg");

            ref.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            getDownloadUrl(ref);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, e.getCause().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void uploadHandle(Bitmap bitmap) {

        final ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final StorageReference reference = FirebaseStorage.getInstance().getReference()
                .child("profileImages")
                .child(uid + ".jpeg");

        reference.putBytes(byteArrayOutputStream.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        getDownloadUrl(reference);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileActivity.this, e.getCause().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getDownloadUrl(StorageReference reference) {

        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        setUserProfileUrl(uri);
                    }
                });
    }
    private void setUserProfileUrl(Uri uri){
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfileActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileActivity.this, "Profile image failed...", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}



















