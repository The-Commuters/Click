package com.david.clicker.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.david.clicker.R;
import com.david.clicker.data.entities.Profile;
import com.david.clicker.databinding.FragmentDashboardBinding;
import com.david.clicker.ui.profile.ProfileViewModel;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    FragmentDashboardBinding bindingClicker;

    private ProfileViewModel profileViewModel;


    Profile profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        profile.setScore(1);


        bindingClicker.setProfile(profile);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
      /*  dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


        bindingClicker = FragmentDashboardBinding.inflate(inflater, container, false);

        View root = bindingClicker.getRoot();
        //bindingClicker.getLifecycleOwner();

         profile = new Profile("mafen", "mark@mark.com", "122232" );
         bindingClicker.setProfile(profile);
         profile.setScore(1);


         Log.d("hi", ""+ profile.getScore());


        return root;
    }


}