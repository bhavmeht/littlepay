package com.littlepay.billing;

import com.littlepay.billing.model.TransactionEntry;
import com.littlepay.billing.model.TripEntry;

import java.util.List;

public class BillCustomersMain {

    public static void main(String[] args) throws Exception {
        // Get Input data into model objects.
        List<TransactionEntry> transactionEntries = new CsvTripsReader().readTripsFromCsv("taps.csv");
        // Process data and create trip details for customer along with amount charged for trips.
        List<TripEntry> tripEntries = new EvaluateTrips().createTripEntriesToBillCustomers(transactionEntries);
        // Write trip details to output file under resource folder.
        new CsvTripsWriter().writeTripsToCsv("./src/main/resources/trips.csv", tripEntries);
    }

}
