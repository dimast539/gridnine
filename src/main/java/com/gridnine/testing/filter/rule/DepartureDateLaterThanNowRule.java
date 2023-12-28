package com.gridnine.testing.filter.rule;


import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;

public class DepartureDateLaterThanNowRule implements FlightFilterRule {
    private static final String NAME = "Departure date later than now";

    @Override
    public String getMessage() {
        return NAME;
    }

    @Override
    public boolean check(Flight flight) {
        return flight.getSegments().stream().anyMatch(
                segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())
        );
    }
}
