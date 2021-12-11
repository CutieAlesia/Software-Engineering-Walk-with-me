package com.walkwithme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.walkwithme.databinding.FragmentTopUsersBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class TopUsers extends Fragment {

    private FragmentTopUsersBinding binding;
    TextView textView;
    ImageView imageView;
    TableRow tRow;
    private static final String TAG = "TopUsers Fragment";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTopUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TableLayout tableLayout = (TableLayout) getView().findViewById(R.id.topUserTable);
        final TableLayout.LayoutParams params =
                new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
        final TableRow.LayoutParams rowParams =
                new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        getTopUser(tableLayout, rowParams);
    }

    public void getTopUser(TableLayout tableLayout, TableRow.LayoutParams rowParams) {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = MainActivity.url + "info/TopUser?key=" + MainActivity.apiKey;
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
                                        JSONArray topUsers = response.getJSONArray("topUsers");
                                        for (int i = 0; i < topUsers.length(); i++) {
                                            JSONObject topUser = topUsers.getJSONObject(i);
                                            loadUserInfo(tableLayout, rowParams, topUser);
                                        }
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

    public void loadUserInfo(
            TableLayout tableLayout, TableRow.LayoutParams rowParams, JSONObject jsonObject) {
        tRow = new TableRow(getContext());
        imageView = new ImageView(getContext());
        JSONObject avatarJson;
        String name;
        int rank;
        try {
            name = jsonObject.getString("username");
            rank = jsonObject.getInt("rank");
            avatarJson = new JSONObject(jsonObject.getString("avatar"));
            loadAvatar(avatarJson);
            tRow.addView(imageView, 350, 350);
            imageView.setId(rank);
            textView = new TextView(getContext());
            textView.setText(name);
            textView.setId(rank);
            tRow.addView(textView, rowParams);
            tableLayout.addView(tRow);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAvatar(JSONObject response) throws IOException, JSONException {

        String imageURL =
                "http://185.194.217.213:8080/resources/" + response.getString("image") + ".jpg";
        URL myUrl = new URL(imageURL);

        new DownloadImageTask(imageView).execute(imageURL);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
