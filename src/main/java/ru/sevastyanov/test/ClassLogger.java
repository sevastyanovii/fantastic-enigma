package ru.sevastyanov.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ClassLogger {

  /**
   * вывод лога
   *
   * @param pattern шаблон строки
   * @param params параметры
   */
  public static void info(String pattern, Object ... params) {
    System.out.printf(Locale.ENGLISH, (pattern) + "%n", params);
  }

  public static String formatNumber(BigDecimal decimal) {
    DecimalFormatSymbols en = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
    return new DecimalFormat( ".#", en).format(decimal.doubleValue());
  }

  public static String format(BigDecimal decimal) {
    var decimalFormat = decimal.remainder(BigDecimal.ONE).doubleValue() == 0 ? "#" : "#.##";
    DecimalFormatSymbols en = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
    return new DecimalFormat( decimalFormat, en).format(decimal.doubleValue());
  }

}
