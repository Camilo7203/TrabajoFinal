import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "data.csv";
    private static final String CSV_SEPARATOR = ",";

    public void writeRecord(String record) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.println(record);
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<String> readRecords() {
        List<String> records = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            while ((line = reader.readLine()) != null) {
                records.add(line);
            }

            reader.close();
            fileReader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return records;
    }

    public void updateRecord(String oldRecord, String newRecord) {
        List<String> records = readRecords();
        records.remove(oldRecord);
        records.add(newRecord);
        updateRecords(records);
    }

    public void deleteRecord(String record) {
        List<String> records = readRecords();
        records.remove(record);
        updateRecords(records);
    }

    private void updateRecords(List<String> records) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, false);
            PrintWriter writer = new PrintWriter(fileWriter);

            for (String record : records) {
                writer.println(record);
            }

            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
