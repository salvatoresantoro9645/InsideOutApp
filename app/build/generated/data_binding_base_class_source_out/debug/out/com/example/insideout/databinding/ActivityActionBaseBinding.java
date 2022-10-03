// Generated by view binder compiler. Do not edit!
package com.example.insideout.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.insideout.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ActivityActionBaseBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout actionBaseLayout;

  private ActivityActionBaseBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout actionBaseLayout) {
    this.rootView = rootView;
    this.actionBaseLayout = actionBaseLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityActionBaseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityActionBaseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_action_base, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityActionBaseBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    ConstraintLayout actionBaseLayout = (ConstraintLayout) rootView;

    return new ActivityActionBaseBinding((ConstraintLayout) rootView, actionBaseLayout);
  }
}
