package io.natalietay.towermapper;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = Build.VERSION_CODES.LOLLIPOP
)
public class SplashActivityTest {
    @Test
    public void shouldNotBeNull()
    {
        ActivityController<SplashActivity> activity =
                Robolectric.buildActivity(SplashActivity.class);
        assertNotNull(activity);
    }
}