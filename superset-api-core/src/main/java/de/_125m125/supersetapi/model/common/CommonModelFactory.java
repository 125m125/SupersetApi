package de._125m125.supersetapi.model.common;

public interface CommonModelFactory {
  Result createResult(String result);

  Class<? extends Result> getResultClass();
}
