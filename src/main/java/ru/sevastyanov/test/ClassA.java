package ru.sevastyanov.test;

import ru.sevastyanov.test.param.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface ClassA {

  void setDefaultRate(BigDecimal newDefaultRate);
  BigDecimal getDefaultRate();
  BigDecimal getSpecialtRate();
  public BigDecimal calcSpecialInterest(BigDecimal amount, Param<BigDecimal>... rate);
  BigDecimal calcDefaultInterest(BigDecimal amount);
}
