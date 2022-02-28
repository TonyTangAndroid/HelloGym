package com.uber.rib.root;

import static com.uber.autodispose.AutoDispose.autoDisposable;

import androidx.annotation.NonNull;
import com.uber.autodispose.ScopeProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;
import timber.log.Timber;

public class UnboundedScopedWorker {

  public void onStart(@NonNull ScopeProvider scopeProvider) {
    Observable.interval(1, TimeUnit.SECONDS)
        .doOnSubscribe(this::logOnSubscribe)
        .doOnDispose(this::logOnDispose)
        .as(autoDisposable(scopeProvider)).subscribe(this::log);
  }

  private void logOnDispose() {
    Timber.i("logOnDispose");
  }

  private void logOnSubscribe(Disposable disposable) {
    Timber.i("logOnSubscribe:%s", disposable);
  }

  private void log(Long count) {
    Timber.v("UnboundedScopedWorker:%s%n", count);
  }


}
