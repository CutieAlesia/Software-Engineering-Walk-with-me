package com.walkwithme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.walkwithme.databinding.FragmentProfileBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass. Use the {@link Profile} factory method to create an instance
 * of this fragment.
 */
public class Profile extends Fragment {

    private static final String TAG = "Swipe Fragment";
    private static final int IO_BUFFER_SIZE = Integer.MAX_VALUE;
    private FragmentProfileBinding binding;
    static final int VOID = 0;
    static final int LIKE = 1;
    ImageView previewImages;
    ArrayList<String> values = new ArrayList<>();
    ArrayList<String> imagesUrls = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();
    ImageView avatarView;

    public static int getuId() {
        return uId;
    }

    public static void setuId(int uId) {
        Profile.uId = uId;
    }

    static int uId;
    EditText userNameView;
    EditText userInfoView;
    EditText preferencesView;

    public Profile() {}

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userNameView = (EditText) getView().findViewById(R.id.UserNameField);
        userInfoView = (EditText) getView().findViewById(R.id.UserInfoField);
        preferencesView = (EditText) getView().findViewById(R.id.PreferencesField);
        uId = ProfileArgs.fromBundle(getArguments()).getId();

        avatarView = (ImageView) getView().findViewById(R.id.AvatarField);

        userNameView.setFocusable(false);
        userNameView.setFocusableInTouchMode(false);
        userNameView.setClickable(false);
        preferencesView.setFocusable(false);
        preferencesView.setFocusableInTouchMode(false);
        preferencesView.setClickable(false);
        updateViewData();
        if (MainActivity.getLoggedInUserId() == getuId()) {
            binding.save.setVisibility(View.VISIBLE);
            binding.remove.setVisibility(View.GONE);
            binding.chat.setVisibility(View.GONE);
            binding.save.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            saveChanges();
                        }
                    });
        } else {
            userInfoView.setFocusable(false);
            userInfoView.setFocusableInTouchMode(false);
            userInfoView.setClickable(false);

            if (isFriend()) {
                binding.remove.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Remove friend?")
                                        .setMessage("Do you really want to remove this friend?")
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton(
                                                android.R.string.yes,
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(
                                                            DialogInterface dialog,
                                                            int whichButton) {
                                                        Toast.makeText(
                                                                        getContext(),
                                                                        "Removing Friend",
                                                                        Toast.LENGTH_SHORT)
                                                                .show();
                                                        removeAddFriend(VOID);
                                                    }
                                                })
                                        .setNegativeButton(
                                                android.R.string.no,
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(
                                                            DialogInterface dialog,
                                                            int whichButton) {}
                                                })
                                        .show();
                            }
                        });

            } else {
                binding.remove.setText("Add");
                binding.remove.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Add friend?")
                                        .setMessage("Do you really want to add this person?")
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton(
                                                android.R.string.yes,
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(
                                                            DialogInterface dialog,
                                                            int whichButton) {
                                                        Toast.makeText(
                                                                        getContext(),
                                                                        "Adding Friend",
                                                                        Toast.LENGTH_SHORT)
                                                                .show();
                                                        removeAddFriend(LIKE);
                                                    }
                                                })
                                        .setNegativeButton(
                                                android.R.string.no,
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(
                                                            DialogInterface dialog,
                                                            int whichButton) {}
                                                })
                                        .show();
                            }
                        });
            }
            binding.chat.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Not implemented yet.")
                                    .setMessage("This functionality is not yet implemented.")
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setPositiveButton(
                                            android.R.string.yes,
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(
                                                        DialogInterface dialog, int whichButton) {}
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
    }

    private boolean isFriend() {
        final boolean[] friends = {false};
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url =
                MainActivity.url
                        + "relations/getRelation?key="
                        + MainActivity.apiKey
                        + "&id="
                        + MainActivity.getLoggedInUserId()
                        + "&id2="
                        + getuId();
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
                                        int liked = response.getInt("liked");
                                        if (liked == 1) {
                                            friends[0] = true;
                                        } else {
                                            friends[0] = false;
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

        return true;
    }

    private void removeAddFriend(int change) {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url =
                MainActivity.url
                        + "relations/changeLike?key="
                        + MainActivity.apiKey
                        + "&id="
                        + MainActivity.getLoggedInUserId()
                        + "&id2="
                        + getuId()
                        + "&like="
                        + change;
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("200")) {
                                    Fragment frg =
                                            getFragmentManager().findFragmentByTag("profile");
                                    getFragmentManager()
                                            .beginTransaction()
                                            .detach(frg)
                                            .attach(frg)
                                            .commit();
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

    private void saveChanges() {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url =
                MainActivity.url
                        + "info/changeBio?key="
                        + MainActivity.apiKey
                        + "&id="
                        + MainActivity.getLoggedInUserId()
                        + "&newBio="
                        + userInfoView.getText();
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("200")) {
                                    Fragment frg =
                                            getFragmentManager().findFragmentByTag("profile");
                                    getFragmentManager()
                                            .beginTransaction()
                                            .detach(frg)
                                            .attach(frg)
                                            .commit();
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

    public void updateViewData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url =
                MainActivity.url + "info/getUser?key=" + MainActivity.apiKey + "&id=" + getuId();
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
                                        String userInfo = response.getString("bio");
                                        String preferences =
                                                "Gender: "
                                                        + response.getString("gender")
                                                        + "\n"
                                                        + "Race: "
                                                        + response.getString("race")
                                                        + "\n"
                                                        + "Height: "
                                                        + response.getInt("height")
                                                        + "\n"
                                                        + "Weight: "
                                                        + response.getInt("weight")
                                                        + "\n"
                                                        + "Is Friendly: "
                                                        + response.getInt("friendly");

                                        userInfoView.setText(userInfo);

                                        preferencesView.setText(preferences);

                                        userNameView.setText(name);

                                        JSONObject avatarJson =
                                                new JSONObject(response.getString("avatar"));
                                        loadAvatar(avatarJson);
                                        JSONObject imageJson =
                                                new JSONObject(response.getString("images"));
                                        loadPreviewImages(imageJson);

                                    } catch (JSONException | IOException e) {
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
        ListView lv = (ListView) getView().findViewById(R.id.previewList);
        ImageListAdapter adapter = new ImageListAdapter(getActivity(), values, imagesUrls);
        lv.setAdapter(adapter);
    }

    public void loadPreviewImages(JSONObject response) throws JSONException {
        values.clear();
        imagesUrls.clear();

        String imageURL = "http://185.194.217.213:8080/resources/";

        JSONArray keys = response.names();
        for (int i = 0; i < keys.length(); i++) {
            String key = keys.getString(i);

            values.add("");
            imagesUrls.add(imageURL + response.getString(key) + ".jpg");
        }

        ListView lv = (ListView) getView().findViewById(R.id.previewList);
        ImageListAdapter adapter = new ImageListAdapter(getActivity(), values, imagesUrls);
        lv.setAdapter(adapter);
    }

    public void loadAvatar(JSONObject response) throws IOException, JSONException {

        String imageURL =
                "http://185.194.217.213:8080/resources/" + response.getString("image") + ".jpg";
        URL myUrl = new URL(imageURL);

        new DownloadImageTask(avatarView).execute(imageURL);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
