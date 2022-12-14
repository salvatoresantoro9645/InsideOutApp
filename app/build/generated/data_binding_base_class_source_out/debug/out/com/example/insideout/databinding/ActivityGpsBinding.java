// Generated by view binder compiler. Do not edit!
package com.example.insideout.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.insideout.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityGpsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView gpsCurrentPositionTextView;

  @NonNull
  public final TextView gpsTextView;

  @NonNull
  public final FrameLayout mapContainer;

  private ActivityGpsBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView gpsCurrentPositionTextView, @NonNull TextView gpsTextView,
      @NonNull FrameLayout mapContainer) {
    this.rootView = rootView;
    this.gpsCurrentPositionTextView = gpsCurrentPositionTextView;
    this.gpsTextView = gpsTextView;
    this.mapContainer = mapContainer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGpsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGpsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_gps, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGpsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.gps_currentPosition_textView;
      TextView gpsCurrentPositionTextView = ViewBindings.findChildViewById(rootView, id);
      if (gpsCurrentPositionTextView == null) {
        break missingId;
      }

      id = R.id.gps_textView;
      TextView gpsTextView = ViewBindings.findChildViewById(rootView, id);
      if (gpsTextView == null) {
        break missingId;
      }

      id = R.id.map_container;
      FrameLayout mapContainer = ViewBindings.findChildViewById(rootView, id);
      if (mapContainer == null) {
        break missingId;
      }

      return new ActivityGpsBinding((ConstraintLayout) rootView, gpsCurrentPositionTextView,
          gpsTextView, mapContainer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
