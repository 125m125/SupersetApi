package de._125m125.supersetapi.model.security;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import de._125m125.supersetapi.model.security.SecurityModelFactory.GuestTokenRequestDataBuilder;

public class SimpleGuestTokenRequestDataBuilder<T extends GuestTokenRequestData, U extends Resource, V extends RlsRule, W extends GuestUser>
    implements GuestTokenRequestDataBuilder<T, U, V, W> {

  private final GuestUserBuilder<W> guestUserBuilder;
  private final BiFunction<String, String, U> resourceBuilder;
  private final Function<String, V> rlsRuleBuilder1;
  private final BiFunction<String, Integer, V> rlsRuleBuilder2;
  private final GuestTokenRequestDataFactory<T, U, V, W> requestDataFactory;
  private final U[] resourceBaseArray;
  private final V[] rlsRuleBaseArray;

  private W guestUser;
  private final List<U> resources = new ArrayList<>();
  private final List<V> rlsRules = new ArrayList<>();

  public SimpleGuestTokenRequestDataBuilder(GuestUserBuilder<W> guestUserBuilder,
      BiFunction<String, String, U> resourceBuilder, Function<String, V> rlsRuleBuilder1,
      BiFunction<String, Integer, V> rlsRuleBuilder2,
      GuestTokenRequestDataFactory<T, U, V, W> requestDataFactory, U[] resourceBaseArray,
      V[] rlsRuleBaseArray) {
    this.guestUserBuilder = guestUserBuilder;
    this.resourceBuilder = resourceBuilder;
    this.rlsRuleBuilder1 = rlsRuleBuilder1;
    this.rlsRuleBuilder2 = rlsRuleBuilder2;
    this.requestDataFactory = requestDataFactory;
    this.resourceBaseArray = resourceBaseArray;
    this.rlsRuleBaseArray = rlsRuleBaseArray;
  }

  @Override
  public GuestTokenRequestDataBuilder<T, U, V, W> setGuestUser() {
    this.guestUser = guestUserBuilder.createGuestUser();
    return this;
  }

  @Override
  public GuestTokenRequestDataBuilder<T, U, V, W> setGuestUser(String username) {
    this.guestUser = guestUserBuilder.createGuestUser(username);
    return this;
  }

  @Override
  public GuestTokenRequestDataBuilder<T, U, V, W> setGuestUser(String username, String firstname,
      String lastname) {
    this.guestUser = guestUserBuilder.createGuestUser(username, firstname, lastname);
    return this;
  }

  @Override
  public GuestTokenRequestDataBuilder<T, U, V, W> addResource(String id, String type) {
    this.resources.add(resourceBuilder.apply(id, type));
    return this;
  }

  @Override
  public GuestTokenRequestDataBuilder<T, U, V, W> addRlsRule(String clause) {
    this.rlsRules.add(rlsRuleBuilder1.apply(clause));
    return this;
  }

  @Override
  public GuestTokenRequestDataBuilder<T, U, V, W> addRlsRule(String clause, int dataset) {
    this.rlsRules.add(rlsRuleBuilder2.apply(clause, dataset));
    return this;
  }

  @Override
  public T build() {
    return requestDataFactory.createGuestTokenRequestData(resources.toArray(resourceBaseArray),
        rlsRules.toArray(rlsRuleBaseArray), guestUser);
  }

  public interface GuestUserBuilder<W extends GuestUser> {
    W createGuestUser();

    W createGuestUser(String username);

    W createGuestUser(String firstname, String lastname, String username);
  }

  public interface GuestTokenRequestDataFactory<T extends GuestTokenRequestData, U extends Resource, V extends RlsRule, W extends GuestUser> {
    T createGuestTokenRequestData(U[] resources, V[] rlsRules, W GuestUser);
  }

}
