package com.example.mehmood_beeah_wastemanagementsystem.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehmood_beeah_wastemanagementsystem.R;
import com.example.mehmood_beeah_wastemanagementsystem.UserComplaint;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ImageAdapterViewComplaint extends RecyclerView.Adapter <ImageAdapterViewComplaint.ImageViewHolder>{
    private Context mContext;
    private List<UserComplaint> mUploads;

    public ImageAdapterViewComplaint(Context context, List<UserComplaint> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_view_complaint, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserComplaint uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("User Name: "+uploadCurrent.getCompName());
        holder.textViewDetail.setText("Complaint: "+uploadCurrent.getCompDetail());
        Picasso.get()
                .load(uploadCurrent.getCompUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewDetail;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name);
            textViewDetail= itemView.findViewById(R.id.image_view_detail);
            imageView= itemView.findViewById(R.id.image_view_uploaded);


        }
    }
}
