package io.natalietay.towermapper.permission;

import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class AndroidPermissionsManager implements PermissionsManager {
    private Activity activity;

    public AndroidPermissionsManager(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean hasCoarseLocationPermission() {
        return ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PERMISSION_GRANTED;
    }

    @Override
    public void requestCoarseLocationPermission() {
        ActivityCompat.requestPermissions(
                activity,
                new String[]{(Manifest.permission.ACCESS_COARSE_LOCATION)},
                REQUEST_ACCESS_COARSE_LOCATION
        );
    }

    @Override
    public boolean accessCoarseLocationGranted(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_ACCESS_COARSE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }
}
