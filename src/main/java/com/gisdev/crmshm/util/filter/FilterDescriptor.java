package com.gisdev.crmshm.util.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterDescriptor<T extends IQueryField, V> {

  private T field;
  private String fieldAlias;
  private FilterOperator operator;
  private V value;
}