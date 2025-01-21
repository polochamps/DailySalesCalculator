package dailysales;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class DailySales {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Daily Sales Calculation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Phone Sales Panel
        JPanel phonePanel = new JPanel(new GridBagLayout());
        phonePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Phone Sales",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Phone Fields
        JTextField phoneField = new JTextField("10");
        JTextField phonePriceField = new JTextField("500");
        addField(phonePanel, "Phones Sold:", phoneField, gbc, 0);
        addField(phonePanel, "Price per Phone (₱):", phonePriceField, gbc, 1);

        // Repair Sales Panel
        JPanel repairPanel = new JPanel(new GridBagLayout());
        repairPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Repair Services",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        // Repair Fields
        JTextField repairField = new JTextField("5");
        JTextField repairPriceField = new JTextField("50");
        addField(repairPanel, "Repair Hours:", repairField, gbc, 0);
        addField(repairPanel, "Price per Hour (₱):", repairPriceField, gbc, 1);

        // Results Area
        JTextArea resultArea = new JTextArea(4, 25);
        resultArea.setEditable(false);
        resultArea.setMargin(new Insets(5, 5, 5, 5));
        resultArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 0, 10, 0),
            BorderFactory.createLineBorder(Color.GRAY)
        ));

        // Calculate Button
        JButton calculateButton = new JButton("Calculate Sales");
        calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculateButton.setMaximumSize(new Dimension(150, 30));

        calculateButton.addActionListener(e -> {
            try {
                int phoneQuantity = Integer.parseInt(phoneField.getText());
                double phonePrice = Double.parseDouble(phonePriceField.getText());
                PhoneSales phoneSales = new PhoneSales("Phone", phonePrice, phoneQuantity);

                int repairHours = Integer.parseInt(repairField.getText());
                double repairPrice = Double.parseDouble(repairPriceField.getText());
                RepairSales repairSales = new RepairSales("Repair", repairPrice, repairHours);

                double phoneTotal = phoneSales.calculateTotal();
                double repairTotal = repairSales.calculateTotal();
                double grandTotal = phoneTotal + repairTotal;

                resultArea.setText(String.format(
                    "Sales Summary:\n\n" +
                    "Phone Sales: ₱%,.2f\n" +
                    "Repair Services: ₱%,.2f\n" +
                    "Total Revenue: ₱%,.2f",
                    phoneTotal, repairTotal, grandTotal
                ));
            } catch (NumberFormatException ex) {
                resultArea.setText("Error: Please enter valid numeric values.");
                JOptionPane.showMessageDialog(frame,
                    "Please enter valid numbers in all fields.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components to main panel
        mainPanel.add(phonePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(repairPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(calculateButton);
        mainPanel.add(scrollPane);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addField(JPanel panel, String labelText, JTextField field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }
}