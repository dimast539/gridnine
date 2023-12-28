package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.rule.ArrivalDateEarlyLaterDepartureDateRule;
import com.gridnine.testing.filter.rule.DepartureDateLaterThanNowRule;
import com.gridnine.testing.filter.rule.TimeBetweenFlightsLessThanSomeHoursRule;
import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Flight> listOfFlights = FlightBuilder.createFlights();
        System.out.println("Flights from test builder:");
        System.out.println(listOfFlights);
        System.out.println();

        FlightFilter flightFilter = new FlightFilter(
                List.of(
                        new DepartureDateLaterThanNowRule()
                )
        );
        for (Map.Entry<String, List<Flight>> entry : flightFilter.filteredFlight(listOfFlights).entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println();

        FlightFilter flightFilterTwo = new FlightFilter(
                List.of(
                        new ArrivalDateEarlyLaterDepartureDateRule()
                )
        );
        for (Map.Entry<String, List<Flight>> entry : flightFilterTwo.filteredFlight(listOfFlights).entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println();

        FlightFilter flightFilterThree = new FlightFilter(
                List.of(
                        new TimeBetweenFlightsLessThanSomeHoursRule(2)
                )
        );
        for (Map.Entry<String, List<Flight>> entry : flightFilterThree.filteredFlight(listOfFlights).entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println();

        FlightFilter flightFilterAll = new FlightFilter(
                List.of(
                        new DepartureDateLaterThanNowRule(),
                        new ArrivalDateEarlyLaterDepartureDateRule(),
                        new TimeBetweenFlightsLessThanSomeHoursRule(2)
                )
        );
        for (Map.Entry<String, List<Flight>> entry : flightFilterAll.filteredFlight(listOfFlights).entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
