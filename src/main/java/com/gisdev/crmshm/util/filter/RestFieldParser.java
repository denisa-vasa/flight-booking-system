package com.gisdev.crmshm.util.filter;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class RestFieldParser<T extends IQueryField> {

  private final T[] fieldValues;

  public List<FilterDescriptor<T, ?>> parseFilters(List<String> filters) {
      List<String> multiFilters = new ArrayList<>(filters.size());
      for (String filter : filters) {
          if (filter.startsWith("[") && filter.endsWith("]")) {
              String filterContent = filter.substring(1, filter.length() - 1);
              multiFilters.addAll(Arrays.asList(filterContent.split(";")));
          } else {
              multiFilters.add(filter);
          }
      }
      List<FilterDescriptor<T, ?>> result = new ArrayList<>();
      for (String filter : multiFilters) {
          if (StringUtils.isBlank(filter)) {
              continue;
          }
          for (FilterOperator operator : FilterOperator.values()) {
              String[] tokens = filter.split(operator.getOperator());
              if (tokens.length > 1) {
                  String fieldAlias = tokens[0];
                  String serializedString = tokens[1];

                  T field = fromRestAlias(fieldAlias);
                  if (field != null) {
                      result.add(new FilterDescriptor<>(field, fieldAlias, operator, serializedString));
                  }
                  break;
              }
          }
      }

      return result;
  }

  private T fromRestAlias(String fieldAlias) {
    return Stream.of(fieldValues).filter(candidate -> Objects.equals(candidate.getFieldName(), fieldAlias)).findFirst()
        .orElse(null);
  }
}