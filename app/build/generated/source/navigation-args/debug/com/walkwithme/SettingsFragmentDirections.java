package com.walkwithme;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class SettingsFragmentDirections {
  private SettingsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSettingsFragmentToFirstFragment() {
    return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_FirstFragment);
  }

  @NonNull
  public static ActionSettingsFragmentToProfile actionSettingsFragmentToProfile() {
    return new ActionSettingsFragmentToProfile();
  }

  @NonNull
  public static NavDirections actionSettingsFragmentToLoginFragment() {
    return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_loginFragment);
  }

  public static class ActionSettingsFragmentToProfile implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionSettingsFragmentToProfile() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionSettingsFragmentToProfile setId(int Id) {
      this.arguments.put("Id", Id);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("Id")) {
        int Id = (int) arguments.get("Id");
        __result.putInt("Id", Id);
      } else {
        __result.putInt("Id", 0);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_settingsFragment_to_profile;
    }

    @SuppressWarnings("unchecked")
    public int getId() {
      return (int) arguments.get("Id");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionSettingsFragmentToProfile that = (ActionSettingsFragmentToProfile) object;
      if (arguments.containsKey("Id") != that.arguments.containsKey("Id")) {
        return false;
      }
      if (getId() != that.getId()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + getId();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionSettingsFragmentToProfile(actionId=" + getActionId() + "){"
          + "Id=" + getId()
          + "}";
    }
  }
}
