package com.example.plantnany.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.model.CreatureModel;
import com.example.plantnany.model.PlantModel;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {
    private Context mContext;
    private List<PlantModel> mList;

    public PlantAdapter(Context mContext, List<PlantModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public PlantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_item_creature, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlantAdapter.ViewHolder holder, int position) {


        holder.description.setText(mList.get(position).getDescription());
        holder.title.setText(mList.get(position).getTitle());
        holder.imageView.setImageResource(mList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);

        }
    }
}
