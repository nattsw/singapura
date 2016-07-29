package io.natalietay.towermapper.permission;

import android.support.annotation.NonNull;

public interface PermissionsManager {
    int REQUEST_ACCESS_COARSE_LOCATION = 42;

    boolean hasCoarseLocationPermission();

    void requestCoarseLocationPermission();

    boolean accessCoarseLocationGranted(int requestCode, @NonNull int[] grantResults);
}
