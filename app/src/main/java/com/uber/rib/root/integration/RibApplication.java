package com.uber.rib.root.integration;

import android.app.Application;
import timber.log.ThreadTree;
import timber.log.Timber;

public class RibApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    initLog();
  }

  private void initLog() {
    Timber.plant(new ThreadTree("Tony_Gym"));
    System.out.println("onCreate");
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    onTerminated();
  }

  private void onTerminated() {
    System.out.println("onTerminate");
  }
}