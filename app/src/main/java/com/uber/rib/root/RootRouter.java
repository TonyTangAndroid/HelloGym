package com.uber.rib.root;

import androidx.annotation.NonNull;
import com.uber.rib.core.ViewRouter;

 class RootRouter extends ViewRouter<RootView, RootInteractor> {

  protected RootRouter(
      @NonNull RootView view,
      @NonNull RootInteractor interactor) {
    super(view, interactor);
  }
}
