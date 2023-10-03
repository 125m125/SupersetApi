package de._125m125.supersetapi.model;

import de._125m125.supersetapi.model.common.CommonModelFactory;
import de._125m125.supersetapi.model.security.SecurityModelFactory;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@AllArgsConstructor
public class DelegatingSupersetFactory implements SupersetFactory {
  @Delegate
  private final CommonModelFactory commonModelFactory;
  @Delegate
  private final SecurityModelFactory securityModelFactory;
}
