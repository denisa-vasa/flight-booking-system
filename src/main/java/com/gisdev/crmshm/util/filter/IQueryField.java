package com.gisdev.crmshm.util.filter;

public interface IQueryField {
    /**
     * @return name of field to filter
     */
    String getFieldName();

    /**
     *
     * @return name of attribute in the entity
     */
    String getAttributeName();
}
