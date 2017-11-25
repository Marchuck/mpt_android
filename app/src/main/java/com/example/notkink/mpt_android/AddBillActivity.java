package com.example.notkink.mpt_android;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.notkink.mpt_android.toast.Toaster;
import com.example.notkink.mpt_android.upload.ImageUploader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import pub.devrel.easypermissions.EasyPermissions;

public class AddBillActivity extends AppCompatActivity {

    Toaster toaster = new Toaster();

    File photoFile = null;

    ImageView openCameraImageView;
    ImageView imgTakenPic;
    ImageView pickDate;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int CAMERA_REQUEST_CODE = 10;
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private String mImageFileLocation = "";
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Bitmap bitmap;

    private Button buttonAdd;
    private ImageView uploadImageView;

    private EditText year, shopName, billName;
    private EditText purchaseDate;
    private Spinner unitSpinner;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_bill);

        findAllViews();

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCalendarDialog();
            }
        });
        openCameraImageView.setOnClickListener(new imgTakePhotoClicker());
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                purchaseDate.setText(date);

            }
        };

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
                NavUtils.navigateUpFromSameTask(AddBillActivity.this);

            }
        });

        uploadImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send file
//                File file = photoFile;
//                new ImageUploader().uploadFile(file);

                //send scaled bitmap
                Bitmap reducedBitmap = toReducedImageSize(bitmap);
                new ImageUploader().uploadImage(reducedBitmap);
            }
        });
    }

    private void findAllViews() {
        openCameraImageView = findViewById(R.id.openCamera);
        imgTakenPic = findViewById(R.id.takenPhoto);
        uploadImageView = findViewById(R.id.upload);
        pickDate = findViewById(R.id.addDate);
        year = findViewById(R.id.year);
        shopName = findViewById(R.id.shopNameFill);
        billName = findViewById(R.id.billNameFill);
        purchaseDate = findViewById(R.id.dateOfPurchaseFill); // pole do wpisanie daty z kalendarza
        unitSpinner = findViewById(R.id.spin);
        buttonAdd = findViewById(R.id.add);
    }


    File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);
        mImageFileLocation = image.getAbsolutePath();

        return image;
    }

    private Bitmap toReducedImageSize(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int targetWidth = imgTakenPic.getWidth();
        int targetHeight = imgTakenPic.getHeight();

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false);

        return scaledBitmap;
    }

    public void takePhoto(View view) {

        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));

        startActivityForResult(intent, ACTIVITY_START_CAMERA_APP);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            //Toast.makeText(this, "Picture taken successfully", Toast.LENGTH_SHORT).show();
            //Bundle extras = data.getExtras();
            //Bitmap photoCapturedBitmap = (Bitmap) extras.get("data");
            //imgTakenPic.setImageBitmap(photoCapturedBitmap);
            //Bitmap photoCapturedBitmap = BitmapFactory.decodeFile(mImageFileLocation);
            //imgTakenPic.setImageBitmap(photoCapturedBitmap);

            if (EasyPermissions.hasPermissions(this, galleryPermissions)) {
                /*Bitmap*/
                bitmap = (Bitmap) data.getExtras().get("data");
                imgTakenPic.setImageBitmap(bitmap);
            } else {
                EasyPermissions.requestPermissions(this, "Access for storage",
                        101, galleryPermissions);
            }

//            if (EasyPermissions.hasPermissions(this, galleryPermissions)) {
//                toReducedImageSize();
//            } else {
//                EasyPermissions.requestPermissions(this, "Access for storage",
//                        101, galleryPermissions);
//            }


        }

    }

    public void addItem() {

        GuaranteeUnits unit = null;
        switch (String.valueOf(unitSpinner.getSelectedItem())) {
            case "Rok":
                unit = GuaranteeUnits.YEAR;
                break;
            case "Miesiac":
                unit = GuaranteeUnits.MONTH;
                break;
            case "Dzien":
                unit = GuaranteeUnits.DAY;
                break;
        }
        BillEntry be = new BillEntry();
        be.setGuaranteeDuration(Integer.parseInt(year.getText().toString()));
        be.setShopName(shopName.getText().toString());
        be.setBillName(billName.getText().toString());
        be.setGuaranteeUnit(unit);
        be.setPhoto(bitmap);
        BillEntriesCointener.billEntries.add(be);
    }


    class imgTakePhotoClicker implements View.OnClickListener {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View view) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                takePhoto(view);
            } else {
                String[] permissionRequested = {Manifest.permission.CAMERA};
                requestPermissions(permissionRequested, CAMERA_REQUEST_CODE);
            }
        }
    }

    private void showCalendarDialog() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(AddBillActivity.this, mDateSetListener,
                year, month, dayOfMonth);
        dialog.show();
    }
}