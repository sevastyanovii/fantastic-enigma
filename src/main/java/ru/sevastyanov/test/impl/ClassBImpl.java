package ru.sevastyanov.test.impl;

import ru.sevastyanov.test.ClassA;
import ru.sevastyanov.test.ClassB;
import ru.sevastyanov.test.ClassLogger;
import ru.sevastyanov.test.param.InOutParam;
import ru.sevastyanov.test.ThreadLocalHolder;
import ru.sevastyanov.test.param.Param;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.BigDecimal.ZERO;
import static ru.sevastyanov.test.CalcUtils.getParameter;
import static ru.sevastyanov.test.ClassLogger.formatNumber;
import static ru.sevastyanov.test.ClassLogger.info;

public class ClassBImpl implements ClassB {

  public static final String CALCULATOR_VERSION = "calc v1.0";

  public ClassBImpl() {
    initClass();
  }

  public void initClass() {
    info("init package ClassB");
    info("using default rate = %s", ThreadLocalHolder.getClassA()
        .map(ClassA::getDefaultRate).map(ClassLogger::formatNumber).orElse("not defined"));
  }
  @Override
  public void doCalcInterest(BigDecimal amount, InOutParam<BigDecimal> outInterest, Param...params) {
    var tariff = getParameter(params, 0, "DEF");
    var rate = getParameter(params, 1, ThreadLocalHolder.getClassA().map(ClassA::getDefaultRate).orElseThrow());
    var commAmt = Optional.ofNullable(getParameter(params, 2, (BigDecimal)null)).orElse(ZERO);
    outInterest.setValue(Optional.of(amount).map(a -> a.multiply(rate)).map(res -> res.subtract(commAmt)).get());
    info("do calc interest for amount=%s; tariff=%s; rate=%s; p_comm_amt=%s", amount, tariff, formatNumber(rate), ZERO.equals(commAmt) ? "" : commAmt);
  }

}
