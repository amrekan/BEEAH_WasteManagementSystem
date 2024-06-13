package com.example.mehmood_beeah_wastemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehmood_beeah_wastemanagementsystem.Admin.UserBinLocator;

import java.util.List;

public class ImageAdapterViewBInLocation extends RecyclerView.Adapter <ImageAdapterViewBInLocation.ImageViewHolder>{
    private Context mContext;
    private List<UserBinLocator> mUploads;

    public ImageAdapterViewBInLocation(Context context, List<UserBinLocator> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_view_bin_location, parent, false);
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

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewType;
        public TextView textViewAddress;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.b_name1);
            textViewType= itemView.findViewById(R.id.b_type1);
            textViewAddress= itemView.findViewById(R.id.b_address1);

        }
    }
}
