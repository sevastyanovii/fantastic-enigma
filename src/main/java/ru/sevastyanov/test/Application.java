package ru.sevastyanov.test;

import ru.sevastyanov.test.impl.ClassAImpl;
import ru.sevastyanov.test.param.InOutParam;
import ru.sevastyanov.test.param.Param;

import java.math.BigDecimal;

import static ru.sevastyanov.test.ClassLogger.info;


public class Application {

  public static void main(String[] args) {
    info("*** TEST1");
    var classA = new ClassAImpl();
    ThreadLocalHolder.setA(classA);
    var classB = ThreadLocalHolder.getClassB().orElseThrow();
    var param = InOutParam.<BigDecimal>of(null);
    classB.doCalcInterest(new BigDecimal("2000"), param, Param.ofEmpty(), Param.ofEmpty(), Param.of(new BigDecimal(100)));
    info("interest = %s", param.getValue());

    info("");
    info("*** TEST2");
    classA.setDefaultRate(new BigDecimal("0.5"));
    var resAmt = classA.calcDefaultInterest(new BigDecimal("1000"));
    info("interest = %s", resAmt);

    info("");
    info("*** TEST3");
    classA.setDefaultRate(new BigDecimal("0.6"));
    resAmt = classA.calcSpecialInterest(new BigDecimal("1000"));
    info("interest = %s", resAmt);

  }
}
