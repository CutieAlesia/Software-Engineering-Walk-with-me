package com.walkwithme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.walkwithme.databinding.FragmentSettingsBinding;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class SettingsFragment extends Fragment {

    private static final String TAG = "Settings";
    private static final int PICK_IMAGE_REQUEST = 111;
    private FragmentSettingsBinding binding;
    Bitmap bitmap;
    ProgressDialog progressDialog;

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
                                .navigate(R.id.action_settingsFragment_to_preferencesFragment);
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

        binding.ManagePicturesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
                        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
                    }
                });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                uploadImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    private void uploadImage() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading, please wait...");
        progressDialog.show();

        //converting image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        String URL = MainActivity.url + "info/upload&key="+ MainActivity.apiKey + "&id=" + MainActivity.getLoggedInUserId() + "&image=" + imageString;

        //sending image to server
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                if(s.equals("200")){
                    Toast.makeText(getContext(), "Upload Successful", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(), "Some error occurred!", Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();;
            }
        }) {
            //adding parameters to send
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("image", imageString);
                return parameters;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(getContext());
        rQueue.add(request);

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
