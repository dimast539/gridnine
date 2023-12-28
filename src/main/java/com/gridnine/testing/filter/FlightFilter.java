package com.gridnine.testing.filter;

import com.gridnine.testing.filter.rule.FlightFilterRule;
import com.gridnine.testing.model.Flight;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FlightFilter {
    private final List<FlightFilterRule> rules;

    public FlightFilter(List<FlightFilterRule> rules) {
        this.rules = rules;
    }

    public Map<String, List<Flight>> filteredFlight(List<Flight> flights) {

        final Map<String, List<Flight>> mapOfFlightFilters = new HashMap<>();
        StringBuilder resultRuleNames = new StringBuilder();

        List<Flight> resultFilteredFlights = new LinkedList<>();
        final Map<String, List<Flight>> resultMap = new HashMap<>();

        rules.forEach(rule ->
                mapOfFlightFilters.put(
                        rule.getMessage(),
                        flights.stream()
                                .filter(rule::check)
                                .toList()
                )
        );

        for (Map.Entry<String, List<Flight>> entry : mapOfFlightFilters.entrySet()) {
            resultRuleNames.append(entry.getKey()).append(", ");
            if (resultFilteredFlights.isEmpty()) {
                resultFilteredFlights.addAll(entry.getValue());
            } else {
                resultFilteredFlights.retainAll(entry.getValue());
            }
        }

        resultMap.put(resultRuleNames.toString(), resultFilteredFlights);

        return resultMap;
    }
}
