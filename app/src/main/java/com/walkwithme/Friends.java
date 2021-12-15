package com.walkwithme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.walkwithme.databinding.FragmentFriendsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Friends extends Fragment {

    private FragmentFriendsBinding binding;
    ArrayList<String> names;
    ArrayList<String> avatars;
    ArrayList<Integer> ids;
    private static final String TAG = "Friendlist Fragment";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFriendsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        names = new ArrayList<>();
        avatars = new ArrayList<>();
        ids = new ArrayList<>();
        getFriends();
    }

    public void getFriends() {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = MainActivity.url + "relations/getMatches?key=" + MainActivity.apiKey + "&id=" +MainActivity.getLoggedInUserId();
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response != null) {
                                    try {
                                        JSONArray friends = new JSONArray(response);

                                        for (int i = 0; i < friends.length(); i++) {
                                            JSONObject friend = friends.getJSONObject(i);
                                            loadUserInfo(friend);
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

        queue.add(stringRequest);





    }

    public void updateView(){
        ListView lv = (ListView) getView().findViewById(R.id.friend_listview);
        FriendListAdapter adapter = new FriendListAdapter(getActivity(), names, avatars, ids);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriendsDirections.ActionFriendsToProfile action = FriendsDirections.actionFriendsToProfile();

                int targetId = (int) view.getTag();
                action.setId(targetId);
                NavHostFragment.findNavController(Friends.this)
                        .navigate(action);
            }
        });
    }

    public void loadUserInfo(@NonNull JSONObject jsonObject) {
        String avatarURL;
        String name;
        try {
        int friendId = jsonObject.getInt("second");
        ids.add(friendId);


            RequestQueue queue = Volley.newRequestQueue(getContext());
            String url =
                    MainActivity.url + "info/getUser?key=" + MainActivity.apiKey + "&id=" + friendId;
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
                                            String name = response.getString("username");
                                            JSONObject avatarJson =
                                                    new JSONObject(response.getString("avatar"));
                                            String avatarURL =
                                                    "http://185.194.217.213:8080/resources/" + avatarJson.getString("image") + ".jpg";;

                                            names.add(name);
                                            avatars.add(avatarURL);
                                            updateView();

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
