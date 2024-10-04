package com.netcetera.leaddevedu.jfr;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Timestamp;
import jdk.jfr.Timespan;
import jdk.jfr.Label;
import jdk.jfr.DataAmount;

@Label("JDBC Operation")
@Description("A JDBC Operation")
@Category("JDBC")
class CustomJfrEvent extends Event {

  CustomJfrEvent() {
    super();
  }

  @Label("Object")
  @Description("The object type executing the operation")
  String operationObject;
  
  @Label("Duration")
  @Description("How long X has lasted")
  @Timespan(Timespan.MILLISECONDS)
  long millis;
  
  @Label("Started At")
  @Description("When X has started")
  @Timestamp
  long instant;
  
  @Label("Started At")
  @Description("When X has started")
  @DataAmount
  long uploadSize;

}
