package com.gisdev.crmshm.util.filter.fields;

import com.gisdev.crmshm.util.filter.IQueryField;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProfiliField implements IQueryField {

    EMRI("emri", "emri");

    private String fieldName;
    private String attributeName;

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getAttributeName() {
        return attributeName;
    }

}
