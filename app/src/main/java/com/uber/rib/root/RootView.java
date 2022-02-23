package com.uber.rib.root;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.uber.rib.root.RootInteractor.RootPresenter;
import com.uber.rib.sample.R;

public class RootView extends FrameLayout implements RootPresenter {

  private TextView tv_count;

  public RootView(@NonNull Context context) {
    super(context);
  }

  public RootView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public RootView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    tv_count = findViewById(R.id.tv_count);
  }

  @Override
  public void bindCount(String count) {
    System.out.println("count:"+count);
    tv_count.setText(count);
  }
}
