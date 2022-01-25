package com.littlepay.billing.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class TripEntry {
    @CsvBindByName(column = "Started")
    //@CsvBindByPosition(position = 0)
    @CsvDate("dd-MM-yyyy' 'HH:mm:ss")
    private Date started;

    @CsvBindByName(column = "Finished")
    //@CsvBindByPosition(position = 1)
    @CsvDate("dd-MM-yyyy' 'HH:mm:ss")
    private Date finished;

    @CsvBindByName(column = "DurationSecs")
    //@CsvBindByPosition(position = 2)
    private Long durationSecs;

    @CsvBindByName(column = "FromStopId")
    private String fromStopId;

    @CsvBindByName(column = "ToStopId")
    //@CsvBindByPosition(position = 3)
    private String toStopId;

    @CsvBindByName(column = "ChargeAmount")
    //@CsvBindByPosition(position = 4)
    private Double chargeAmount;

    @CsvBindByName(column = "CompanyId")
    //@CsvBindByPosition(position = 5)
    private String companyId;

    @CsvBindByName(column = "BusID")
    //@CsvBindByPosition(position = 6)
    private String busId;

    @CsvBindByName(column = "PAN")
    //@CsvBindByPosition(position = 7)
    private String pan;

    @CsvBindByName(column = "Status")
    //@CsvBindByPosition(position = 8)
    private TripStatus status;

    public TripEntry(Date started, Date finished, Long durationSecs, String fromStopId, String toStopId, Double chargeAmount, String companyId, String busId, String pan, TripStatus status) {
        this.started = started;
        this.finished = finished;
        this.durationSecs = durationSecs;
        this.fromStopId = fromStopId;
        this.toStopId = toStopId;
        this.chargeAmount = chargeAmount;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
        this.status = status;
    }

    public Date getStarted() {
        return started;
    }

    public Date getFinished() {
        return finished;
    }

    public Long getDurationSecs() {
        return durationSecs;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getBusId() {
        return busId;
    }

    public String getPan() {
        return pan;
    }

    public TripStatus getStatus() {
        return status;
    }
}
