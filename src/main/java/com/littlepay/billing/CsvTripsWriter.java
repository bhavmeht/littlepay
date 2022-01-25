package com.littlepay.billing;

import com.littlepay.billing.model.TripEntry;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.*;
import java.util.List;

public class CsvTripsWriter {

    /**
     * Writes trip details with charged amount to customer into csv file under resource folder.
     * It uses Open CSV utility to write model objects directly to output file.
     * Output file will be deleted and recreated on every run.
     * @param fileName
     * @param tripEntries
     * @throws Exception
     */
    public void writeTripsToCsv(String fileName, List<TripEntry> tripEntries) throws Exception {
        try (BufferedWriter bw = createFile(fileName)) {
            StatefulBeanToCsv<TripEntry> csvWriter = new StatefulBeanToCsvBuilder<TripEntry>(bw)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .withOrderedResults(true)
                    .build();

            // write trips to CSV
            csvWriter.write(tripEntries);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Creates Buffere writer object for IO.
     * @param filename
     * @return
     * @throws Exception
     */
    private BufferedWriter createFile(String filename) throws Exception {
        File file = new File(filename);
        if (file.exists()) file.delete();
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        OutputStreamWriter streamWriter = new OutputStreamWriter(fileOutputStream);
        return new BufferedWriter(streamWriter);
    }

}
