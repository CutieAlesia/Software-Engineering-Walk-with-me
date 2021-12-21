package com.walkwithme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.walkwithme.databinding.FragmentPreferencesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass. Use the {@link PreferencesFragment} factory method to create
 * an instance of this fragment.
 */
public class PreferencesFragment extends Fragment {
    private static final String TAG = "Preferences";
    private FragmentPreferencesBinding binding;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> preferences;

    public PreferencesFragment() {}

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPreferencesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadPreferences();
        binding.savePreferences.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        savePreferences();
                    }
                });
    }

    public void loadPreferences() {
        preferences = new ArrayList<String>();

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url =
                MainActivity.url
                        + "info/getUser?key="
                        + MainActivity.apiKey
                        + "&id="
                        + MainActivity.getLoggedInUserId();
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response != null) {
                                    try {
                                        JSONObject preferencesJSON =
                                                new JSONObject(response.getString("pref"));
                                        JSONArray keys = preferencesJSON.names();
                                        for (int i = 0; i < keys.length(); i++) {
                                            String key = keys.getString(i);
                                            preferences.add(key);
                                        }
                                        loadIsPreference(preferencesJSON);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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

        queue.add(jsonObjectRequest);
    }

    private void loadIsPreference(JSONObject response) throws JSONException {
        listView = getView().findViewById(R.id.preferencesChoiceList);
        adapter =
                new ArrayAdapter<String>(
                        getContext(),
                        android.R.layout.simple_list_item_multiple_choice,
                        preferences);
        listView.setAdapter(adapter);
        JSONArray keys = response.names();
        for (int i = 0; i < keys.length(); i++) {
            String key = keys.getString(i);
            if (response.getInt(key) == 1) {
                listView.setItemChecked(i, true);
            }
        }
    }

    private void savePreferences() {
        listView = getView().findViewById(R.id.preferencesChoiceList);
        String preferencesUrl =
                MainActivity.url
                        + "info/changePref?key="
                        + MainActivity.apiKey
                        + "&id="
                        + MainActivity.getLoggedInUserId();
        for (int i = 0; i < listView.getCount(); i++) {
            preferencesUrl += "&" + listView.getItemAtPosition(i).toString() + "=";
            if (listView.isItemChecked(i)) {
                preferencesUrl += "1";
            } else {
                preferencesUrl += "2";
            }
        }

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.POST,
                        preferencesUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("200")) {
                                    refresh();
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
    }

    private void refresh() {
        NavHostFragment.findNavController(PreferencesFragment.this)
                .navigate(PreferencesFragmentDirections.actionPreferencesFragmentSelf());
    }
}
