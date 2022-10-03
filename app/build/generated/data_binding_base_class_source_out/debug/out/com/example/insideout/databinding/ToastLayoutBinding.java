// Generated by view binder compiler. Do not edit!
package com.example.insideout.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.insideout.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ToastLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView toastImage;

  @NonNull
  public final LinearLayout toastRoot;

  @NonNull
  public final TextView toastText;

  private ToastLayoutBinding(@NonNull LinearLayout rootView, @NonNull ImageView toastImage,
      @NonNull LinearLayout toastRoot, @NonNull TextView toastText) {
    this.rootView = rootView;
    this.toastImage = toastImage;
    this.toastRoot = toastRoot;
    this.toastText = toastText;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ToastLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ToastLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.toast_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ToastLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.toast_image;
      ImageView toastImage = ViewBindings.findChildViewById(rootView, id);
      if (toastImage == null) {
        break missingId;
      }

      LinearLayout toastRoot = (LinearLayout) rootView;

      id = R.id.toast_text;
      TextView toastText = ViewBindings.findChildViewById(rootView, id);
      if (toastText == null) {
        break missingId;
      }

      return new ToastLayoutBinding((LinearLayout) rootView, toastImage, toastRoot, toastText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
