package com.uber.rib.root.integration;

import android.app.Application;

public class RibApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    System.out.println("onCreate");
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    System.out.println("onTerminate");
  }
}