package com.ariguter.marketdemosdk.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ariguter.marketdemosdk.viewModel.MarketViewModel;

import com.ariguter.marketdemosdk.databinding.FragmentMarketBinding;

public class MarketFragment extends Fragment {
    private FragmentMarketBinding binding;
    private MarketViewModel viewModel;
    private PostAdapter adapter;

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

        adapter = new PostAdapter();
        binding.marketRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.marketRecyclerView.setAdapter(adapter);

        viewModel.getPosts().observe(getViewLifecycleOwner(), posts -> {
            adapter.submitList(posts);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}