package com.shmoozed.model;

import java.util.List;

public class UserAuthorizations {

  private String token;
  private List<UserRoles> roles;

  public UserAuthorizations(String token, List<UserRoles> roles) {
    this.token = token;
    this.roles = roles;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public List<UserRoles> getRoles() {
    return roles;
  }

  public void setRoles(List<UserRoles> roles) {
    this.roles = roles;
  }

}
