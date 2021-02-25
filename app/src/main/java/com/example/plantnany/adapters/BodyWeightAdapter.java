package com.example.plantnany.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private int pos;

    public BodyWeightAdapter(Context mContext, List<Integer> mList) {
        this.mContext = mContext;
        this.mList = mList;
        pos = SharedPreferencesManager.getInstance(mContext).getWeight();
    }

    @NonNull
    @Override
    public BodyWeightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_item_body_weight, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BodyWeightAdapter.ViewHolder holder, int position) {

        holder.weight.setText(String.valueOf(mList.get(position)));

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
                    String text = holder.weight.getText().toString();
                    SharedPreferencesManager.getInstance(mContext).setWeight(Integer.parseInt(text));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView weight;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weight = itemView.findViewById(R.id.tv_weight);

            checkBox = itemView.findViewById(R.id.checkbox);

        }
    }
}
