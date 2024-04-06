package com.gisdev.crmshm.dataType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Destination {
    MILANO("Milano"),
    PARIS("Paris"),
    TIRANA("Tirana");

    public String name;
}
