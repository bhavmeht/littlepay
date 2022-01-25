package com.littlepay.billing;

import com.littlepay.billing.model.TransactionEntry;
import com.littlepay.billing.model.TripEntry;

import com.littlepay.billing.model.TripStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluateTripsTest {

    private final EvaluateTrips evalTrips = new EvaluateTrips();

    @Test
    @DisplayName("Completed trip between Stop1 and Stop 2")
    void createTripEntriesToBillCustomers_completed_stop1_stop2() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry1, fixtures.transactionEntry2));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(3.25, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.COMPLETED, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Completed trip between Stop1 and Stop3")
    void createTripEntriesToBillCustomers_completed_stop1_stop3() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry1, fixtures.transactionEntry3));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(7.30, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.COMPLETED, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Completed trip between Stop2 and Stop1")
    void createTripEntriesToBillCustomers_completed_stop2_stop1() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry4, fixtures.transactionEntry5));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(3.25, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.COMPLETED, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Completed trip between Stop2 and Stop3")
    void createTripEntriesToBillCustomers_completed_stop2_stop3() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry4, fixtures.transactionEntry6));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(5.50, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.COMPLETED, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Completed trip between Stop3 and Stop2")
    void createTripEntriesToBillCustomers_completed_stop3_stop2() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry7, fixtures.transactionEntry8));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(5.50, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.COMPLETED, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Completed trip between Stop3 and Stop1")
    void createTripEntriesToBillCustomers_completed_stop3_stop1() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry7, fixtures.transactionEntry9));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(7.30, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.COMPLETED, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Incomplete trip from Stop1")
    void createTripEntriesToBillCustomers_incomplete_stop1() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry1));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(7.30, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.INCOMPLETE, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Incomplete trip from Stop2")
    void createTripEntriesToBillCustomers_incomplete_stop2() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry4));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(5.50, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.INCOMPLETE, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Incomplete trip from Stop3")
    void createTripEntriesToBillCustomers_incomplete_stop3() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry7));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(7.30, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.INCOMPLETE, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Cancelled trip from Stop1")
    void createTripEntriesToBillCustomers_cancelled_stop1() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry1, fixtures.transactionEntry10));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(0, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.CANCELLED, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Cancelled trip from Stop2")
    void createTripEntriesToBillCustomers_cancelled_stop2() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry4, fixtures.transactionEntry11));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(0, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.CANCELLED, tripEntries.get(0).getStatus());
    }

    @Test
    @DisplayName("Cancelled trip from Stop3")
    void createTripEntriesToBillCustomers_cancelled_stop3() {
        List<TransactionEntry> transactionEntries = new ArrayList<>(Arrays.asList(fixtures.transactionEntry7, fixtures.transactionEntry12));
        List<TripEntry> tripEntries = evalTrips.createTripEntriesToBillCustomers(transactionEntries);
        assertNotNull(tripEntries);
        assertTrue(tripEntries.size() == 1);
        assertEquals(0, tripEntries.get(0).getChargeAmount());
        assertEquals(TripStatus.CANCELLED, tripEntries.get(0).getStatus());
    }

}
