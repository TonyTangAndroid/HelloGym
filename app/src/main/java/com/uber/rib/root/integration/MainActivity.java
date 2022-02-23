package com.uber.rib.root.integration;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.uber.rib.core.RibActivity;
import com.uber.rib.core.ViewRouter;
import com.uber.rib.root.RootScope;
import motif.ScopeFactory;

public class MainActivity extends RibActivity {

  @NonNull
  @Override
  protected ViewRouter<?, ?> createRouter(@NonNull ViewGroup parentViewGroup) {
    return ScopeFactory.create(RootScope.class, () -> parentViewGroup).rootRouter();
  }
}