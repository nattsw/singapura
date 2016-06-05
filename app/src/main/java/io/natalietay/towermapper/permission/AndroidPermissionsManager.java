package io.natalietay.towermapper.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

public class AndroidPermissionsManager implements PermissionsManager {
    private Context context;

    public AndroidPermissionsManager(Context context) {
        this.context = context;
    }

    @Override
    public boolean hasCoarseLocationPermission() {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }
}
