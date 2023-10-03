package de._125m125.supersetapi.adapter;

import de._125m125.supersetapi.adapter.security.SupersetSecurityAdapter;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@AllArgsConstructor
public class DelegatingSupersetAdapter implements SupersetAdapter {
  @Delegate
  private final SupersetSecurityAdapter supersetSecurityAdapter;
}
