package com.raj.haikujamtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;

import com.raj.haikujamtest.databinding.ActivityMainBinding;
import com.raj.haikujamtest.interfaces.ClickListener;
import com.raj.haikujamtest.viewmodels.MainViewModel;

import java.util.concurrent.TimeUnit;

import static com.raj.haikujamtest.utils.AppUtils.animateCircle;
import static com.raj.haikujamtest.utils.AppUtils.convertDpToPx;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setMainViewModel(mainViewModel);
        setDefaultVisibility();
        setListeners();
    }

    private void setListeners() {
        binding.imageButton.setOnClickListener(imageClickListener);
    }

    private void setDefaultVisibility() {
        binding.imageLeft.setVisibility(View.GONE);
        binding.imageRight.setVisibility(View.GONE);
        binding.imageBottomright.setVisibility(View.GONE);
        binding.button.setVisibility(View.GONE);
        binding.button.setOnClickListener(firstAnimation);
    }

    View.OnClickListener firstAnimation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int fromValue = convertDpToPx(MainActivity.this, 150);
            int toValue = convertDpToPx(MainActivity.this, 0);
            ValueAnimator anim = ValueAnimator.ofInt(fromValue, toValue);
            final ValueAnimator animator = animateCircle(anim, binding.imageLeft, TimeUnit.SECONDS.toMillis(1));
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    TransitionManager.beginDelayedTransition(binding.transitionsContainer);
                    binding.imageLeft.setVisibility(View.GONE);
                    binding.button.setOnClickListener(secondAnimation);
                }
            });


        }
    };

    View.OnClickListener secondAnimation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int fromValue = convertDpToPx(MainActivity.this, 150);
            int toValue = convertDpToPx(MainActivity.this, 0);
            ValueAnimator anim = ValueAnimator.ofInt(fromValue, toValue);
            final ValueAnimator animator = animateCircle(anim, binding.imageRight, TimeUnit.SECONDS.toMillis(1));
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    TransitionManager.beginDelayedTransition(binding.transitionsContainer);
                    binding.imageRight.setVisibility(View.GONE);
                    binding.button.setOnClickListener(thirdAnimation);
                }
            });

        }
    };

    View.OnClickListener thirdAnimation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int fromValue = convertDpToPx(MainActivity.this, 150);
            int toValue = convertDpToPx(MainActivity.this, 0);
            ValueAnimator anim = ValueAnimator.ofInt(fromValue, toValue);
            final ValueAnimator animator = animateCircle(anim, binding.imageBottomright, TimeUnit.SECONDS.toMillis(1));
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    TransitionManager.beginDelayedTransition(binding.transitionsContainer);
                    binding.imageBottomright.setVisibility(View.GONE);
                    binding.button.setText("Reset");
                    binding.button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //code to reset to initial state
                            binding.imageButton.setVisibility(View.VISIBLE);
                            binding.button.setText("Next");
                            TransitionManager.beginDelayedTransition(binding.transitionsContainer);
                            binding.image.setRadius(convertDpToPx(MainActivity.this, 0));
                            binding.image.getLayoutParams().height = convertDpToPx(MainActivity.this, 250);
                            binding.image.getLayoutParams().width = convertDpToPx(MainActivity.this, 250);
                            binding.imageLeft.setVisibility(View.GONE);
                            binding.imageRight.setVisibility(View.GONE);
                            binding.imageBottomright.setVisibility(View.GONE);
                            binding.button.setVisibility(View.GONE);
                            binding.image.setOnClickListener(imageClickListener);
                            binding.button.setOnClickListener(firstAnimation);

                            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) binding.imageLeft.getLayoutParams();
                            layoutParams.circleRadius = convertDpToPx(MainActivity.this, 150);
                            binding.imageLeft.setLayoutParams(layoutParams);

                            layoutParams = (ConstraintLayout.LayoutParams) binding.imageRight.getLayoutParams();
                            layoutParams.circleRadius = convertDpToPx(MainActivity.this, 150);
                            binding.imageRight.setLayoutParams(layoutParams);

                            layoutParams = (ConstraintLayout.LayoutParams) binding.imageBottomright.getLayoutParams();
                            layoutParams.circleRadius = convertDpToPx(MainActivity.this, 150);
                            binding.imageBottomright.setLayoutParams(layoutParams);

                        }
                    });
                }
            });
        }
    };

    View.OnClickListener imageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //update card property to convert card from square to circle
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binding.imageButton.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(binding.transitionsContainer);
                    binding.image.setRadius(convertDpToPx(MainActivity.this, 200));
                    binding.image.getLayoutParams().height = convertDpToPx(MainActivity.this, 300);
                    binding.image.getLayoutParams().width = convertDpToPx(MainActivity.this, 300);

                    //change visibility of buttons and 3 circles
                    TransitionManager.beginDelayedTransition(binding.transitionsContainer);
                    binding.imageLeft.setVisibility(View.VISIBLE);
                    binding.imageRight.setVisibility(View.VISIBLE);
                    binding.imageBottomright.setVisibility(View.VISIBLE);
                    binding.button.setVisibility(View.VISIBLE);
                }
            });
            //anim.start();
            //reset the handler
            binding.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    };

}
