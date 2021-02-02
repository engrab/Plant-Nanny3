package com.example.plantnany.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.model.TimeModel;

import java.util.List;

public class CustomTimeAdapter extends RecyclerView.Adapter<CustomTimeAdapter.ViewHolder> {
    private Context mContext;
    private List<TimeModel> mList;

    public CustomTimeAdapter(Context mContext, List<TimeModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CustomTimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_item_cutom_time, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomTimeAdapter.ViewHolder holder, int position) {



        holder.mint.setText(mList.get(position).getMint());
        holder.hour.setText(mList.get(position).getHour());
        holder.sw_time.setChecked(mList.get(position).isEnable());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SwitchCompat sw_time;
        TextView mint;
        TextView hour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sw_time = itemView.findViewById(R.id.radio_time);
            mint = itemView.findViewById(R.id.tv_min);
            hour = itemView.findViewById(R.id.tv_hour);


        }
    }
    public void newAddeddata(int hour, int mint){
        mList.add(new TimeModel(hour, mint, true));
        notifyDataSetChanged();
    }
}
