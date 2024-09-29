import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// UI
// daļēja iedvesma/pamācība ņemta no interneta, sakarā ar Swing priekš vienkāršā 
// GUI un dažām rindiņām! <3

public class ExpenseManagerGUI extends JFrame {
    private JTextField dateField;
    private JTextField amountField;
    private JTextField descriptionField;
    private JTextArea displayArea;
    private DatabaseManager dbManager;

    public ExpenseManagerGUI() {
        dbManager = new DatabaseManager();

        // title, lodziņš
        setTitle("izdevumi.exe");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // datums
        JLabel dateLabel = new JLabel("Datums:");
        dateLabel.setBounds(10, 10, 100, 20);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(120, 10, 150, 20);
        add(dateField);

        // summa
        JLabel amountLabel = new JLabel("Summa:");
        amountLabel.setBounds(10, 40, 100, 20);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(120, 40, 150, 20);
        add(amountField);

        // apraksts
        JLabel descriptionLabel = new JLabel("Apraksts:");
        descriptionLabel.setBounds(10, 70, 100, 20);
        add(descriptionLabel);

        descriptionField = new JTextField();
        descriptionField.setBounds(120, 70, 150, 20);
        add(descriptionField);

        // pievienot
        JButton addButton = new JButton("PIEVIENOT");
        addButton.setBounds(10, 100, 150, 30);
        add(addButton);

        // apskatīt
        JButton viewButton = new JButton("APSKATĪT");
        viewButton.setBounds(10, 140, 150, 30);
        add(viewButton);

        JButton totalButton = new JButton("KOPĒJĀ SUMMA");
        totalButton.setBounds(170, 100, 150, 30);
        add(totalButton);

        // display lodzins
        displayArea = new JTextArea();
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(10, 180, 360, 80);
        add(scrollPane);

        // saglabāšana
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String description = descriptionField.getText();

                Expense expense = new Expense(date, amount, description);
                dbManager.addExpense(expense);
                displayArea.setText("Izdevums saglabāts!");
            }
        });

        // saglabatais info
        JLabel sortLabel = new JLabel("Kārtot pēc:");
        sortLabel.setBounds(170, 140, 100, 20);
        add(sortLabel);

        String[] sortOptions = {"-", "Datums", "Summa"};
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.setBounds(240, 140, 100, 30);
        add(sortComboBox);

        // kārtošana
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSort = (String) sortComboBox.getSelectedItem();
                String orderBy = null;

                if ("Datums".equals(selectedSort)) {
                    orderBy = "datums";
                } else if ("Summa".equals(selectedSort)) {
                    orderBy = "summa";
                }

                List<Expense> expenses = dbManager.getExpenses(orderBy);
                displayArea.setText("");
                for (Expense expense : expenses) {
                    displayArea.append(expense.toString() + "\n");
                }
            }
        });

        // kopējie izdevumi
        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = dbManager.getTotalExpenses();
                displayArea.setText("Kopējie izdevumi: " + total + " EUR");
            }
        });

        setVisible(true);
    }
}
