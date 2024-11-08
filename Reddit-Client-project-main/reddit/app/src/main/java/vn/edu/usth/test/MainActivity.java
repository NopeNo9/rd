package vn.edu.usth.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import vn.edu.usth.test.User_profile; // or User_profile if you keep that name


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Get the current theme mode from SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkMode = preferences.getBoolean("isDarkMode", false);

        // Apply the saved theme mode
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Dark mode
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Light mode
        }

        // Initialize the TabLayout and ViewPager
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        UserPagerAdapter adapter = new UserPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText(R.string.News);
                            break;
                        case 1:
                            tab.setText(R.string.friends);
                            break;
                        case 2:
                            tab.setText(R.string.Gallery);
                            break;
                    }
                }).attach();

        // Initialize the DrawerLayout and Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Setup ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle click on the account icon to open the drawer
        ImageButton accountIcon = findViewById(R.id.account_icon);
        accountIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open UserProfile fragment
                User_profile userProfileFragment = new User_profile();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, userProfileFragment) // Make sure this ID matches your layout
                        .addToBackStack(null) // Add this transaction to the back stack
                        .commit();
            }
        });

        // Set Navigation Item Selected Listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_change_theme) {
                    // Handle Change Theme menu click
                    toggleTheme();
                    drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer
                    return true;
                } else if (item.getItemId() == R.id.nav_profile) {
                    // Navigate to User Profile Fragment
                    selectedFragment = new User_profile();
                } else if(item.getItemId() == R.id.nav_log_out){
                    // Log out and go to LoginPage Activity
                    Intent intent = new Intent(MainActivity.this, LoginPage.class);
                    startActivity(intent);
                    return true;
                }


                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment) // Replace the fragment container with the new fragment
                            .addToBackStack(null) // Add this transaction to the back stack
                            .commit();
                    drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer
                    return true;
                }

                return false;
            }
        });

        // Initialize Bottom Navigation View
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.navigation_Home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.navigation_Communities) {
                    selectedFragment = new CommunitiesFragment();
                } else if (item.getItemId() == R.id.navigation_Create) {
                    selectedFragment = new CreateFragment();
                } else if (item.getItemId() == R.id.navigation_Chat) {
                    selectedFragment = new ChatFragment();
                } else if (item.getItemId() == R.id.navigation_Inbox) {
                    selectedFragment = new NotificationFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .addToBackStack(null)  // Add this transaction to the back stack
                            .commit();
                    return true;
                }
                return false;
            }

        });

    }

    private void toggleTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkMode = preferences.getBoolean("isDarkMode", false);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Switch to light theme
            // Save background color for light mode
            preferences.edit().putInt("backgroundColor", android.R.color.white).apply();
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Switch to dark theme
            // Save background color for dark mode
            preferences.edit().putInt("backgroundColor", android.R.color.black).apply();
        }

        // Save the new theme mode to SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isDarkMode", !isDarkMode);
        editor.apply();
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            // Pop the back stack
            getSupportFragmentManager().popBackStack();
        } else {
            // No fragments in back stack, so finish activity
            super.onBackPressed();
        }
    }
}
