package de._125m125.supersetapi.model.security;

public interface GuestTokenRequestData {
  Resource[] getResources();

  RlsRule[] getRls();

  GuestUser getUser();
}
