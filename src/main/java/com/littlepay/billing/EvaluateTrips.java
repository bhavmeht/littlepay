package com.littlepay.billing;

import com.littlepay.billing.model.TransactionEntry;
import com.littlepay.billing.model.TripEntry;
import com.littlepay.billing.model.TripStatus;
import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class EvaluateTrips {

    /**
     * Takes tap transaction details from input system and bill customers for their trip transit details.
     * @param entries
     * @return List<TripEntry> for all billed user trips.
     */
    public List<TripEntry> createTripEntriesToBillCustomers(List<TransactionEntry> entries) {
        // GroupBy PAN/User, bus company and bus id.
        Map<String, Map<String, Map<String, List<TransactionEntry>>>> groupByPanCmpIdBusId = entries.stream().collect(
                Collectors.groupingBy(TransactionEntry::getPan,
                        Collectors.groupingBy(TransactionEntry::getCompanyId,
                                Collectors.groupingBy(TransactionEntry::getBusId)
                        )
                )
        );

        // Create new empty list of trip entries to be pushed to output file.
        List<TripEntry> tripEntries = new ArrayList<>();

        // Process
        groupByPanCmpIdBusId.forEach((pan, mapOfCmpIdBusId) ->
                mapOfCmpIdBusId.forEach((cmpId, mapOfBusId) ->
                        mapOfBusId.forEach((busId, transits) -> {
                            // Sort trip entries for user using company bus on bus id by date time sequentially.
                            List<TransactionEntry> timeSortedEntries = transits.stream()
                                    .sorted(Comparator.comparing(TransactionEntry::getTransactionTime))
                                    .collect(Collectors.toList());
                            // Get 1st index record as current record.
                            TransactionEntry currentEnrty = timeSortedEntries.get(0);
                            // Iterate through list of entries made by user on bus id and create trip details.
                            for (int i = 0; i < timeSortedEntries.size() - 1; i++) {
                                TransactionEntry nextEntry = timeSortedEntries.get(i + 1);
                                // Process only when tapType is ON, skip to next entry for tapType as OFF.
                                if (currentEnrty.getTapType().equalsIgnoreCase("ON")) {
                                    if (nextEntry.getTapType().equalsIgnoreCase("OFF")) {
                                        Pair<String, String> stops = Pair.with(currentEnrty.getStopId(), nextEntry.getStopId());
                                        Double chargedAmount = calculateTripCharges(stops);
                                        if (!currentEnrty.getStopId().equalsIgnoreCase(nextEntry.getStopId())) {
                                            // Completed tip
                                            tripEntries.add(generateTripEntry(currentEnrty, nextEntry, chargedAmount, TripStatus.COMPLETED));
                                        } else {
                                            // Cancelled trip
                                            tripEntries.add(generateTripEntry(currentEnrty, nextEntry, chargedAmount, TripStatus.CANCELLED));
                                        }
                                    } else {
                                        // Incomplete tip
                                        tripEntries.add(generateIncompleteTripEntry(currentEnrty));
                                    }
                                }
                                // Shift pointer on nextEntry to currentEntry for further processing.
                                currentEnrty = nextEntry;
                            }

                            // Process last entry only if it is for incomplete trip.
                            TransactionEntry lastEnrty = timeSortedEntries.get(timeSortedEntries.size() - 1);
                            if (lastEnrty.getTapType().equalsIgnoreCase("ON")) {
                                // Incomplete tip
                                tripEntries.add(generateIncompleteTripEntry(currentEnrty));
                            }
                        })));

        return tripEntries;
    }


    /**
     * Generates trip entry details for incomplete trips. Customer will be charged maximum amount of trip
     * from current stop to any farther stop he could have travelled too.
     * Incomplete entry will use current entry details for End StopId and transaction time
     * @param currentEntry
     * @return TripEntry for Incomplete trip by customer
     */
    private TripEntry generateIncompleteTripEntry(TransactionEntry currentEntry) {
        // Incomplete tip
        Pair<String, String> stops;
        if (currentEntry.getStopId().equalsIgnoreCase("stop2")) {
            stops = Pair.with(currentEntry.getStopId(), "stop3");
        } else {
            stops = Pair.with("stop1", "stop3");
        }
        Double chargedAmount = calculateTripCharges(stops);
        return generateTripEntry(currentEntry, currentEntry, chargedAmount, TripStatus.INCOMPLETE);
    }

    /**
     * Calculate fare charges for trip between stops for Completed, Incomplete
     * and Cancelled trips.
     * @param stops
     * @return Double
     */
    private Double calculateTripCharges(Pair<String, String> stops) {
        double fareCharged;
        if (stops.containsAll(Arrays.asList("stop1", "stop2"))) {
            // Fare charged between Stop1 and Stop2 in either directions.
            fareCharged = 3.25;
        } else if (stops.containsAll(Arrays.asList("stop2", "stop3"))) {
            // Fare charged between Stop2 and Stop3 in either directions.
            fareCharged = 5.50;
        } else if (stops.containsAll(Arrays.asList("stop1", "stop3"))) {
            // Fare charged between Stop1 and Stop3 in either directions.
            fareCharged = 7.30;
        } else {
            // No Fare charged for cancelled trips.
            fareCharged = 0.0;
        }
        return fareCharged;
    }

    /**
     * Creates Trip Entry model objects to be written into output file.
     * @param currentEntry
     * @param nextEntry
     * @param chargedAmount
     * @param status
     * @return TripEntry
     */
    private TripEntry generateTripEntry(TransactionEntry currentEntry, TransactionEntry nextEntry,
                                        Double chargedAmount, TripStatus status) {
        return new TripEntry(
                currentEntry.getTransactionTime(),
                nextEntry.getTransactionTime(),
                0L, // TODO - evaluate duration.
                currentEntry.getStopId(),
                nextEntry.getStopId(),
                chargedAmount,
                currentEntry.getCompanyId(),
                currentEntry.getBusId(),
                currentEntry.getPan(),
                status
        );
    }
}
