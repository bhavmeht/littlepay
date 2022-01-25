package com.littlepay.billing.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class TransactionEntry {
    @CsvBindByName(column = "ID")
    private Integer id;

    @CsvBindByName(column = "DateTimeUTC")
    @CsvDate("dd-MM-yyyy' 'HH:mm:ss")
    private Date transactionTime;

    @CsvBindByName(column = "TapType")
    private String tapType;

    @CsvBindByName(column = "StopId")
    private String stopId;

    @CsvBindByName(column = "CompanyId")
    private String companyId;

    @CsvBindByName(column = "BusID")
    private String busId;

    @CsvBindByName(column = "PAN")
    private String pan;

    public Integer getId() {
        return id;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public String getTapType() {
        return tapType.trim();
    }

    public String getStopId() {
        return stopId.trim().toLowerCase();
    }

    public String getCompanyId() {
        return companyId.trim();
    }

    public String getBusId() {
        return busId.trim();
    }

    public String getPan() {
        return pan.trim();
    }

    public TransactionEntry(Integer id, Date transactionTime, String tapType, String stopId, String companyId, String busId, String pan) {
        this.id = id;
        this.transactionTime = transactionTime;
        this.tapType = tapType;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
    }
}
