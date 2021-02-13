package com.example.plantnany.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.model.CreatureModel;
import com.example.plantnany.sharedpref.SharedPreferencesManager;

import java.util.List;

public class BodyWeightAdapter extends RecyclerView.Adapter<BodyWeightAdapter.ViewHolder> {
    private Context mContext;
    private List<Integer> mList;

    public BodyWeightAdapter(Context mContext, List<Integer> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public BodyWeightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_item_body_weight, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BodyWeightAdapter.ViewHolder holder, int position) {



        holder.weight.setText(String.valueOf(mList.get(position)));
        holder.weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = holder.weight.getText().toString();
                SharedPreferencesManager.getInstance(mContext).setWeight(Integer.parseInt(weight));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        TextView weight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.rad_weight);
            weight = itemView.findViewById(R.id.tv_weight);


        }
    }
}
