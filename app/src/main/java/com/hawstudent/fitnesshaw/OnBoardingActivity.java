package com.hawstudent.fitnesshaw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private Button buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnBoardingIndicators);
        buttonOnboardingAction = findViewById(R.id.btnOnBoardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onBoardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        });
    }

    private void setupOnboardingItems(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemFindNewPeopleToTrainWith = new OnboardingItem();
        itemFindNewPeopleToTrainWith.set_title("Find New People To Train With");
        itemFindNewPeopleToTrainWith.set_description("Your perfect workout buddy is out there, you just gotta know where to look!");
        itemFindNewPeopleToTrainWith.set_image(R.drawable.train_with_partner);

        OnboardingItem itemOrganizeYourTime = new OnboardingItem();
        itemOrganizeYourTime.set_title("Organize Your Time to Exercise");
        itemOrganizeYourTime.set_description("Set reminders to help you stay on track.");
        itemOrganizeYourTime.set_image(R.drawable.time_organisieren);

        OnboardingItem itemShareYourWorkout = new OnboardingItem();
        itemShareYourWorkout.set_title("Share Your Workout Plan");
        itemShareYourWorkout.set_description("You can share your own Workout plan with friends and others.");
        itemShareYourWorkout.set_image(R.drawable.share_your_workout);

        onboardingItems.add(itemFindNewPeopleToTrainWith);
        onboardingItems.add(itemOrganizeYourTime);
        onboardingItems.add(itemShareYourWorkout);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if (index == onboardingAdapter.getItemCount() - 1){
            buttonOnboardingAction.setText("Start");
        }else {
            buttonOnboardingAction.setText("Next");
        }
    }
}