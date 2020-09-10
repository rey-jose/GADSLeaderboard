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
import com.gaad.GADSLeaderboard.model.Skill_IQ;

import java.util.List;


public class AdapterSkillIQ extends RecyclerView.Adapter<AdapterSkillIQ.MyviewHolder> {

//    Context ctx;
    List<Skill_IQ> sList;

    public AdapterSkillIQ(List<Skill_IQ> sList) {
        this.sList = sList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AdapterSkillIQ.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_skill_id, parent, false);


        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSkillIQ.MyviewHolder holder, int position) {

        holder.sName.setText(sList.get(position).getName());
        holder.sScore.setText(sList.get(position).getScore());
        holder.sCountry.setText(sList.get(position).getCountry());
        Glide.with(holder.itemView.getContext()).load(sList.get(position).getBadgeUrl())
//                .apply(RequestOptions.centerCropTransform())
                .into(holder.sBadge);
    }

    @Override
    public int getItemCount() {
        assert sList != null;
        return sList.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {
        TextView sName, sScore, sCountry;
        ImageView sBadge;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

             sName = itemView.findViewById(R.id.name_skilliq_TV);
             sScore = itemView.findViewById(R.id.scrs_skilliq_TV);
             sCountry = itemView.findViewById(R.id.country_skilliq_TV);
             sBadge = itemView.findViewById(R.id.skill_iq_IMV);


        }
    }
}
