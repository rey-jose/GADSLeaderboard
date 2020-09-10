package com.gaad.GADSLeaderboard.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gaad.GADSLeaderboard.R;
import com.gaad.GADSLeaderboard.adapter.AdapterSkillIQ;
import com.gaad.GADSLeaderboard.model.Skill_IQ;
import com.gaad.GADSLeaderboard.util.ApiBuilder;
import com.gaad.GADSLeaderboard.util.ApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillIQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillIQFragment extends Fragment {

    private View sView;

    private RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SkillIQFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkillIQFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillIQFragment newInstance(String param1, String param2) {
        SkillIQFragment fragment = new SkillIQFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sView = inflater.inflate(R.layout.fragment_skill_i_q, container, false);



        recyclerView = sView.findViewById(R.id.skill_iq_recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();


        return sView;
    }

    private void loadJSON() {

        ApiService mService = ApiBuilder.buildService(ApiService.class);
        Call<List<Skill_IQ>> request = mService.getDataSkill();

        request.enqueue(new Callback<List<Skill_IQ>>() {
            @Override
            public void onResponse(Call<List<Skill_IQ>> request, Response<List<Skill_IQ>> response) {
                if(response.isSuccessful()){
                    recyclerView.setAdapter(new AdapterSkillIQ(response.body()));
                } else if(response.code() == 401) {
                    Toast.makeText(getActivity(), "Your session has expired", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Skill_IQ>> request, Throwable t) {
                if (t instanceof IOException){
                    Toast.makeText(getActivity(), "A connection error occured", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}