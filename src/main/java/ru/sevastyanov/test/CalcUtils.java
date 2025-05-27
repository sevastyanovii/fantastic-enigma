package ru.sevastyanov.test;

import ru.sevastyanov.test.param.Param;

import java.util.Optional;
import java.util.function.Function;

public class CalcUtils {

  /**
   * Получение параметра
   *
   * @param params параметры переменной длины
   * @param index индекс параметра
   * @param defaultValue значение по умолчанию, если параметр пропущен
   * @return значение параметра
   * @param <T> параметр типа
   */
  public static <T> T getParameter(Param<T>[] params, int index, T defaultValue) {
    try {
      return Optional.ofNullable(params).map(p -> p[index])
          .map(p -> p.isEmpty() ? defaultValue : p.getValue()).orElse(null);
    } catch (IndexOutOfBoundsException e) {
      return defaultValue;
    }
  }

}
