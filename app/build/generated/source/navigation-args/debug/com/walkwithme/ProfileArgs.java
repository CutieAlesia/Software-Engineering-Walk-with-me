package com.walkwithme;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ProfileArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ProfileArgs() {
  }

  @SuppressWarnings("unchecked")
  private ProfileArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ProfileArgs fromBundle(@NonNull Bundle bundle) {
    ProfileArgs __result = new ProfileArgs();
    bundle.setClassLoader(ProfileArgs.class.getClassLoader());
    if (bundle.containsKey("Id")) {
      int Id;
      Id = bundle.getInt("Id");
      __result.arguments.put("Id", Id);
    } else {
      __result.arguments.put("Id", 0);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getId() {
    return (int) arguments.get("Id");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    ProfileArgs that = (ProfileArgs) object;
    if (arguments.containsKey("Id") != that.arguments.containsKey("Id")) {
      return false;
    }
    if (getId() != that.getId()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getId();
    return result;
  }

  @Override
  public String toString() {
    return "ProfileArgs{"
        + "Id=" + getId()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(ProfileArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public ProfileArgs build() {
      ProfileArgs result = new ProfileArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setId(int Id) {
      this.arguments.put("Id", Id);
      return this;
    }

    @SuppressWarnings("unchecked")
    public int getId() {
      return (int) arguments.get("Id");
    }
  }
}
