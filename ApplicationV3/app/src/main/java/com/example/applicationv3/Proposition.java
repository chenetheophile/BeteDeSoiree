package com.example.applicationv3;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Proposition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);

        TextView text=findViewById(R.id.Type);
        text.setText(getIntent().getExtras().getString("type"));

        EditText nomRecettePro=findViewById(R.id.NomRecettePropo);

        ImageView img=findViewById(R.id.photo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Proposition.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions( Proposition.this,new String[]{Manifest.permission.CAMERA}, 0);
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                int REQUEST_ID_IMAGE_CAPTURE = 100;
                startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);

            }

        });

        Button envoi=findViewById(R.id.envoyer);
        envoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendProp();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            ImageView img=findViewById(R.id.photo);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(photo);
        }
    }
    private void sendProp(){
        String Email="chene.theophile@gmail.com";
        String Subject="slt";
        String mess="lol";
        JavaMailAPI javaMailAPI=new JavaMailAPI(this,Email,Subject, mess);
        javaMailAPI.execute();
    }
}