package io.natalietay.towermapper;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import io.natalietay.towermapper.permission.AndroidPermissionsManager;
import io.natalietay.towermapper.permission.PermissionsManager;

public class SplashActivity extends AppCompatActivity {
    //In the splash activity, check if the app has its required permissions
    // then move on to the MainActivity.
    //The code caters for both before and after Marshmallow.

    PermissionsManager permissionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Inject this soon
        permissionsManager = new AndroidPermissionsManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsManager.hasCoarseLocationPermission()) {
                navigateToMainActivity();
            } else {
                permissionsManager.requestCoarseLocationPermission();
            }
        } else {
            navigateToMainActivity();
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (permissionsManager.accessCoarseLocationGranted(requestCode, grantResults)) {
            //do stuff

        } else {

        }
    }
}
