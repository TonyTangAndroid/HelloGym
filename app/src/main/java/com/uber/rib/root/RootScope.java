package com.uber.rib.root;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.uber.rib.core.ViewRouter;
import com.uber.rib.sample.R;
import motif.Creatable;

@motif.Scope
public interface RootScope extends Creatable<RootScope.Dependencies> {

  ViewRouter<?, ?> rootRouter();

  @motif.Objects
  abstract class objects {

    abstract FakeDataStreaming fakeDataStreaming();

    abstract RootInteractor rootInteractor();

    abstract RootInteractor.RootPresenter rootInteractor(RootView rootView);

    abstract RootRouter rootRouter();
    abstract RootWorker rootWorker();

    abstract ViewRouter<?, ?> viewRouter(RootRouter rootRouter);

    static int intervalInMilliseconds() {
      return 3000;
    }

    static RootView rootView(ViewGroup parentViewGroup) {
      return (RootView) LayoutInflater.from(parentViewGroup.getContext())
          .inflate(R.layout.root_view, parentViewGroup, false);
    }
  }

  interface Dependencies {

    ViewGroup parentViewGroup();
  }
}
