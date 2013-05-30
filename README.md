# Hamcrest 1.3 utility matchers

The static factory methods for each matcher are generated to `com.nitorcreations.Matchers` class.

## Maven

Available in Maven central repository. Add this to your `pom.xml`

```xml
<dependency>
  <groupId>com.nitorcreations</groupId>
  <artifactId>matchers</artifactId>
  <version>1.0</version>
</dependency>
```

## Contents

The package contains the following matchers:

* CollectionMatchers
 - `containsElements(Collection<E>)` - matches that the target collection contains exactly the same items in exactly the same order
 - `containsElementsInAnyOrder(Collection<E>)` - matches that the target collection contains exactly the same items in any order
 - `hasItemsOf(Collection<T> items)` - matches that the target collection contains all of the items in the given collection. The collection may also contain other items.
 - `hasNoDuplicates(Class<T>)` - matches that the target collection contains no duplicates. The `class` parameter is to ensure generics work without unchecked casts.
* HasItemAtIndex
 - `hasFirstItem(Matcher<T> item)`
 - `hasLastItem(Matcher<T> item)`
 - `hasItemAtIndex(Matcher<T> item)`
* IteratorMatcher
 - `iteratorThat(Iterable<T>)`- wraps `Iterable<T>` matchers to work with `Iterator<T>`
* ReflectionEqualsMatcher
 - `reflectEquals(T target, String... excludedFields)` - matches reflectively (shallow) that all fields except `excludedFields` are equal
* SerializableMatcher
 - `serializable()` - matches that the given object can be serialized and deserialized
* StringMatchers
 - `containsIgnoreCase(String)`

## Creating new matchers

If you add new matcher classes, be sure to add `@Factory` annotations to the static factory methods, and remember to add the new class to the `matchers.xml` file. This will ensure that the factory methods will be accessible in the common `com.nitorcreations.Matchers` class.

Example:

```java
public final class HasItemAtIndex<X> extends TypeSafeDiagnosingMatcher<Iterable<X>> {
    @Factory
    public static <T> Matcher<Iterable<T>> hasFirstItem(Matcher<T> item) {
        return new HasItemAtIndex<T>(0, item);
    }
```

And the corresponding row in `matchers.xml`:

```xml
<matchers>
    ...
    <factory class="com.nitorcreations.matchers.HasItemAtIndex"/>
    ...
</matchers>
```
