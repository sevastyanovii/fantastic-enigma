package ru.sevastyanov.test.param;

public class Param<T> {
  public enum TypeValue {
    EMPTY, VALUE
  }

  private TypeValue typeValue;

  private T value;

  public T getValue() {
    return value;
  }

  public boolean isEmpty() {
    return typeValue == TypeValue.EMPTY;
  }

  private Param(TypeValue typeValue, T value) {
    this.typeValue = typeValue;
    this.value = value;
  }

  public static <T> Param<T> of(T value) {
    return new Param<>(TypeValue.VALUE, value);
  }

  public static <T> Param<T> ofEmpty() {
    return new Param<>(TypeValue.EMPTY, null);
  }
}
