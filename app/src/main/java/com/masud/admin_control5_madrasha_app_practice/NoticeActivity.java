package com.masud.admin_control5_madrasha_app_practice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class NoticeActivity extends AppCompatActivity {
    private MaterialCardView galleyImagePicking;
    private ImageView imageGet;
    private AppCompatButton submitBtn;
    private TextInputLayout textInputLayout;
    private DatabaseReference database;

    Bitmap imageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        database=FirebaseDatabase.getInstance().getReference().child("Notice");


        galleyImagePicking = findViewById(R.id.imagePost);
        imageGet = findViewById(R.id.imageGet);
        submitBtn = findViewById(R.id.submitBtn);
        textInputLayout = findViewById(R.id.textInputLayout);
        galleyImagePicking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goImageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(goImageIntent, 1);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postTitle = textInputLayout.getEditText().getText().toString().trim();
                if (postTitle.isEmpty()) {
                    textInputLayout.getEditText().setError("something write");
                    textInputLayout.getEditText().requestFocus();
                } else if (imageBitmap == null) {
                    gotoDatabase ();
                }
            }
        });
    }

    private void gotoDatabase() {
        String uniqueId = database.push().getKey();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) ;
        {
            Uri imageUri = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            } catch (IOException e) {


            }
            imageGet.setImageURI(imageUri);

        }
    }
}