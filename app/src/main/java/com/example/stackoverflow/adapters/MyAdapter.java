package com.example.stackoverflow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.stackoverflow.R;
import com.example.stackoverflow.model.Items;
import com.example.stackoverflow.model.MyResponse;
import com.example.stackoverflow.model.Owner;

import java.util.List;

public class MyAdapter extends ListAdapter<Items, MyAdapter.MyViewHolder> {

    private Context context;

    public MyAdapter(@NonNull DiffUtil.ItemCallback<Items> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false
                ));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Items current = getItem(position);
        holder.userName.setText("Name : " + current.getOwner().getDisplayName());
        holder.userType.setText("Type : " + current.getOwner().getUserType());
        holder.userId.setText("User id : " + current.getOwner().getUserId());

        Glide.with(context).load(current.getOwner().getProfileImage())
                .into(holder.userImage);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView userName, userType, userId;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.user_image);
            userName = itemView.findViewById(R.id.user_name);
            userType = itemView.findViewById(R.id.user_type);
            userId = itemView.findViewById(R.id.user_id);

        }
    }

}
