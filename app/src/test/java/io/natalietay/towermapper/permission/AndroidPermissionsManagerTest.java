package io.natalietay.towermapper.permission;

import android.Manifest;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowApplication;

import io.natalietay.towermapper.BuildConfig;
import io.natalietay.towermapper.SplashActivity;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = Build.VERSION_CODES.LOLLIPOP
)
public class AndroidPermissionsManagerTest {


    @Test
    public void testHasCoarseLocationPermission() throws Exception {
        SplashActivity activity = Robolectric.buildActivity(SplashActivity.class).get();
        ShadowApplication application = (ShadowApplication) ShadowExtractor.extract(activity.getApplication());
        AndroidPermissionsManager manager = new AndroidPermissionsManager(activity);

        assertFalse(manager.hasCoarseLocationPermission());

        application.grantPermissions(Manifest.permission.ACCESS_COARSE_LOCATION);
        assertTrue(manager.hasCoarseLocationPermission());
    }
}
