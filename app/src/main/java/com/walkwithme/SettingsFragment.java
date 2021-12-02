package com.walkwithme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walkwithme.databinding.FragmentFirstBinding;
import com.walkwithme.databinding.FragmentSecondBinding;
import com.walkwithme.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ManageProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.action_settingsFragment_to_FirstFragment);
            }
        });

        binding.PreferencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.action_settingsFragment_to_FirstFragment);
            }
        });

        binding.ManagePicturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.action_settingsFragment_to_FirstFragment);
            }
        });

        binding.DeleteProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.action_settingsFragment_to_FirstFragment);
            }
        });
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    }
