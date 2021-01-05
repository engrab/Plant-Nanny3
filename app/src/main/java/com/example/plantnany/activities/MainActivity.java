package com.example.plantnany.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.plantnany.R;
import com.example.plantnany.fragments.AllPlantsFragment;
import com.example.plantnany.fragments.GraphFragment;
import com.example.plantnany.fragments.HomeFragment;
import com.example.plantnany.fragments.PotsFragment;
import com.example.plantnany.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_pots:
                fragment = new PotsFragment();
                break;

            case R.id.navigation_all_plants:
                fragment = new AllPlantsFragment();
                break;

            case R.id.navigation_setting:
                fragment = new SettingsFragment();
                break;
            case R.id.navigation_graph:
                fragment = new GraphFragment();
                break;
            default:
                fragment = new HomeFragment();
        }

        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return true;
    }
}