package com.walkwithme.ui.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.walkwithme.R;

public class LoginFragmentDirections {
  private LoginFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionLoginFragmentToFirstFragment() {
    return new ActionOnlyNavDirections(R.id.action_loginFragment_to_FirstFragment);
  }
}
