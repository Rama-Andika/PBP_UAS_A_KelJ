package com.example.tubes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tubes.API.ApiClient;
import com.example.tubes.API.ApiInterface;
import com.example.tubes.API.RoomResponse;
import com.example.tubes.API.UserResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    private int REQUEST_IMAGE_CAPTURE = 100;
    private int RESULT_OK = -1;
    private int PERMISSION_CODE = 1001;
    private int IMAGE_PICK_CODE = 1001;

    protected Cursor cursor;
    DatePickerDialog.OnDateSetListener setListener;
    private static final String TAG = "BookingActivity";
    private AutoCompleteTextView exposedDropdownRoom;
    private TextInputLayout layananLayout;
    private TextInputLayout priceLayout;
    private TextInputEditText layanan;
    private TextInputEditText price;
    MaterialButton btn_create, btnUnggah;
    private String sRoom = "";
    private String[] saRoom = new String[] {"Classic", "Standart", "Deluxe"};
    private ProgressDialog progressDialog;
    private ImageView ivGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_room);
        progressDialog = new ProgressDialog(this);

        exposedDropdownRoom = findViewById(R.id.edKamar);
        
        ivGambar = findViewById(R.id.ivGambar);

        layananLayout = (TextInputLayout) findViewById(R.id.layananLayout);
        priceLayout = (TextInputLayout) findViewById(R.id.priceLayout);

        layanan = (TextInputEditText) findViewById(R.id.layanan);
        price = (TextInputEditText) findViewById(R.id.price);
        btn_create = findViewById(R.id.btn_create);
        btnUnggah = findViewById(R.id.btnUnggah);

        ArrayAdapter<String> adapterRoom = new ArrayAdapter<>(Objects.requireNonNull(this),
                R.layout.list_item, R.id.item_list, saRoom );
        exposedDropdownRoom.setAdapter(adapterRoom);
        exposedDropdownRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                sRoom = saRoom[i];
            }
        });
        
        btnUnggah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture();
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRoom();
            }
        });

    }

    private void selectPicture() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(RoomActivity.this);
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
                        if(ActivityCompat.checkSelfPermission(RoomActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
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
        if (takePictureIntent.resolveActivity(RoomActivity.this.getPackageManager()) != null) {
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
            Toast.makeText(RoomActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ivGambar.setImageBitmap(imageBitmap);
            uploadHandle(imageBitmap);
        }
        else if(requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK){
            Uri uri = data.getData();

            final ProgressDialog progressDialog = new ProgressDialog(RoomActivity.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            ivGambar.setImageURI(uri);

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
                            Toast.makeText(RoomActivity.this, e.getCause().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void uploadHandle(Bitmap bitmap) {
        final ProgressDialog progressDialog = new ProgressDialog(RoomActivity.this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final StorageReference reference = FirebaseStorage.getInstance().getReference()
                .child("profile_images")
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
                        Toast.makeText(RoomActivity.this, e.getCause().toString(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RoomActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RoomActivity.this, "Profile image failed...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void createRoom(){
        Log.d(TAG, "Booking");

        if (!validate()) {
            onBookingFailed();
            return;
        }
        progressDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RoomResponse> add = apiService.createRoom(sRoom, price.getText().toString(),
                layanan.getText().toString());

        add.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                Toast.makeText(RoomActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {
                Toast.makeText(RoomActivity.this, "Kesalahan jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        onBookingSuccess();
    }

    public void onBookingSuccess() {
        Toast.makeText(getBaseContext(), "Create Room success", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onBookingFailed() {
        Toast.makeText(getBaseContext(), "Create Room failed", Toast.LENGTH_LONG).show();

        btn_create.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;

        String layanan = this.layanan.getText().toString();
        String price = this.price.getText().toString();

        if (layanan.isEmpty() || layanan.length() < 3) {
            this.layanan.setError("At Least 3 Characters");
            valid = false;
        } else {
            this.layanan.setError(null);
        }

        if (price.isEmpty()){
            this.price.setError("Enter Valid price");
            valid = false;
        } else {
            this.price.setError(null);
        }


        return valid;
    }


}