package com.commuters.clicker.ui.game;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.databinding.FragmentGameBinding;

public class GameFragment extends Fragment {

    FragmentGameBinding bindingClicker;
    Profile localprofile;
    private GameViewModel gameViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.getLocalProfile().observe(this, new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                localprofile = profile;
                bindingClicker.setProfile(localprofile);

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        gameViewModel.update(localprofile);

        Log.d("Frag", "I was killed");

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        bindingClicker = FragmentGameBinding.inflate(inflater, container, false);

        View root = bindingClicker.getRoot();


        bindingClicker.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        return root;
    }

    public void update() {
        localprofile.setScore(localprofile.getScore() + localprofile.getClick_strength());
        gameViewModel.update(localprofile);
    }

}