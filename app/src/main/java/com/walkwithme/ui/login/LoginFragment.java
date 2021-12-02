package com.walkwithme.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.walkwithme.MainActivity;
import com.walkwithme.databinding.FragmentLoginBinding;

import com.walkwithme.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private static final String TAG = "LoginFragment";

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final Button registerButton = binding.register;
        final ProgressBar loadingProgressBar = binding.loading;
        final TextView unsuccessful = binding.success;

        unsuccessful.setVisibility(View.GONE);

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        String url = "";
                        if (patternMatches(usernameEditText.getText().toString())) {
                            url =
                                    MainActivity.url
                                            + "user/loginByEmail?key="
                                            + MainActivity.apiKey
                                            + "&email="
                                            + usernameEditText.getText().toString()
                                            + "&password="
                                            + passwordEditText.getText().toString();
                        } else {
                            url =
                                    MainActivity.url
                                            + "user/loginByUsername?key="
                                            + MainActivity.apiKey
                                            + "&username="
                                            + usernameEditText.getText().toString()
                                            + "&password="
                                            + passwordEditText.getText().toString();
                        }
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
                                                        if (response.length() > 0) {
                                                            int uId = response.getInt("id");
                                                            MainActivity.setLoggedInUserId(uId);
                                                            NavHostFragment.findNavController(
                                                                            LoginFragment.this)
                                                                    .navigate(
                                                                            R.id.action_loginFragment_to_FirstFragment);
                                                        } else {
                                                            unsuccessful.setVisibility(
                                                                    View.VISIBLE);
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    unsuccessful.setVisibility(View.VISIBLE);
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {

                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                unsuccessful.setVisibility(View.VISIBLE);
                                                /**
                                                 * VolleyLog.d(TAG, "Error: " + error.getMessage());
                                                 * Toast.makeText(getContext(), error.getMessage(),
                                                 * Toast.LENGTH_SHORT).show();*
                                                 */
                                                // hide the progress dialog
                                            }
                                        });

                        queue.add(jsonObjectRequest);
                    }
                });

        registerButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        String url = "";
                        url =
                                MainActivity.url
                                        + "user/newUser?key="
                                        + MainActivity.apiKey
                                        + "&username="
                                        + usernameEditText.getText().toString()
                                        + "&password="
                                        + passwordEditText.getText().toString();

                        JsonObjectRequest jsonObjectRequest =
                                new JsonObjectRequest(
                                        Request.Method.POST,
                                        url,
                                        null,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                if (response != null) {
                                                    try {
                                                        if (response.length() > 0) {
                                                            int uId = response.getInt("id");
                                                            MainActivity.setLoggedInUserId(uId);
                                                            NavHostFragment.findNavController(
                                                                            LoginFragment.this)
                                                                    .navigate(
                                                                            R.id.action_loginFragment_to_FirstFragment);
                                                        } else {
                                                            unsuccessful.setVisibility(
                                                                    View.VISIBLE);
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    unsuccessful.setVisibility(View.VISIBLE);
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {

                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                unsuccessful.setVisibility(View.VISIBLE);
                                                /**
                                                 * VolleyLog.d(TAG, "Error: " + error.getMessage());
                                                 * Toast.makeText(getContext(), error.getMessage(),
                                                 * Toast.LENGTH_SHORT).show();*
                                                 */
                                                // hide the progress dialog
                                            }
                                        });
                        queue.add(jsonObjectRequest);

                        loginButton.callOnClick();

                        url = "";
                        if (patternMatches(usernameEditText.getText().toString())) {
                            url =
                                    MainActivity.url
                                            + "user/loginByEmail?key="
                                            + MainActivity.apiKey
                                            + "&email="
                                            + usernameEditText.getText().toString()
                                            + "&password="
                                            + passwordEditText.getText().toString();
                        } else {
                            url =
                                    MainActivity.url
                                            + "user/loginByUsername?key="
                                            + MainActivity.apiKey
                                            + "&username="
                                            + usernameEditText.getText().toString()
                                            + "&password="
                                            + passwordEditText.getText().toString();
                        }
                        JsonObjectRequest jsonObjectRequest2 =
                                new JsonObjectRequest(
                                        Request.Method.GET,
                                        url,
                                        null,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                if (response != null) {
                                                    try {
                                                        if (response.length() > 0) {
                                                            int uId = response.getInt("id");
                                                            MainActivity.setLoggedInUserId(uId);
                                                            NavHostFragment.findNavController(
                                                                            LoginFragment.this)
                                                                    .navigate(
                                                                            R.id.action_loginFragment_to_FirstFragment);
                                                        } else {
                                                            unsuccessful.setVisibility(
                                                                    View.VISIBLE);
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    unsuccessful.setVisibility(View.VISIBLE);
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {

                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                unsuccessful.setVisibility(View.VISIBLE);
                                                /**
                                                 * VolleyLog.d(TAG, "Error: " + error.getMessage());
                                                 * Toast.makeText(getContext(), error.getMessage(),
                                                 * Toast.LENGTH_SHORT).show();*
                                                 */
                                                // hide the progress dialog
                                            }
                                        });
                        queue.add(jsonObjectRequest2);
                    }
                });
    }

    private boolean patternMatches(String emailAddress) {
        return Pattern.compile(
                        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(emailAddress)
                .matches();
    }

    private void updateUiWithUser() {
        String welcome = getString(R.string.welcome) + "AYAYA";
        // TODO : initiate successful logged in experience
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(), errorString, Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
