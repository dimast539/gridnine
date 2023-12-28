package com.gridnine.testing.filter.rule;

import com.gridnine.testing.model.Flight;

public class ArrivalDateEarlyLaterDepartureDateRule implements FlightFilterRule {
    @Override
    public String getMessage() {
        return "Flight with arrival date later than departure date";
    }

    @Override
    public boolean check(Flight flight) {
        return flight.getSegments().stream().anyMatch(
                segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())
        );
    }
}
