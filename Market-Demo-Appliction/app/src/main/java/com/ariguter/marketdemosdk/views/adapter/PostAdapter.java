package com.ariguter.marketdemosdk.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariguter.marketdemosdk.R;
import com.ariguter.marketdemosdk.views.fragments.PostDetailDialogFragment;
import com.ariguter.marketsdk.DTO.PostDTO;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<PostDTO> posts = new ArrayList<>();
    private FragmentManager fragmentManager;

    public PostAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void submitList(List<PostDTO> newPosts) {
        posts.clear();
        if (newPosts != null) {
            posts.addAll(newPosts);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostDTO post = posts.get(position);

//        holder.imageView.setImageResource(R.drawable.man);
        holder.titleTextView.setText(post.getTitle());
        holder.priceTextView.setText(String.valueOf(post.getPrice()));

        holder.itemView.setOnClickListener(v -> {
            PostDetailDialogFragment dialogFragment = PostDetailDialogFragment.newInstance(post);
            dialogFragment.show(fragmentManager, "PostDetailDialog");
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        TextView priceTextView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.postTitle);
            imageView = itemView.findViewById(R.id.postImageView);
            priceTextView = itemView.findViewById(R.id.postPrice);
        }
    }
}