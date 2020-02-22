package com.worldworkssys.stressless.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.worldworkssys.stressless.R;
import com.worldworkssys.stressless.adapter.IntroViewPagerAdapter;
import com.worldworkssys.stressless.databinding.ActivityIntroBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;

    IntroViewPagerAdapter introViewPagerAdapter;
    //TabLayout tabIndicator;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // when this activity is about to be launch we need to check if its openened before or not

        if (restorePrefData()) {

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        // fill list screen

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Add Courses", "Add cours one by one with their respective course numbers", R.drawable.screen_1));
        mList.add(new ScreenItem("Add More Details", "Add assignments, quizzes, midterm, projects and finals marks", R.drawable.screen_2));
        mList.add(new ScreenItem("Calculate current grade", "Input all the marks for the respective course to calculate current grade", R.drawable.screen_3));

        // setup viewpager
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        binding.viewPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager

        binding.tabIndicator.setupWithViewPager(binding.viewPager);

        // next button click Listner

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = binding.viewPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    binding.viewPager.setCurrentItem(position);
                }

                if (position == mList.size() - 1) {

                    loaddLastScreen();
                }
            }
        });


        binding.tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size() - 1) {

                    loaddLastScreen();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent mainActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });

        binding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewPager.setCurrentItem(mList.size());
            }
        });
    }

    private boolean restorePrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend", false);
        return isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend", true);
        editor.commit();
    }
    private void loaddLastScreen() {

        binding.btnNext.setVisibility(View.INVISIBLE);
        binding.btnGetStarted.setVisibility(View.VISIBLE);
        binding.tvSkip.setVisibility(View.INVISIBLE);
    }
}