package com.david.clicker.ui.highscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.david.clicker.data.entities.Profile;
import com.david.clicker.ui.profile.ProfileActivity;
import com.david.clicker.ui.profile.ProfileViewModel;
import com.david.clicker.R;

import java.util.List;

public class HighscoreFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_highscore, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_profile);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setHasFixedSize(true);

        // Extra
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Extra

        final HighscoreAdapter highscoreAdapter = new HighscoreAdapter();
        recyclerView.setAdapter(highscoreAdapter);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.getAllProfiles().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profiles) {
                highscoreAdapter.submitList(profiles);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Profile profile = highscoreAdapter.getProfileAt(position);
                String message;

                switch (direction) {
                    case (4):
                        profileViewModel.delete(profile);
                        message = profile.getUsername() + " has been deleted by swiping left";
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        return;
                    case (8):
                        profileViewModel.delete(profile);
                        message = profile.getUsername() + " has been deleted by swiping right";
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        return;
                }
            }
        }).attachToRecyclerView(recyclerView);

        highscoreAdapter.setOnItemClickListener(new HighscoreAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Profile profile) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_SCORE, profile.getScore());
                intent.putExtra(ProfileActivity.EXTRA_USERNAME, profile.getUsername());
                intent.putExtra(ProfileActivity.EXTRA_EMAIL, profile.getEmail());
                intent.putExtra(ProfileActivity.EXTRA_PASSWORD, profile.getPassword());
                startActivity(intent);
            }
        });

        return root;
    }
}