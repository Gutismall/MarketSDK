package com.ariguter.marketdemosdk.views.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ariguter.marketdemosdk.R;
import com.ariguter.marketdemosdk.databinding.FragmentCreateNewPostDialogBinding;
import com.ariguter.marketdemosdk.viewModel.CreateNewPostViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CreateNewPostDialogFragment extends DialogFragment {
    private MutableLiveData<Map<String, String>> categoryNames = new MutableLiveData<>(new HashMap<>());

    private FragmentCreateNewPostDialogBinding binding;
    private CreateNewPostViewModel viewModel;
    private String marketId;

    public CreateNewPostDialogFragment() {
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateNewPostDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(CreateNewPostViewModel.class);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MarketPreferences", Context.MODE_PRIVATE);
        marketId = sharedPreferences.getString("selectedMarket", null);
        viewModel.setMarketId(marketId);

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());

        binding.createNewPostUploadImageButton.setOnClickListener(v -> openImagePicker());

        viewModel.getAllCategoriesNames().observe(getViewLifecycleOwner(), categories -> {
            categoryNames.postValue(categories);
            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, new ArrayList<>(categories.values()));
            binding.autoCompleteTextView.setAdapter(categoryAdapter);
        });

        viewModel.getPostCreated().observe(getViewLifecycleOwner(), created -> {
            if (created != null && created) {
                Toast.makeText(requireContext(), "Post created successfully", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        viewModel.loadCategories();

        binding.createNewPostCreateButton.setOnClickListener(v -> {
            String selectedCategory = binding.autoCompleteTextView.getText().toString();
            String selectedCategoryId = null;
            Map<String, String> categories = categoryNames.getValue();
            if (categories != null) {
                for (Map.Entry<String, String> entry : categories.entrySet()) {
                    if (entry.getValue().equals(selectedCategory)) {
                        selectedCategoryId = entry.getKey();
                        break;
                    }
                }
            }
            String title = String.valueOf(binding.createNewPostTitle.getText());
            String description = String.valueOf(binding.createNewPostDescription.getText());
            double price = Double.parseDouble(String.valueOf(binding.createNewPostPrice.getText()));
            Log.e("CreateNewPostDialogFragment", "Selected Category: " + selectedCategoryId +
                    ", Title: " + title +
                    ", Description: " + description +
                    ", Price: " + price);
            fetchLocationAndCreatePost(fusedLocationClient, title, description, price, selectedCategoryId);
        });
    }


    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    private void fetchLocationAndCreatePost(FusedLocationProviderClient fusedLocationClient, String title, String description, double price, String selectedCategory) {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            String country = "";
            String city = "";
            if (location != null) {
                Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        country = address.getCountryName();
                        city = address.getLocality();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Replace "userId" with actual user ID as needed
            Log.e("CreateNewPostDialogFragment", "Selected Category: " + selectedCategory +
                    ", Title: " + title +
                    ", Description: " + description +
                    ", Price: " + price);
            viewModel.createPost(title, description, price, selectedCategory, "testUser0", country, city);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle image selection if needed
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}