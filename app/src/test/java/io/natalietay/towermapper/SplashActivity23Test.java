package io.natalietay.towermapper;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import io.natalietay.towermapper.permission.PermissionsManager;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = Build.VERSION_CODES.M
)
public class SplashActivity23Test {

    ActivityController<SplashActivity> activityController;

    @Before
    public void setup() {
        activityController = Robolectric.buildActivity(SplashActivity.class);
    }

    @Test
    public void hasLocationPermissionNavigateToMainActivity() {
        SplashActivity activity = activityController.create().get();
        PermissionsManager permissionsManager = new PermissionsManager() {
            @Override
            public boolean hasCoarseLocationPermission() {
                return true;
            }

            @Override
            public void requestCoarseLocationPermission() {

            }

            @Override
            public boolean accessCoarseLocationGranted(int requestCode, @NonNull int[] grantResults) {
                return true;
            }
        };
        activity.permissionsManager = permissionsManager;

        activityController.resume();

        ShadowActivity shadowActivity = (ShadowActivity) ShadowExtractor.extract(activity);
        Intent nextStartedActivity = shadowActivity.getNextStartedActivity();
        Intent expectedIntent = new Intent(activity, MainActivity.class);
        assertTrue(expectedIntent.filterEquals(nextStartedActivity));
    }
}
