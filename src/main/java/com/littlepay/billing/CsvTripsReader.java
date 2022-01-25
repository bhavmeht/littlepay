package com.littlepay.billing;

import java.io.*;
import java.util.List;

import com.littlepay.billing.model.TransactionEntry;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

public class CsvTripsReader {

    /**
     * Reads CSV file from resource folder and creates model objects for billing processing.
     * It uses Open CVS utility for reading and applying data to model objects by parsing data.
     * @param fileName
     * @return List<TransactionEntry> model objects from input
     * @throws Exception
     */
    public List<TransactionEntry> readTripsFromCsv(String fileName) throws Exception {
        try (BufferedReader br = getFile(fileName)) {
            return new CsvToBeanBuilder<TransactionEntry>(br)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .withType(TransactionEntry.class).build().parse();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Creates Reader objects for IO.
     * @param filename
     * @return
     * @throws Exception
     */
    private BufferedReader getFile(String filename) throws Exception {
        InputStream classLoader = getClass().getClassLoader().getResourceAsStream(filename);
        Reader reader = new InputStreamReader(classLoader, StandardCharsets.US_ASCII);
        return new BufferedReader(reader);
    }

}
