package com.gisdev.crmshm.util.filter;


public enum FilterOperator {
  EQ("eq"),
  NOT_EQ("neq"),
  GT("gt"),
  LT("lt"),
  GTE("gte"),
  LTE("lte"),
  LIKE("like"),
  ILIKE("ilike"),
  START_WITH("start_with"),
  IN("in");

  private final String operator;

  FilterOperator(String operator) {
    this.operator = operator;
  }

  public String getOperator() {
    return String.format(":%s:", operator);
  }

  @Override
  public String toString() {
    return operator;
  }
}
