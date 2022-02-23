package com.uber.rib.root;

import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class FakeDataStreaming {

  private final int intervalInMilliseconds;

  public FakeDataStreaming(int intervalInMilliseconds) {
    this.intervalInMilliseconds = intervalInMilliseconds;
  }

  public Observable<String> dataStreaming() {
    return Observable.interval(intervalInMilliseconds, TimeUnit.MILLISECONDS).map(String::valueOf);
  }

}
