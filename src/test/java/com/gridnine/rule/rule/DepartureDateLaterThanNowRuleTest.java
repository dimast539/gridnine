package com.gridnine.rule.rule;

import com.gridnine.testing.filter.rule.DepartureDateLaterThanNowRule;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.gridnine.testing.builder.FlightBuilder.createFlight;
import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 * Testing of {@link DepartureDateLaterThanNowRule}.
 */
class DepartureDateLaterThanNowRuleTest {
    LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    Flight flight = createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow);

    @Test
    void checkDepartureDateLaterThanNowRule_Test() {
        DepartureDateLaterThanNowRule rule = new DepartureDateLaterThanNowRule();
        boolean actual = rule.check(flight);
        assertFalse(actual);
    }
}