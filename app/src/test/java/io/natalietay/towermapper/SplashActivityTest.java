package io.natalietay.towermapper;

import android.content.Intent;
import android.os.Build;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = Build.VERSION_CODES.LOLLIPOP
)
public class SplashActivityTest {

    ActivityController<SplashActivity> activityController;
    SplashActivity activity;

    @Before
    public void setup() {
        activityController = Robolectric.buildActivity(SplashActivity.class);
        activity = Robolectric.setupActivity(SplashActivity.class);
    }

    @Test
    public void navigateToMainActivity() {
        ShadowActivity shadowActivity = (ShadowActivity) ShadowExtractor.extract(activity);
        Intent nextStartedActivity = shadowActivity.getNextStartedActivity();
        Intent expectedIntent = new Intent(activity, MainActivity.class);

        assertTrue(expectedIntent.filterEquals(nextStartedActivity));
    }
}
