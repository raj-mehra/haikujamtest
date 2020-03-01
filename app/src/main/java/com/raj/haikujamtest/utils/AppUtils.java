package com.raj.haikujamtest.utils;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;

public class AppUtils {


    public static int convertDpToPx(Context context, float dp) {
        float f = dp * context.getResources().getDisplayMetrics().density;
        return (int) f;

    }

    public static ValueAnimator animatePlanet(final ImageView planet, long orbitDuration) {
        ValueAnimator anim = ValueAnimator.ofInt(0, 359);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) planet.getLayoutParams();
                layoutParams.circleAngle = val;
                planet.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(orbitDuration);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(1);

        return anim;
    }

    public static ValueAnimator animateCircle(final ValueAnimator anim, final ImageView circle, long animationDuration) {

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) circle.getLayoutParams();
                layoutParams.circleRadius = val;
                circle.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(animationDuration);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(0);
        return anim;
    }

    @SuppressLint("CheckResult")
    public static void loadImage(final Context mContext, final Drawable drawable, final ImageView imageView,
                                 int placeHolder) {
        try {
            if (mContext == null || imageView == null || drawable == null) return;
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(AppCompatResources.getDrawable(mContext, placeHolder));
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
            requestOptions.error(AppCompatResources.getDrawable(mContext, placeHolder));
            Glide.with(mContext).load(drawable).apply(requestOptions).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
