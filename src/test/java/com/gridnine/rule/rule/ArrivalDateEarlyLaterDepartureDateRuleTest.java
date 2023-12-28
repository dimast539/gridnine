package com.gridnine.rule.rule;

import com.gridnine.testing.filter.rule.ArrivalDateEarlyLaterDepartureDateRule;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.gridnine.testing.builder.FlightBuilder.createFlight;
import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 * Testing of {@link ArrivalDateEarlyLaterDepartureDateRule}.
 */
class ArrivalDateEarlyLaterDepartureDateRuleTest {
    LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    Flight flight = createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6));

    @Test
    void checkArrivalDateEarlyLaterDepartureDateRule_Test() {
        ArrivalDateEarlyLaterDepartureDateRule rule = new ArrivalDateEarlyLaterDepartureDateRule();
        boolean actual = rule.check(flight);
        assertFalse(actual);
    }
}