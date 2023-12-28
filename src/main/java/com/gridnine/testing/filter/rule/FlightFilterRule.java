package com.gridnine.testing.filter.rule;

import com.gridnine.testing.model.Flight;

public interface FlightFilterRule {
    /** Return name of rule */
    String getMessage();
    /** Return true if flight satisfy the rule else false */
    boolean check(Flight flight);
}
