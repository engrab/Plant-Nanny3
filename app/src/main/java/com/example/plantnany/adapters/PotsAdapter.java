package com.example.plantnany.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.model.CreatureModel;
import com.example.plantnany.model.PotModel;
import com.example.plantnany.sharedpref.SharedPreferencesManager;

import java.util.List;

public class PotsAdapter extends RecyclerView.Adapter<PotsAdapter.ViewHolder> {
    private Context mContext;
    private List<PotModel> mList;
    private static final int DIFFER_CLOVER = 30;

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
        holder.setPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clover = SharedPreferencesManager.getInstance(mContext).getClover();
                if (clover >= DIFFER_CLOVER){
                    SharedPreferencesManager.getInstance(mContext).setClover(clover-DIFFER_CLOVER);
                    SharedPreferencesManager.getInstance(mContext).setPot(position+1);
                }else {
                    Toast.makeText(mContext, "Please Collect Clover", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvColor;
        LinearLayout setPot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_pot);
            tvColor = itemView.findViewById(R.id.tv_color);
            setPot = itemView.findViewById(R.id.ll_set_pot);

        }
    }
}
