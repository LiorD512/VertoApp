package com.dahanlior.vertoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.dahanlior.vertoapp.model.Qr;
import com.dahanlior.vertoapp.utils.GpsLocationTracker;
import com.dahanlior.vertoapp.utils.QrUtils;
import com.dahanlior.vertoapp.view_model.QrScannerViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_PERMISSION = 1;
    private QrScannerViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        checkPermission();

        viewModel = ViewModelProviders
                .of(this)
                .get(QrScannerViewModel.class);


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        byte[] bArray = new byte[0];
        Bitmap bitmap;

        if (result != null) {
            String path = result.getBarcodeImagePath();
            if (path != null) {
                bitmap = QrUtils.encodeAsBitmap(path, 720, 1080);
                QrUtils.saveImage(bitmap, "Qr" + System.currentTimeMillis());
            } else {
                Toast.makeText(this, "Could not take qr photo, try again", Toast.LENGTH_SHORT).show();
                return;
            }


            if (bitmap != null) {
                bArray = QrUtils.compressBitmapToByte(bitmap);
            }

            String decodedValue = result.getContents();

            String address = getCurrentLocation();

            Date date = new Date();

            viewModel.saveNewQr(Qr.create(decodedValue, date, bArray, address));

            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

            Toast.makeText(MainActivity.this, "Scanned" + "\n" + decodedValue + "\n" + address + "\n" +
                    dateFormat.format(date), Toast.LENGTH_LONG).show();
        }


        new Handler().postDelayed(this::initiateQrScanner, 1500);
    }


    private void initiateQrScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan Code");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }


    private String getCurrentLocation() {

        GpsLocationTracker gpsLocationTracker = new GpsLocationTracker(this);
        String address = null;
        List<Address> addresses = null;
        Geocoder geocoder;

        if (gpsLocationTracker.canGetLocation()) {
            double latitude = gpsLocationTracker.getLatitude();
            double longitude = gpsLocationTracker.getLongitude();
            Log.i(TAG, String.format("latitude: %s", latitude));
            Log.i(TAG, String.format("longitude: %s", longitude));

            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert addresses != null;
            address = addresses.get(0).getAddressLine(0);

        } else {
            gpsLocationTracker.showSettingsAlert();
        }

        return address;

    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION
            );
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED && grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                initiateQrScanner();
            } else {
                Toast.makeText(MainActivity.this, "Grant permissions to use this app", Toast.LENGTH_LONG).show();
            }
        }
    }


}
