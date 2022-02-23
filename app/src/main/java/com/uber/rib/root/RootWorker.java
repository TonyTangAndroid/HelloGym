package com.uber.rib.root;

import static com.uber.autodispose.AutoDispose.autoDisposable;

import androidx.annotation.NonNull;
import com.uber.rib.core.Worker;
import com.uber.rib.core.WorkerScopeProvider;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class RootWorker implements Worker {


  @Override
  public void onStart(@NonNull WorkerScopeProvider lifecycle) {
    Worker.super.onStart(lifecycle);
    Observable.interval(1, TimeUnit.SECONDS).as(autoDisposable(lifecycle)).subscribe(this::log);
  }

  private void log(Long count) {
    System.out.printf("WorkerCount:%s%n", count);
  }


}
