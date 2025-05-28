package ru.sevastyanov.test;

import ru.sevastyanov.test.impl.ClassAImpl;
import ru.sevastyanov.test.impl.ClassBImpl;
import ru.sevastyanov.test.param.InOutParam;

import java.math.BigDecimal;

import static ru.sevastyanov.test.ClassLogger.format;
import static ru.sevastyanov.test.ClassLogger.info;
import static ru.sevastyanov.test.param.Param.of;
import static ru.sevastyanov.test.param.Param.ofEmpty;

public class Application {

  public static void main(String[] args) {
    info("*** TEST1");
    ClassAImpl.init();
    ThreadLocalHolder.setB(new ClassBImpl());
    var classA = new ClassAImpl();
    ThreadLocalHolder.setA(classA);

    var classB = ThreadLocalHolder.getClassB().orElseThrow();
    var param = InOutParam.<BigDecimal>of(null);
    classB.doCalcInterest(new BigDecimal("2000"), param, ofEmpty(), ofEmpty(), of(new BigDecimal(100)));
    info("interest = %s", format(param.getValue()));

    info("");
    info("*** TEST2");
    classA.setDefaultRate(new BigDecimal("0.5"));
    var resAmt = classA.calcDefaultInterest(new BigDecimal("1000"));
    info("interest = %s", format(resAmt));

    info("");
    info("*** TEST3");
    classA.setDefaultRate(new BigDecimal("0.6"));
    resAmt = classA.calcSpecialInterest(new BigDecimal("1000"));
    info("interest = %s", format(resAmt));

    info("");
    info("*** TEST4");
    resAmt = classA.calcSpecialInterest(new BigDecimal("1000"), of(new BigDecimal("0.3")));
    info("interest = %s", format(resAmt));

    info("");
    info("*** TEST5");
    resAmt = classA.calcSpecialInterest(new BigDecimal("1000"), of(null));
    info("interest = %s", format(resAmt));

  }
}
