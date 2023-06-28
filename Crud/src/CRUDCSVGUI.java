import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CRUDCSVGUI extends JFrame {
    private JTextField nameField, ageField, idField;
    private CRUDManager crudManager;

    public CRUDCSVGUI() {
        setTitle("CRUD CSV");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        crudManager = new CRUDManager();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Nombre:");
        nameField = new JTextField();
        JLabel ageLabel = new JLabel("Edad:");
        ageField = new JTextField();
        JLabel idLabel = new JLabel("Cédula:");
        idField = new JTextField();

        JButton addButton = new JButton("Añadir registro");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });

        JButton readButton = new JButton("Leer registros");
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readRecords();
            }
        });

        JButton updateButton = new JButton("Actualizar registro");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });

        JButton deleteButton = new JButton("Borrar registro");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(addButton);
        panel.add(readButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        bottomPanel.add(updateButton);
        bottomPanel.add(deleteButton);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addRecord() {
        String name = nameField.getText();
        String age = ageField.getText();
        String id = idField.getText();

        if (age.isEmpty()) {
            age = "No data";
        }

        crudManager.addRecord(name, age, id);

        clearFields();
    }

    private void readRecords() {
        String records = crudManager.readRecords();
        JOptionPane.showMessageDialog(this, records, "Registros", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateRecord() {
        String records = crudManager.readRecords();

        if (records.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay registros disponibles.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String selectedRecord = (String) JOptionPane.showInputDialog(this, "Seleccione el registro a actualizar:", "Actualizar registro", JOptionPane.QUESTION_MESSAGE, null, records.split("\n"), records.split("\n")[0]);

        if (selectedRecord != null) {
            String[] parts = selectedRecord.split(",");
            String name = parts[0];
            String age = parts[1];
            String id = parts[2];

            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new GridLayout(4, 2, 10, 10));
            updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel("Nombre:");
            JTextField nameField = new JTextField(name);
            JLabel ageLabel = new JLabel("Edad:");
            JTextField ageField = new JTextField(age);
            JLabel idLabel = new JLabel("Cédula:");
            JTextField idField = new JTextField(id);

            updatePanel.add(nameLabel);
            updatePanel.add(nameField);
            updatePanel.add(ageLabel);
            updatePanel.add(ageField);
            updatePanel.add(idLabel);
            updatePanel.add(idField);

            int option = JOptionPane.showConfirmDialog(this, updatePanel, "Actualizar registro", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String updatedName = nameField.getText();
                String updatedAge = ageField.getText();
                String updatedId = idField.getText();

                if (updatedAge.isEmpty()) {
                    updatedAge = "No data";
                }

                crudManager.updateRecord(selectedRecord, updatedName, updatedAge, updatedId);
            }
        }
    }

    private void deleteRecord() {
        String records = crudManager.readRecords();

        if (records.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay registros disponibles.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String selectedRecord = (String) JOptionPane.showInputDialog(this, "Seleccione el registro a borrar:", "Borrar registro", JOptionPane.QUESTION_MESSAGE, null, records.split("\n"), records.split("\n")[0]);

        if (selectedRecord != null) {
            crudManager.deleteRecord(selectedRecord);
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        idField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CRUDCSVGUI gui = new CRUDCSVGUI();
                gui.setVisible(true);
            }
        });
    }
}
