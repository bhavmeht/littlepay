package com.littlepay.billing;

import com.littlepay.billing.model.TransactionEntry;

import java.util.*;

public class fixtures {
    public static final TransactionEntry transactionEntry1 = new TransactionEntry(
            1, Calendar.getInstance().getTime(), "ON", "Stop1", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry2 = new TransactionEntry(
            2, Calendar.getInstance().getTime(), "OFF", "Stop2", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry3 = new TransactionEntry(
            3, Calendar.getInstance().getTime(), "OFF", "Stop3", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry4 = new TransactionEntry(
            4, Calendar.getInstance().getTime(), "ON", "Stop2", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry5 = new TransactionEntry(
            5, Calendar.getInstance().getTime(), "OFF", "Stop1", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry6 = new TransactionEntry(
            6, Calendar.getInstance().getTime(), "OFF", "Stop3", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry7 = new TransactionEntry(
            7, Calendar.getInstance().getTime(), "ON", "Stop3", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry8 = new TransactionEntry(
            8, Calendar.getInstance().getTime(), "OFF", "Stop2", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry9 = new TransactionEntry(
            9, Calendar.getInstance().getTime(), "OFF", "Stop1", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry10 = new TransactionEntry(
            10, Calendar.getInstance().getTime(), "OFF", "Stop1", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry11 = new TransactionEntry(
            4, Calendar.getInstance().getTime(), "OFF", "Stop2", "Company1", "Bus37", "5500005555555559"
    );

    public static final TransactionEntry transactionEntry12 = new TransactionEntry(
            7, Calendar.getInstance().getTime(), "OFF", "Stop3", "Company1", "Bus37", "5500005555555559"
    );



}
