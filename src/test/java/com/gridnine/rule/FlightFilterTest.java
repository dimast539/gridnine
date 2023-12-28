package com.gridnine.rule;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.rule.ArrivalDateEarlyLaterDepartureDateRule;
import com.gridnine.testing.filter.rule.DepartureDateLaterThanNowRule;
import com.gridnine.testing.filter.rule.TimeBetweenFlightsLessThanSomeHoursRule;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gridnine.testing.builder.FlightBuilder.createFlight;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing of {@link FlightFilter}.
 */
class FlightFilterTest {

    List<Flight> listOfFlights = FlightBuilder.createFlights();

    LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    FlightFilter flightFilter = new FlightFilter(
            List.of(
                    new TimeBetweenFlightsLessThanSomeHoursRule(2)
            )
    );

    FlightFilter flightFilterAll = new FlightFilter(
            List.of(
                    new DepartureDateLaterThanNowRule(),
                    new ArrivalDateEarlyLaterDepartureDateRule(),
                    new TimeBetweenFlightsLessThanSomeHoursRule(2)
            )
    );

    @Test
    void filteredFlight_Test() {
        Map<String, List<Flight>> actualMapForOneFilter = flightFilter.filteredFlight(listOfFlights);
        Map<String, List<Flight>> actualMapForAllFilters = flightFilterAll.filteredFlight(listOfFlights);

        Map<String, List<Flight>> expectedMapForOneFilter = new HashMap<>();
        expectedMapForOneFilter.put(
                "Time between flights less than some hours, ",
                List.of(
                        createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                        createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                        createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6))
                )
                );

        Map<String, List<Flight>> expectedMapForAllFilters = new HashMap<>();
        expectedMapForAllFilters.put(
                "Departure date later than now," +
                        " Time between flights less than some hours," +
                        " Flight with arrival date later than departure date,",
                Arrays.asList(
                        createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5))
                )
        );


        assertEquals(expectedMapForOneFilter.values().toString(),actualMapForOneFilter.values().toString());
        assertEquals(expectedMapForAllFilters.values().toString(),actualMapForAllFilters.values().toString());
    }
}