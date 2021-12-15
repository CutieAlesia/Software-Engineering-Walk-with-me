package com.walkwithme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.walkwithme.databinding.FragmentSettingsBinding;



public class SettingsFragment extends Fragment {

    private static final String TAG = "Settings";
    private FragmentSettingsBinding binding;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ManageProfileButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SettingsFragmentDirections.ActionSettingsFragmentToProfile action =
                                SettingsFragmentDirections.actionSettingsFragmentToProfile();
                        action.setId(MainActivity.getLoggedInUserId());
                        NavHostFragment.findNavController(SettingsFragment.this).navigate(action);
                    }
                });

        binding.PreferencesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavHostFragment.findNavController(SettingsFragment.this)
                                .navigate(R.id.action_settingsFragment_to_FirstFragment);
                    }
                });

        binding.ManagePicturesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavHostFragment.findNavController(SettingsFragment.this)
                                .navigate(R.id.action_settingsFragment_to_FirstFragment);
                    }
                });

        binding.DeleteProfileButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Delete Profile?")
                                .setMessage("Do you really want to delete your Profile?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(
                                        android.R.string.yes,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int whichButton) {
                                                Toast.makeText(
                                                                getContext(),
                                                                "Deleting Profile...",
                                                                Toast.LENGTH_SHORT)
                                                        .show();
                                                deleteProfile();
                                            }
                                        })
                                .setNegativeButton(
                                        android.R.string.no,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int whichButton) {}
                                        })
                                .show();
                    }
                });
    }

    public void deleteProfile() {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url =
                MainActivity.url
                        + "user/deleteUser?key="
                        + MainActivity.apiKey
                        + "&id="
                        + MainActivity.getLoggedInUserId();
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("200")) {
                                    MainActivity.setLoggedInUserId(0);
                                }
                            }
                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                                // hide the progress dialog
                            }
                        });

        queue.add(stringRequest);
        NavHostFragment.findNavController(SettingsFragment.this)
                .navigate(R.id.action_settingsFragment_to_loginFragment);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
