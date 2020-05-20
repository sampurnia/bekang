package com.example.bekang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.bekang.Tab.MyAdapter;
import com.example.bekang.fragment.HelpFragment;
import com.example.bekang.fragment.HomeFragment;
import com.example.bekang.fragment.ListFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private MyAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private int[] tabIcon = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_notifications_black_24dp,
            R.drawable.ic_dashboard_black_24dp,
    };

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setSupportActionBar(toolbar);

        tabAdapter = new MyAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new HomeFragment(), "Tab 1");
        tabAdapter.addFragment(new HelpFragment(), "Tab 2");
        tabAdapter.addFragment(new ListFragment(), "Tab 3");
        tabAdapter.addFragment(new ListFragment(), "Tab 4");
        tabAdapter.addFragment(new HelpFragment(), "Tab 5");
        tabAdapter.addFragment(new HomeFragment(), "Tab 6");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(tabIcon[0]);
        tabLayout.getTabAt(1).setIcon(tabIcon[1]);
        tabLayout.getTabAt(2).setIcon(tabIcon[2]);
        tabLayout.getTabAt(3).setIcon(tabIcon[3]);
        tabLayout.getTabAt(4).setIcon(tabIcon[4]);
        tabLayout.getTabAt(5).setIcon(tabIcon[5]);

        ColorStateList colors;
        if (Build.VERSION.SDK_INT >= 23) {
            colors = getResources().getColorStateList(R.drawable.tab_icon, getTheme());
        }
        else {
            colors = getResources().getColorStateList(R.drawable.tab_icon);
        }

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Drawable icon = tab.getIcon();

            if (icon != null) {
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTintList(icon, colors);
            }
        }

    }
}

