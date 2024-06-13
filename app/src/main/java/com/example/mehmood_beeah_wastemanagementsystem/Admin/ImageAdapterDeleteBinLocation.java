package com.example.mehmood_beeah_wastemanagementsystem.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehmood_beeah_wastemanagementsystem.ImageAdapterDeleteSchedule;
import com.example.mehmood_beeah_wastemanagementsystem.ImageAdapterViewBInLocation;
import com.example.mehmood_beeah_wastemanagementsystem.R;

import java.util.List;

public class ImageAdapterDeleteBinLocation extends RecyclerView.Adapter <ImageAdapterDeleteBinLocation.ImageViewHolder>{
    private Context mContext;
    private List<UserBinLocator> mUploads;

    private OnItemClickListener mListener;

    public ImageAdapterDeleteBinLocation(Context context, List<UserBinLocator> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_delete_bin_location, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserBinLocator uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Location Name: "+uploadCurrent.getBinName());
        holder.textViewType.setText("Bin Type: "+uploadCurrent.getBinType());
        holder.textViewAddress.setText("Location Address:  "+uploadCurrent.getBinAddress());

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public TextView textViewType;
        public TextView textViewAddress;
        public Button btn_delete;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.b_name);
            textViewType= itemView.findViewById(R.id.b_type);
            textViewAddress= itemView.findViewById(R.id.b_address);
            btn_delete= itemView.findViewById(R.id.del_bin);

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
