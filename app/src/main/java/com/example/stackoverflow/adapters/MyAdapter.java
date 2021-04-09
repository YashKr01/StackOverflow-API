package com.example.stackoverflow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.stackoverflow.R;
import com.example.stackoverflow.model.Items;
import com.example.stackoverflow.model.MyResponse;
import com.example.stackoverflow.model.Owner;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Items> responseList;

    public MyAdapter(Context context, List<Items> responseList) {
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Items items = responseList.get(position);
        holder.userName.setText("Name : " + items.getOwner().getDisplayName());
        holder.userType.setText("Type : " + items.getOwner().getUserType());
        holder.userId.setText("Used id : " + String.valueOf(items.getOwner().getUserId()));

        Glide.with(context).load(items.getOwner().getProfileImage())
                .into(holder.userImage);

    }

    @Override
    public int getItemCount() {
        return responseList.size();
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

    public static DiffUtil.ItemCallback<Items> itemCallback = new DiffUtil.ItemCallback<Items>() {
        @Override
        public boolean areItemsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return oldItem.getOwner().getUserId().equals(newItem.getOwner().getUserId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return oldItem.equals(newItem);
        }
    };

}
