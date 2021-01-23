package com.example.plantnany.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.model.CreatureModel;
import com.example.plantnany.model.PotModel;

import java.util.List;

public class PotsAdapter extends RecyclerView.Adapter<PotsAdapter.ViewHolder> {
    private Context mContext;
    private List<PotModel> mList;

    public PotsAdapter(Context mContext, List<PotModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public PotsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_item_pots, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PotsAdapter.ViewHolder holder, int position) {



        holder.tvColor.setText(mList.get(position).getTitle());
        holder.imageView.setImageResource(mList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvColor;
        LinearLayout llFlower;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_pot);
            tvColor = itemView.findViewById(R.id.tv_color);
            llFlower = itemView.findViewById(R.id.ll_reward_flower);

        }
    }
}
