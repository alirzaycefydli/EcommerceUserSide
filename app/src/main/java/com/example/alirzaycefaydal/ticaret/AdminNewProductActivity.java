package com.example.alirzaycefaydal.ticaret;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdminNewProductActivity extends AppCompatActivity {

    //widgets
    private ImageView image_product;
    private EditText edit_product_name, edit_product_description, edit_product_price;

    //vars
    private String categoryName;
    private static final int PICK_IMAGE_REQUEST = 11;
    private Uri resultUri;
    private StorageReference storageReference;
    private DatabaseReference productRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_product);

        categoryName = getIntent().getExtras().get("category").toString();

        storageReference = FirebaseStorage.getInstance().getReference();
        productRef = FirebaseDatabase.getInstance().getReference();

        image_product = findViewById(R.id.select_product_image);
        edit_product_name = findViewById(R.id.product_name);
        edit_product_description = findViewById(R.id.product_description);
        edit_product_price = findViewById(R.id.product_price);

        image_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }

    private void openGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(AdminNewProductActivity.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(AdminNewProductActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE_REQUEST);
            } else {
                pickImageFromGallery();
            }
        } else {
            pickImageFromGallery();
        }
    }


    public void addProductClicked(View view) {

        final String product_name = edit_product_name.getText().toString();
        final String product_description = edit_product_description.getText().toString();
        final String product_price = edit_product_price.getText().toString();

        if (!TextUtils.isEmpty(product_name) && !TextUtils.isEmpty(product_description) && !TextUtils.isEmpty(product_price)) {

            if (resultUri != null) {
                String post_name = UUID.randomUUID().toString();

                final StorageReference reference = storageReference.child(categoryName).child(post_name + ".jpg");

                reference.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String download_url = uri.toString();

                                    DateFormat format = DateFormat.getTimeInstance();
                                    String date = format.format(Calendar.getInstance().getTime());

                                    Map<String, String> postMap = new HashMap<>();
                                    postMap.put("image", download_url);
                                    postMap.put("date", date);
                                    postMap.put("product_name", product_name);
                                    postMap.put("product_description", product_description);
                                    postMap.put("product_price", product_price);

                                    productRef.child("Products").child(categoryName).push();
                                    productRef.setValue(postMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Intent i = new Intent(AdminNewProductActivity.this, HomeActivity.class);
                                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(i);
                                                finish();
                                            }
                                        }
                                    });


                                }
                            });
                        }
                    }
                });
            }

        }
    }

    private void pickImageFromGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an image!"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data.getData() != null) {
            resultUri = data.getData();
            image_product.setImageURI(resultUri);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PICK_IMAGE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                pickImageFromGallery();

            } else {
                Toast.makeText(AdminNewProductActivity.this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
