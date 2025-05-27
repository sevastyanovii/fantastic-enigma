package ru.sevastyanov.test;

import java.util.Optional;

public class ThreadLocalHolder {

  private static final ThreadLocal<ClassA> classAThreadLocal = new ThreadLocal<>();
  private static final ThreadLocal<ClassB> classBThreadLocal = new ThreadLocal<>();

  public static void setA(ClassA classA) {
    classAThreadLocal.set(classA);
  }

  public static void setB(ClassB classB) {
    classBThreadLocal.set(classB);
  }

  public static Optional<ClassA> getClassA() {
    return Optional.ofNullable(classAThreadLocal.get());
  }

  public static Optional<ClassB> getClassB() {
    return Optional.ofNullable(classBThreadLocal.get());
  }
  public static ThreadLocal<ClassA> getClassAThreadLocal() {
    return classAThreadLocal;
  }

  public static ThreadLocal<ClassB> getClassBThreadLocal() {
    return classBThreadLocal;
  }
}
