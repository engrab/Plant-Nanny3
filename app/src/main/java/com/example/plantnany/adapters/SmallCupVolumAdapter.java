package com.example.plantnany.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.sharedpref.SharedPreferencesManager;

import java.util.List;

public class SmallCupVolumAdapter extends RecyclerView.Adapter<SmallCupVolumAdapter.ViewHolder> {
    private Context mContext;
    private List<Integer> mList;

    public SmallCupVolumAdapter(Context mContext, List<Integer> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public SmallCupVolumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_item_cup_volume, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SmallCupVolumAdapter.ViewHolder holder, int position) {


        holder.cupVolume.setText(String.valueOf(mList.get(position)));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cupVolume.setTextSize(40.0f);
                String text = holder.cupVolume.getText().toString();
                SharedPreferencesManager.getInstance(mContext).setSmallCup(Integer.parseInt(text));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cupVolume;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cupVolume = itemView.findViewById(R.id.tv_weight);
        }
    }
}
