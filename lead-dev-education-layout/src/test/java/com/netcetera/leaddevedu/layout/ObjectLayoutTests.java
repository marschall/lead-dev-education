package com.netcetera.leaddevedu.layout;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * Demonstrates field layout.
 */
class ObjectLayoutTests {
  
  /*
   * Experiment with
   * 
   * -XX:-UseCompressedClassPointers
   * -XX:-UseCompressedOops
   */

  @Test
  void allTypes() {
    // primitive vs reference types -> what are their relative sizes?
    // in which order do instance variables show up
    System.out.println(ClassLayout.parseClass(AllPrimitives.class).toPrintable());
    System.out.println(ClassLayout.parseClass(AllReferences.class).toPrintable());
  }

  @Test
  void referenceTypes() {
    // what are the sizes of the reference types
    System.out.println(ClassLayout.parseClass(Byte.class).toPrintable());
    System.out.println(ClassLayout.parseClass(Boolean.class).toPrintable());
    System.out.println(ClassLayout.parseClass(Short.class).toPrintable());
    System.out.println(ClassLayout.parseClass(Character.class).toPrintable());
    System.out.println(ClassLayout.parseClass(Integer.class).toPrintable());
    System.out.println(ClassLayout.parseClass(Long.class).toPrintable());
    System.out.println(ClassLayout.parseClass(String.class).toPrintable());
  }

  @Test
  void integerArrays() {
    // int[] vs Integer[] -> what are their relative sizes?
    System.out.println(ClassLayout.parseInstance(new int[16]).toPrintable());
    System.out.println(ClassLayout.parseInstance(new Integer[16]).toPrintable());
    
    Object root = IntStream.range(0, 16).boxed().toArray(Integer[]::new);
    GraphLayout graphLayout = GraphLayout.parseInstance(new Object[]{root});
    System.out.println(graphLayout.toPrintable());
    System.out.println(graphLayout.toFootprint());
  }

  static final class AllPrimitives {

    Object o;
    byte b;
    short s;
    char c;
    int i;
    float f;
    long l;
    double d;
    boolean bool;

  }

  static final class AllReferences {

    Object o;
    Byte b;
    Short s;
    Character c;
    Integer i;
    Float f;
    Long l;
    Double d;
    Boolean bool;

  }

}
