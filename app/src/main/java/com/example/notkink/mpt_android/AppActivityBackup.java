package com.example.notkink.mpt_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

public class AppActivityBackup extends AppCompatActivity {


    public static final int CAMERA_REQUEST_CODE = 10;
    ImageView imageView;
    private Button addPictureButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ListView listView;
    private List<BillEntry> billEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        //Toolbar toolbar =  findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //addPictureButton = findViewById(R.id.addPicture);


        ImageView imageView = findViewById(R.id.imageView);

//        addPictureButton = findViewById(R.id.addPicture);
//        addPictureButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
        // TODO Auto-generated method stub
//                startActivity(new Intent(AppActivityBackup.this, AddBillActivity.class));


        //Otworzenie aparatu
/*                if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED) {
                    invokeCamera();
                } else {
                    String[] permissionRequested = {Manifest.permission.CAMERA};
                    requestPermissions(permissionRequested, CAMERA_REQUEST_CODE);
                }*/
//            }


        //Niepotrzebne??
           /* public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

                if(requestCode == CAMERA_REQUEST_CODE){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        invokeCamera();
                    } else {
                        //Toast.makeText(this, getString(R.string.unable_to_invoke_camera), Toast.LENGTH_LONG).show();
                    }


                }*/

/*            private void invokeCamera(){

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }*/

//        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addNewBill);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AppActivityBackup.this, AddBillActivity.class));

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

    }


}