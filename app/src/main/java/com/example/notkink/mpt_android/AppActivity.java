package com.example.notkink.mpt_android;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class AppActivity extends ListActivity {


    public static final int CAMERA_REQUEST_CODE = 10;
    ImageView imageView;
    private Button addPictureButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
//                 TODO Auto-generated method stub
//                startActivity(new Intent(AppActivity.this, AddBillActivity.class));


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

                startActivity(new Intent(AppActivity.this, AddBillActivity.class));

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        listView = getListView();
        //fillOptions();
        listView.setAdapter(new CustomArrayAdapter(BillEntriesCointener.billEntries, this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Clicked position " + i);
                //Todo wyswietlenie koljengo activity z wygladem paragonu
            }
        });
    }

/*    private void fillOptions() {

        billEntries = new LinkedList<>();
        if (BillEntriesCointener.billEntries.isEmpty()){

            BillEntry billEntry1 = new BillEntry();
            billEntry1.setShopName("Sklep");
            billEntry1.setPurchaseDate(new Date());
            billEntry1.setPhoto(BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_name));
            BillEntriesCointener.billEntries.add(billEntry1);

            BillEntry billEntry2 = new BillEntry();
            billEntry2.setShopName("Sklep");
            billEntry2.setPurchaseDate(new Date());
            billEntry2.setPhoto(BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_name));

            BillEntriesCointener.billEntries.add(billEntry2);
        }
    }*/

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