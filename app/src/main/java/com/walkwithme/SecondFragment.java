package com.walkwithme;

import static java.lang.Math.max;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.walkwithme.databinding.FragmentSecondBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private static final String TAG = "Swipe Fragment";
    private static final int IO_BUFFER_SIZE = Integer.MAX_VALUE;
    private FragmentSecondBinding binding;
    ImageView previewImages;




    ArrayList<String> values = new ArrayList<>();
    ArrayList<String> imagesUrls = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();
    ImageView avatarView;
    Integer uId;
    TextView userNameView;
    TextView userInfoView;
    TextView preferencesView;


    public SecondFragment() throws IOException {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {




        super.onViewCreated(view, savedInstanceState);

        avatarView  = (ImageView) getView().findViewById(R.id.AvatarField);

        updateViewData();
        avatarView.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            public void onSwipeRight() {
                updateViewData();
            }
            public void onSwipeLeft() {
                updateViewData();
            }

        });
    }

    public void updateViewData(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = MainActivity.url+"info/getUser?key="+MainActivity.apiKey+"&id="+1;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                if(response != null){
                    try {
                        uId = response.getInt("userid");
                        String name = response.getString("username");
                        String userInfo = response.getString("bio");
                        String preferences = response.getString("gender") + "\n" + response.getString("race");

                        userInfoView = (TextView) getView().findViewById(R.id.UserInfoField);
                        userInfoView.setText(userInfo);
                        preferencesView = (TextView) getView().findViewById(R.id.PreferencesField);
                        preferencesView.setText(preferences);
                        userNameView = (TextView) getView().findViewById(R.id.UserNameField);
                        userNameView.setText(name);

                        JSONObject avatarJson = new JSONObject(response.getString("avatar"));
                        loadAvatar(avatarJson);
                        JSONObject imageJson = new JSONObject(response.getString("images"));
                        loadPreviewImages(imageJson);

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });


        queue.add(jsonObjectRequest);


        ListView lv = (ListView) getView().findViewById(R.id.previewList);
        ImageListAdapter adapter = new ImageListAdapter(getActivity(), values, imagesUrls);
        lv.setAdapter(adapter);
    }

    public void loadPreviewImages(JSONObject response) throws JSONException {
        values.clear();
        imagesUrls.clear();

        String imageURL = "http://185.194.217.213:8080/resources/";

        JSONArray keys = response.names();
        for(int i = 0; i < keys.length(); i++) {
            String key = keys.getString(i);

            values.add("");
            imagesUrls.add(imageURL + response.getString(key)+".jpg");
        }

        ListView lv = (ListView) getView().findViewById(R.id.previewList);
        ImageListAdapter adapter = new ImageListAdapter(getActivity(), values, imagesUrls);
        lv.setAdapter(adapter);



    }

    public void loadAvatar(JSONObject response) throws IOException, JSONException {


        String imageURL =  "http://185.194.217.213:8080/resources/"+response.getString("image")+".jpg";
        URL myUrl = new URL(imageURL);

        new DownloadImageTask(avatarView).execute(imageURL);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}