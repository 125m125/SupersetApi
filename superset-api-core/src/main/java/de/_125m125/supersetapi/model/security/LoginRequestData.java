package de._125m125.supersetapi.model.security;

public interface LoginRequestData {
  String getUsername();

  String getPassword();

  String getProvider();

  boolean isRefresh();
}
