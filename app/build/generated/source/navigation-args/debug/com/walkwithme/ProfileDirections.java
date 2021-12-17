package com.walkwithme;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class ProfileDirections {
  private ProfileDirections() {
  }

  @NonNull
  public static NavDirections actionProfileToSettingsFragment() {
    return new ActionOnlyNavDirections(R.id.action_profile_to_settingsFragment);
  }

  @NonNull
  public static NavDirections actionProfileToFirstFragment() {
    return new ActionOnlyNavDirections(R.id.action_profile_to_FirstFragment);
  }
}
