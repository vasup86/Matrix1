package com.matrix1.app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int pic_id = 1; //pic id

    Button button_camera;
    ImageView click_image_id;
    TextView picText;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_camera = (Button) findViewById(R.id.button_camera); //inside R.id is the id of the button camera.
        click_image_id = (ImageView) findViewById(R.id.click_image);
        picText = (TextView)findViewById(R.id.pic_text);

        //check app level permission is granted for camera
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ){
         //grant permission
         requestPermissions(new String[]{Manifest.permission.CAMERA}, pic_id);
        }

        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the camera => create an intent object
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //we need to continue after taking pic
                startActivityForResult(camera_intent, pic_id); //it works even if deprecated

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == pic_id){
            Bundle bundle = data.getExtras();
            Bitmap photo = (Bitmap) bundle.get("data"); //from bundle, extract image
            click_image_id.setImageBitmap(photo); //set the image in the imageview
        }

        //process the image to extract the


        super.onActivityResult(requestCode, resultCode, data);
    }
}