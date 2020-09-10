package com.gaad.GADSLeaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gaad.GADSLeaderboard.adapter.ViewPagerAdapter;
import com.gaad.GADSLeaderboard.fragments.LearningFragment;
import com.gaad.GADSLeaderboard.fragments.SkillIQFragment;
import com.google.android.material.tabs.TabLayout;

public class LeaderBoard extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager1;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_leader_board);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager1 = findViewById(R.id.viewPager1);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add Fragments Here
        adapter.AddFragment(new LearningFragment(), "Learning Leaders");
        adapter.AddFragment(new SkillIQFragment(),"Skill IQ Leaders");

        tabLayout.setupWithViewPager(viewPager1);
        viewPager1.setAdapter(adapter);

    }

    public void subMitClick(View view) {
        Intent i = new Intent(this, Submit.class );
        startActivity(i);

    }
}