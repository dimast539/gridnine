package com.gridnine.testing.filter.rule;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class TimeBetweenFlightsLessThanSomeHoursRule implements FlightFilterRule {
    public static final String NAME = "Time between flights less than some hours";
    private final int numberOfHours;

    public TimeBetweenFlightsLessThanSomeHoursRule(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    @Override
    public String getMessage() {
        return NAME;
    }

    @Override
    public boolean check(Flight flight) {
        List<Segment> segmentList = flight.getSegments();
        long sumTime = 0L;

        for (int i = 0; i < segmentList.size(); i++) {
            if ((i + 1) < segmentList.size()) {
                sumTime += ChronoUnit.HOURS.between(
                        segmentList.get(i).getArrivalDate(),
                        segmentList.get(i + 1).getDepartureDate()
                );
            }
        }
        return sumTime <= numberOfHours;
    }





}
