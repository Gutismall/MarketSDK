package com.ariguter.marketdemosdk.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.ariguter.marketdemosdk.R;
import com.ariguter.marketdemosdk.databinding.ActivityLandingScreenBinding;
import com.ariguter.marketdemosdk.viewModel.LandingPageViewModel;
import com.ariguter.marketdemosdk.views.fragments.HomeFragment;
import com.ariguter.marketdemosdk.views.fragments.MarketFragment;
import com.ariguter.marketdemosdk.views.fragments.ProfileFragment;

public class LandingScreen extends AppCompatActivity {
    private ActivityLandingScreenBinding binding;

    private final LandingPageViewModel viewModel = new LandingPageViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(binding.fragmentContainerView.getId(), new HomeFragment())
                    .commit();
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;
            int id = item.getItemId();
            if (id == R.id.menu_Home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.menu_Market) {
                selectedFragment = new MarketFragment();
            } else if (id == R.id.menu_Profile) {
                selectedFragment = new ProfileFragment();
            } else {
                return false;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(binding.fragmentContainerView.getId(), selectedFragment)
                    .commit();
            return true;
        });
    }
}