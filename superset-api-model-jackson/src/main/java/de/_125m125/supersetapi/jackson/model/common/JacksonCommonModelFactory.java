package de._125m125.supersetapi.jackson.model.common;

import de._125m125.supersetapi.model.common.CommonModelFactory;

public class JacksonCommonModelFactory implements CommonModelFactory {
  @Override
  public JacksonResult createResult(String result) {
    return new JacksonResult(result);
  }

  @Override
  public Class<? extends JacksonResult> getResultClass() {
    return JacksonResult.class;
  }
}
