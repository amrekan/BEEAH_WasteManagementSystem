package com.example.mehmood_beeah_wastemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapterDeleteSchedule extends RecyclerView.Adapter <ImageAdapterDeleteSchedule.ImageViewHolder>{
    private Context mContext;
    private List<Model> mUploads;
    private OnItemClickListener mListener;

    public ImageAdapterDeleteSchedule(Context context, List<Model> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_delete_schedule, parent, false);
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

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public TextView textViewDate;
        public TextView textViewTime;
        public Button btn_delete;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.schedule_name1);
            textViewDate= itemView.findViewById(R.id.schedule_date1);
            textViewTime= itemView.findViewById(R.id.schedule_time1);
            btn_delete= itemView.findViewById(R.id.del1);

            btn_delete.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }


    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener= listener;
    }
}
