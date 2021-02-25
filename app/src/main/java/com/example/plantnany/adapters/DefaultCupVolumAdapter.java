package com.example.plantnany.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.sharedpref.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class DefaultCupVolumAdapter extends RecyclerView.Adapter<DefaultCupVolumAdapter.ViewHolder> {
    private Context mContext;
    private List<Integer> mList;
    private  int pos;


    public DefaultCupVolumAdapter(Context mContext, List<Integer> mList) {
        this.mContext = mContext;
        this.mList = mList;

        pos = SharedPreferencesManager.getInstance(mContext).getDefaultCupVolume();
    }

    @NonNull
    @Override
    public DefaultCupVolumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_item_cup_volume, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultCupVolumAdapter.ViewHolder holder, int position) {


        holder.cupVolume.setText(String.valueOf(mList.get(position)));

        if (mList.get(position) == pos) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = mList.get(position);
                notifyDataSetChanged();

            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    String text = holder.cupVolume.getText().toString();
                    SharedPreferencesManager.getInstance(mContext).setDefaultCupVolume(Integer.parseInt(text));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cupVolume;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cupVolume = itemView.findViewById(R.id.tv_weight);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
