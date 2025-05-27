package ru.sevastyanov.test;

import ru.sevastyanov.test.param.InOutParam;
import ru.sevastyanov.test.param.Param;

import java.math.BigDecimal;

public interface ClassB {
  void doCalcInterest(BigDecimal amount, InOutParam<BigDecimal> outInterest, Param...params);
}
