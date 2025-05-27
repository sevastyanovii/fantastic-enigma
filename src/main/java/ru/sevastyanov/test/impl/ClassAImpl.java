package ru.sevastyanov.test.impl;

import ru.sevastyanov.test.CalcUtils;
import ru.sevastyanov.test.ClassA;
import ru.sevastyanov.test.param.InOutParam;
import ru.sevastyanov.test.ThreadLocalHolder;
import ru.sevastyanov.test.param.Param;

import java.math.BigDecimal;
import java.util.Optional;

import static ru.sevastyanov.test.ClassLogger.formatNumber;
import static ru.sevastyanov.test.ClassLogger.info;
import static ru.sevastyanov.test.impl.ClassBImpl.CALCULATOR_VERSION;

public class ClassAImpl implements ClassA {

  private BigDecimal defaultRate = null;

  static {
    info("init package ClassA");
  }

  public ClassAImpl() {
    ThreadLocalHolder.setB(new ClassBImpl());
    info("using calculator: %s", CALCULATOR_VERSION);
    initDefaultRate();
  }

  @Override
  public void setDefaultRate(BigDecimal newDefaultRate) {
    info("change default rate from %s to %s", getDefaultRate(), newDefaultRate);
    defaultRate = newDefaultRate;
  }

  @Override
  public BigDecimal getDefaultRate() {
    return defaultRate;
  }

  @Override
  public BigDecimal getSpecialtRate() {
    return new BigDecimal("0.2");
  }

  @Override
  public BigDecimal calcSpecialInterest(BigDecimal amount, Param<BigDecimal> ... rate) {
    var vAmt = InOutParam.of(new BigDecimal("1000"));
    var vRate = Optional.ofNullable(CalcUtils.getParameter(rate, 0, getDefaultRate())).orElse(getSpecialtRate());
    ThreadLocalHolder.getClassB().ifPresent(classB -> classB.doCalcInterest(amount, vAmt, Param.ofEmpty(), Param.of(vRate), Param.ofEmpty()));
    return vAmt.getValue();
  }

  @Override
  public BigDecimal calcDefaultInterest(BigDecimal amount) {
    final var vAmt = InOutParam.of(new BigDecimal("1000"));
    ThreadLocalHolder.getClassB()
        .ifPresent(classB -> classB.doCalcInterest(amount, vAmt, Param.of("TARIFF1"), Param.ofEmpty(), Param.of(new BigDecimal("10"))));
    return vAmt.getValue();
  }

  private void initDefaultRate() {
    defaultRate = new BigDecimal("0.1");
    info("init default rate to value: %s", formatNumber(defaultRate));
  }

}
