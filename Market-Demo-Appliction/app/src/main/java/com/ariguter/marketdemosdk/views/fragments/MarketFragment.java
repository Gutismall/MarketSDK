package com.ariguter.marketdemosdk.views.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ariguter.marketdemosdk.R;
import com.ariguter.marketdemosdk.viewModel.MarketViewModel;

import com.ariguter.marketdemosdk.databinding.FragmentMarketBinding;
import com.ariguter.marketdemosdk.views.adapter.PostAdapter;

import java.util.List;

public class MarketFragment extends Fragment {
    private FragmentMarketBinding binding;
    private MarketViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMarketBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MarketViewModel.class);

        // set up of the recycler view of the posts
        PostAdapter adapter = new PostAdapter(getChildFragmentManager());
        binding.marketRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.marketRecyclerView.setAdapter(adapter);
        viewModel.getAllMarketPosts().observe(getViewLifecycleOwner(), adapter::submitList);

        // set up of the auto complete text view for categories
        viewModel.getAllCategoriesNames().observe(getViewLifecycleOwner(), categories -> {
            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, new java.util.ArrayList<>(categories.values()));
            binding.autoCompleteTextView.setAdapter(categoryAdapter);
});
        binding.autoCompleteTextView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedCategory = (String) parent.getItemAtPosition(position);
            // Do something with selectedCategory
            viewModel.getPostsByCategory(selectedCategory).observe(getViewLifecycleOwner(), adapter::submitList);
        });
        //create a new post
        binding.floatingActionButton.setOnClickListener(v -> {
            CreateNewPostDialogFragment dialog = new CreateNewPostDialogFragment();
            dialog.show(getChildFragmentManager(), "CreatePostDialog");
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}