package com.ariguter.marketdemosdk.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ariguter.marketdemosdk.databinding.FragmentProfileBinding;
import com.ariguter.marketdemosdk.viewModel.ProfileViewModel;
import com.ariguter.marketdemosdk.views.adapter.PostAdapter;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private final ProfileViewModel  viewModel= new ProfileViewModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.profileEmail.append(viewModel.getEmail());
        binding.profileUserName.append(viewModel.getUsername());
        binding.profilePhoneNumber.append(viewModel.getPhoneNumber());

        PostAdapter adapter = new PostAdapter(getChildFragmentManager());
        binding.profileRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.profileRecyclerView.setAdapter(adapter);

        viewModel.getUserPosts().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}