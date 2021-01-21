package com.example.scrumquestions;

import android.app.Application;

import com.facebook.flipper.android.AndroidFlipperClient;
import com.facebook.flipper.android.utils.FlipperUtils;
import com.facebook.flipper.core.FlipperClient;
import com.facebook.flipper.plugins.inspector.DescriptorMapping;
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin;
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin;
import com.facebook.flipper.plugins.sandbox.SandboxFlipperPlugin;
import com.facebook.flipper.plugins.sandbox.SandboxFlipperPluginStrategy;
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin;
import com.facebook.soloader.SoLoader;
import com.facebook.flipper.plugins.inspector.DescriptorMapping;
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin;

public class MyApplication extends Application {

  public static final String sharePreferente = "my_shared_preference_file";

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, false);

    if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
      final FlipperClient client = AndroidFlipperClient.getInstance(this);

      // Plugin 1
      client.addPlugin(new InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()));
      // Plugin Navigation
      client.addPlugin(NavigationFlipperPlugin.getInstance());

      client.addPlugin(
              new SharedPreferencesFlipperPlugin(this, sharePreferente));

      client.start();
    }
  }
}