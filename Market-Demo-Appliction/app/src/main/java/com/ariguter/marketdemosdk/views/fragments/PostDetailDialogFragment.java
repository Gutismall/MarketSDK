package com.ariguter.marketdemosdk.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ariguter.marketdemosdk.R;
import com.ariguter.marketdemosdk.databinding.FragmentPostDetailDialogBinding;
import com.ariguter.marketsdk.DTO.PostDTO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PostDetailDialogFragment extends DialogFragment {
    private static final String ARG_POST = "post";
    private FragmentPostDetailDialogBinding binding;

    public static PostDetailDialogFragment newInstance(PostDTO post) {
        PostDetailDialogFragment fragment = new PostDetailDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_POST,post);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostDetailDialogBinding.inflate(inflater, container, false);

        assert getArguments() != null;
        PostDTO post = (PostDTO) getArguments().getSerializable(ARG_POST);

        Log.d("PostDetailDialogFragment", "Post: " + post.getCountry());


        binding.postDialogFragmentTitle.setText(post.getTitle());
        binding.postDialogFragmentDescription.setText(post.getDescription());
        binding.postDialogFragmentPrice.setText(String.valueOf(post.getPrice()));
        binding.postDialogFragmentCountry.setText(post.getCountry());
        binding.postDialogFragmentCity.setText(post.getCity());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String formattedDate = sdf.format(post.getCreatedAt());
        binding.postDialogFragmentDate.setText(formattedDate);


        binding.postDialogFragmentCloseButton.setOnClickListener(v -> dismiss());

        return binding.getRoot();
    }
}