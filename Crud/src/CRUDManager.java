import java.util.List;

public class CRUDManager {
    private FileManager fileManager;

    public CRUDManager() {
        fileManager = new FileManager();
    }

    public void addRecord(String name, String age, String id) {
        String record = name + "," + age + "," + id;
        fileManager.writeRecord(record);
    }

    public String readRecords() {
        List<String> records = fileManager.readRecords();

        if (records.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String record : records) {
            sb.append(record).append("\n");
        }

        return sb.toString();
    }

    public void updateRecord(String selectedRecord, String updatedName, String updatedAge, String updatedId) {
        String updatedRecord = updatedName + "," + updatedAge + "," + updatedId;
        fileManager.updateRecord(selectedRecord, updatedRecord);
    }

    public void deleteRecord(String record) {
        fileManager.deleteRecord(record);
    }
}
