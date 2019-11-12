package com.commuters.clicker.ui.upgrades;

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
import com.commuters.clicker.databinding.FragmentUpgradesBinding;

public class UpgradesFragment extends Fragment {

    private UpgradesViewModel upgradesViewModel;

    FragmentUpgradesBinding bindingUpgrades;

    Profile localprofile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        upgradesViewModel = new ViewModelProvider(this).get(UpgradesViewModel.class);
        upgradesViewModel.getLocalProfile().observe(this, new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                localprofile = profile;
                bindingUpgrades.setProfile(localprofile);
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
        upgradesViewModel.update(localprofile);

        Log.d("Frag", "I was killed");

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        bindingUpgrades = FragmentUpgradesBinding.inflate(inflater, container, false);

        View root = bindingUpgrades.getRoot();

        bindingUpgrades.clickUpgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localprofile.incrementClickStrength();
                upgradesViewModel.update(localprofile);
            }
        });

        bindingUpgrades.comboMultiplierUpgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localprofile.incrementComboMultiplier();
                upgradesViewModel.update(localprofile);
            }
        });

        return root;
    }

    public void update() {
        localprofile.setScore(localprofile.getScore() + 1);
    }

}