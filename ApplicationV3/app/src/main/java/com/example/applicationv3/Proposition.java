package com.example.applicationv3;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;

public class Proposition extends AppCompatActivity {
    private  EditText nomRecettePro;
    private  EditText Desc;
    private  Bitmap photo;
    private  EditText Ingr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);

        FloatingActionButton addBDD=findViewById(R.id.addB);
        addBDD.setVisibility(View.GONE);

        TextView text=findViewById(R.id.Type);
        text.setText(getIntent().getExtras().getString("type"));

        FirebaseUser usr= (FirebaseUser) getIntent().getExtras().get("User");

        nomRecettePro=findViewById(R.id.NomRecettePropo);
        Desc=findViewById(R.id.DescriptionPropo);
        Ingr=findViewById(R.id.IngrRecettePropo);

        ImageView img=findViewById(R.id.photo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//si il veut prendre une photo verifier que la permission est accordé et ouvre l'appareil photo ensuite
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
        if(usr!=null){//si l'utilisateur est celui de ce UID alors afficher le boutons d'add dans la DBB (admins seulement)
            Log.i("usr",usr.getUid());
            if(usr.getUid().contentEquals("EhyhGe5VcXPlnoOFTIgXtwehLw33")){
                addBDD.setVisibility(View.VISIBLE);
            }
        }else{
            Log.i("usr","null");
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//si jamais l utilisateur veut prendre une photo la prend et l affiche en tant que recette de photo
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            ImageView img=findViewById(R.id.photo);
            photo = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(photo);
        }
    }
    private void sendProp(){//envoi la proposition par mail
        String Email="chene.theophile@gmail.com";
        String Subject="Proposition recette";
        String mess=nomRecettePro.getText().toString()+"\n"+Ingr.getText().toString()+"\n"+Desc.getText().toString();
        JavaMailAPI javaMailAPI=new JavaMailAPI(this,Email,Subject, mess,photo);
        javaMailAPI.execute();
    }
}