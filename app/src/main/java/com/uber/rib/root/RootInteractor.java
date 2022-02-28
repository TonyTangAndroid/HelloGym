package com.uber.rib.root;

import static com.uber.autodispose.AutoDispose.autoDisposable;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.uber.autodispose.ScopeProvider;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.uber.rib.core.BasicInteractor;
import com.uber.rib.core.Bundle;
import com.uber.rib.core.WorkerBinder;
import com.uber.rib.root.RootInteractor.RootPresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

class RootInteractor extends BasicInteractor<RootPresenter, RootRouter> {

  private final RootPresenter presenter;
  private final RootWorker rootWorker;
  private final UnboundedScopedWorker unboundedScopedWorker;
  private final ProcessLifeCycleScopedWorker processLifeCycleScopedWorker;
  private final FakeDataStreaming fakeDataStreaming;

  protected RootInteractor(RootPresenter presenter, RootWorker rootWorker,
      UnboundedScopedWorker unboundedScopedWorker,
      ProcessLifeCycleScopedWorker processLifeCycleScopedWorker,
      FakeDataStreaming fakeDataStreaming) {
    super(presenter);
    this.presenter = presenter;
    this.rootWorker = rootWorker;
    this.unboundedScopedWorker = unboundedScopedWorker;
    this.processLifeCycleScopedWorker = processLifeCycleScopedWorker;
    this.fakeDataStreaming = fakeDataStreaming;
  }

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);
    bindWorkers();
    observeStream();

  }

  private void bindWorkers() {
    WorkerBinder.bind(this, rootWorker);
    unboundedScopedWorker.onStart(ScopeProvider.UNBOUND);
    processLifeCycleScopedWorker.onStart(AndroidLifecycleScopeProvider.from(ProcessLifecycleOwner.get(),
        Event.ON_DESTROY));
  }

  private void observeStream() {
    fakeDataStreaming.dataStreaming()
        .observeOn(AndroidSchedulers.mainThread()).as(autoDisposable(this))
        .subscribe(presenter::bindCount);
  }

  interface RootPresenter {

    void bindCount(String count);
  }
}
