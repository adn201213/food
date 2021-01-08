package com.adnan.lesson7fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NewFoodActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText editTextfoodName, editTextfooddescription, editTextPrice;
    NumberPicker numberPicker;
    ImageView imageView;
    Uri imgUrl;
    String foodName;
    Food foodModel;
    private final String PROFILE_IMAGES = "images1";

    private StorageReference storageReference;
    StorageReference riversRef;
    private StorageTask uploadTask;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    Spinner foodSpiner;
    private static final String TAG = "NewFoodActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        // EditText editTextfoodName,editTextfooddescription;
        editTextfoodName = findViewById(R.id.newfoodactivity_et_foodname);
        editTextfooddescription = findViewById(R.id.newfoodactivity_et_fooddescription);
        editTextPrice = findViewById(R.id.newfoodactivity_et_foodPrice);
        imageView = findViewById(R.id.activity_new_food_im_foodImage);
        //  storageReference= FirebaseStorage.getInstance().getReference("uploads");


        storageReference = FirebaseStorage.getInstance().getReference();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        foodSpiner=findViewById(R.id.newfoodactivity_spiner);
        ArrayAdapter<String> foodSpinerAdapter=new ArrayAdapter<String>(NewFoodActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.food_names));
        foodSpinerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        foodSpiner.setAdapter(foodSpinerAdapter);
        //   numberPicker.setMinValue(1);
        //   numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Food Item");
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imgUrl = data.getData();
            Log.i(TAG, "onActivityResult: " + imgUrl.toString());
            Picasso.get().load(imgUrl).into(imageView);
            // imageView.setImageURI(imgUrl);
            Log.i(TAG, "onActivityResult: " + imgUrl);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_food_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_food:
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(this, "the image is uploding please wait", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                    Log.i(TAG, "onOptionsItemSelected: " + imgUrl);
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    public void uploadFile() {
        foodName = editTextfoodName.getText().toString();
        final String foodDescription = editTextfooddescription.getText().toString();
        final double foodPrice=Double.parseDouble(editTextPrice.getText().toString());
        final String foodType=foodSpiner.getSelectedItem().toString();
        if (foodName.trim().isEmpty() || foodDescription.trim().isEmpty() || editTextPrice.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill data", Toast.LENGTH_SHORT).show();
            return;
        }


        if (imgUrl != null) {
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
             riversRef = storageReference.child("images1/").child(foodName + "." + getFileExtension(imgUrl));
            uploadTask = riversRef.putFile(imgUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //  Get a URL to the uploaded content
                            Toast.makeText(NewFoodActivity.this, "Upload up;load upload Success", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onSuccess: photo  add file");
                            Log.i(TAG, "onSuccess: photo " + imgUrl);
                            Food foodModel3 = new Food(foodName, imgUrl.toString(),foodPrice, foodDescription,foodType);

                            Log.i(TAG, "onSuccess innside upload: " + foodModel3.getFoodName() + foodModel3.getFoodImage() + foodModel3.getFoodDescription());
                            //   foodModel.setFoodImage(imgUrl.toString());
                            saveFoodToCloud(foodModel3);


                            // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            progressDialog.dismiss();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //  progressDialog.dismiss();
                            Toast.makeText(NewFoodActivity.this, "failed to add dnan " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onFailure:failed to add wensday  ");
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
            ;
        }
    }

    private void addFood(Food foodModel1) {


        Map<String, Object> foodMap = new HashMap<>();
        foodMap.put("foodName", foodModel1.getFoodName());
        foodMap.put("foodDescription", foodModel1.getFoodDescription());
        foodMap.put("price", foodModel1.getPrice());
        foodMap.put("foodImage", foodModel1.getFoodImage());
        foodMap.put("foodType", foodModel1.getFoodType());
        // food.put("uri", s);

// Add a new document with a generated ID
        firebaseFirestore.collection("foodMenu").document(foodModel1.getFoodName())
                .set(foodMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "onSuccess: Add Successfully");
                Toast.makeText(NewFoodActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onFailure: faild add food" + e.getMessage());
            }
        });


    }

    public void saveFoodToCloud(final Food foodModel2) {

        riversRef.getDownloadUrl()

      //  storageReference.child(PROFILE_IMAGES + "/" + foodName+ ".jpg").getDownloadUrl()
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            // String imageProfileUrl = task.getResult().toString();
                            imgUrl = task.getResult();
                            Log.i(TAG, "onComplete: saveFoodToCloud  failed");
                            Log.i(TAG, "onComplete: saveFoodToCloud" + imgUrl.toString());
                            Log.i(TAG, " saveFoodToCloud foodModel2: " + foodModel2.getFoodImage() + "   " + foodModel2.getFoodName());
                    foodModel2.setFoodImage(imgUrl.toString());
                            addFood(foodModel2);

                        } else {
                            Toast.makeText(NewFoodActivity.this, "failed to  save to cloud hhhhhh", Toast.LENGTH_LONG).show();
                            Log.i(TAG, "onComplete failed to  save to cloud : failed to  save to cloud");
                            Toast.makeText(NewFoodActivity.this, PROFILE_IMAGES + "\\" + foodModel2.getFoodImage() + ".jpg", Toast.LENGTH_LONG).show();
                            Log.i(TAG, "onComplete after without jpg:: "+PROFILE_IMAGES + "\\" + foodModel2.getFoodImage() + ".jpg");
                            Log.i(TAG, "onComplete after without jpg: " + storageReference.child("images").child(String.valueOf(imgUrl)));

                        }
                    }
                });
    }

}
