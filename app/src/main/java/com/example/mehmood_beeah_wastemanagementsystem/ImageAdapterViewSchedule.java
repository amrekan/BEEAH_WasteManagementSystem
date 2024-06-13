package com.example.mehmood_beeah_wastemanagementsystem;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapterViewSchedule extends RecyclerView.Adapter <ImageAdapterViewSchedule.ImageViewHolder>{
    private Context mContext;
    private List<Model> mUploads;

    public ImageAdapterViewSchedule(Context context, List<Model> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_view_schedule, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Model uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Alarm Name: "+uploadCurrent.getTitle());
        holder.textViewDate.setText("Date: "+uploadCurrent.getDate());
        holder.textViewTime.setText("Time:  "+uploadCurrent.getTime());

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewDate;
        public TextView textViewTime;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.schedule_name);
            textViewDate= itemView.findViewById(R.id.schedule_date);
            textViewTime= itemView.findViewById(R.id.schedule_time);

        }
    }
}
