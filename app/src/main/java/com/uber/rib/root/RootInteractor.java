package com.uber.rib.root;

import static com.uber.autodispose.AutoDispose.autoDisposable;

import androidx.annotation.Nullable;
import com.uber.rib.core.BasicInteractor;
import com.uber.rib.core.Bundle;
import com.uber.rib.core.WorkerBinder;
import com.uber.rib.root.RootInteractor.RootPresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

class RootInteractor extends BasicInteractor<RootPresenter, RootRouter> {

  private final RootPresenter presenter;
  private final RootWorker rootWorker;
  private final FakeDataStreaming fakeDataStreaming;

  protected RootInteractor(RootPresenter presenter, RootWorker rootWorker,
      FakeDataStreaming fakeDataStreaming) {
    super(presenter);
    this.presenter = presenter;
    this.rootWorker = rootWorker;
    this.fakeDataStreaming = fakeDataStreaming;
  }

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);
    WorkerBinder.bind(this, rootWorker);
    fakeDataStreaming.dataStreaming()
        .observeOn(AndroidSchedulers.mainThread()).as(autoDisposable(this))
        .subscribe(presenter::bindCount);

  }

  interface RootPresenter {

    void bindCount(String count);
  }
}
