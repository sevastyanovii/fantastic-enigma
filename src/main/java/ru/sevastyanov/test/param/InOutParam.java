package ru.sevastyanov.test.param;

import java.util.Objects;

public class InOutParam <T> {

  private T value;

  public InOutParam(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InOutParam<?> that = (InOutParam<?>) o;

    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }

  public static <T> InOutParam<T> of(T value) {
    return new InOutParam<>(value);
  }
}
