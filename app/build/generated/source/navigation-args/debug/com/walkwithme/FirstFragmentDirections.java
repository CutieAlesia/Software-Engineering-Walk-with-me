package com.walkwithme;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class FirstFragmentDirections {
  private FirstFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionFirstFragmentToSecondFragment() {
    return new ActionOnlyNavDirections(R.id.action_FirstFragment_to_SecondFragment);
  }

  @NonNull
  public static NavDirections actionFirstFragmentToLoginFragment() {
    return new ActionOnlyNavDirections(R.id.action_FirstFragment_to_loginFragment);
  }

  @NonNull
  public static NavDirections actionFirstFragmentToTopUsers() {
    return new ActionOnlyNavDirections(R.id.action_FirstFragment_to_topUsers);
  }

  @NonNull
  public static NavDirections actionFirstFragmentToSettingsFragment() {
    return new ActionOnlyNavDirections(R.id.action_FirstFragment_to_settingsFragment);
  }

  @NonNull
  public static NavDirections actionFirstFragmentToFriends() {
    return new ActionOnlyNavDirections(R.id.action_FirstFragment_to_friends);
  }
}
