package com.gaad.GADSLeaderboard.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gaad.GADSLeaderboard.R;
import com.gaad.GADSLeaderboard.model.Learning;

import java.util.List;


public class AdapterLearning extends RecyclerView.Adapter<AdapterLearning.MyviewHolder> {

//    Context ctx;
    List<Learning> lList;

    public AdapterLearning(List<Learning> lList) {
        this.lList = lList;

        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public AdapterLearning.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_leaders, parent, false);


        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLearning.MyviewHolder holder, int position) {

        holder.name.setText(lList.get(position).getName());
        holder.hours.setText(lList.get(position).getHours());
        holder.country.setText(lList.get(position).getCountry());
        Glide.with(holder.itemView.getContext()).load(lList.get(position).getBadgeUrl()).apply(RequestOptions.centerCropTransform()).into(holder.badge);
    }

    @Override
    public int getItemCount() {
        assert lList != null;
        return lList.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {
        TextView name, hours, country;
        ImageView badge;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

                name = itemView.findViewById(R.id.name_leader_TV);
            hours= itemView.findViewById(R.id.hrs_leader_TV);
            country = itemView.findViewById(R.id.country_leanr_TV);
                badge = itemView.findViewById(R.id.leader_IMV);


        }
    }
}
