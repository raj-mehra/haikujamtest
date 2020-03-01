package com.raj.haikujamtest.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.raj.haikujamtest.R;

public class BindingAdapterUtil {
    @BindingAdapter({ "app:loadImage" })
    public static void loadGlideImage(ImageView imageView, Drawable imageUrl) {
        AppUtils.loadImage(imageView.getContext(), imageUrl, imageView,
                R.drawable.ic_image_placeholder);
    }
}
