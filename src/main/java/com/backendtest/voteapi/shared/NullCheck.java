package com.backendtest.voteapi.shared;

import java.util.Optional;
import java.util.function.Supplier;

public class NullCheck {

  public static <T> Optional<T> resolve(Supplier<T> resolver) {
    try {
      T result = resolver.get();
      return Optional.ofNullable(result);
    } catch (NullPointerException e) {
      return Optional.empty();
    } catch (IndexOutOfBoundsException e) {
      return Optional.empty();
    }
  }
}
