package com.david.clicker.ui.highscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.david.clicker.R;
import com.david.clicker.data.entities.Profile;
import com.david.clicker.ui.profile.ProfileActivity;

import java.util.List;

public class HighscoreFragment extends Fragment {

    private HighscoreViewModel highscoreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_highscore, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_profile);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final HighscoreAdapter highscoreAdapter = new HighscoreAdapter();
        recyclerView.setAdapter(highscoreAdapter);

        highscoreViewModel = new ViewModelProvider(this).get(HighscoreViewModel.class);
        highscoreViewModel.getHighscorers().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> highscorers) {
                highscoreAdapter.submitList(highscorers);
            }
        });

        highscoreAdapter.setOnItemClickListener(new HighscoreAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Profile profile) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_SCORE, profile.getScore());
                intent.putExtra(ProfileActivity.EXTRA_USERNAME, profile.getUsername());
                intent.putExtra(ProfileActivity.EXTRA_EMAIL, profile.getEmail());
                startActivity(intent);
            }
        });

        return root;
    }
}