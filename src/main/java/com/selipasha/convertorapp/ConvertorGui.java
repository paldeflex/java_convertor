package com.selipasha.convertorapp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConvertorGui {
    private JFrame frame;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountTextField;
    private JButton convertButton;
    private JTextField resultTextField;
    private final Font defaultFont = new Font("Arial", Font.PLAIN, 24);

    private static final List<String> ALLOWED_CURRENCIES = Arrays.asList("USD", "KZT", "EUR", "INR", "JPY", "GBP", "AUD", "CAD", "RUB", "MXN", "GEL");

    private final ConvertorApi converterApi;

    public ConvertorGui(String apiUrl, String apiKey) {
        converterApi = new ConvertorApi(apiUrl, apiKey);
        createUIComponents();
        setupUI();
    }

    private void createUIComponents() {
        frame = new JFrame("Конвертер валют");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        fromCurrencyComboBox = new JComboBox<>(ALLOWED_CURRENCIES.toArray(new String[0]));
        fromCurrencyComboBox.setFont(defaultFont);
        int usdIndex = ALLOWED_CURRENCIES.indexOf("USD");
        if (usdIndex != -1) {
            fromCurrencyComboBox.setSelectedIndex(usdIndex);
        }

        toCurrencyComboBox = new JComboBox<>(ALLOWED_CURRENCIES.toArray(new String[0]));
        toCurrencyComboBox.setFont(defaultFont);
        int rubIndex = ALLOWED_CURRENCIES.indexOf("RUB");
        if (rubIndex != -1) {
            toCurrencyComboBox.setSelectedIndex(rubIndex);
        }
        amountTextField = new JTextField();
        amountTextField.setFont(defaultFont);
        convertButton = new JButton("Конвертировать");
        convertButton.setFont(defaultFont);
        resultTextField = new JTextField();
        resultTextField.setFont(defaultFont);
        resultTextField.setEditable(false);

        convertButton.addActionListener(e -> {
            String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
            String toCurrency = (String) toCurrencyComboBox.getSelectedItem();
            int amount;

            try {
                amount = Integer.parseInt(amountTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Недопустимое значение. Введите число.");
                return;
            }

            try {
                ConvertorData data = converterApi.convert(fromCurrency, toCurrency, amount);
                resultTextField.setText(Float.toString(data.getResult()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void setupUI() {
        frame.setLayout(new GridBagLayout());
        frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Изменено с HORIZONTAL на BOTH
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0; // Добавлено
        gbc.weighty = 1.0; // Добавлено

        gbc.gridy = 0;
        JLabel fromCurrencyLabel = new JLabel("Из валюты:");
        fromCurrencyLabel.setFont(defaultFont);
        frame.add(fromCurrencyLabel, gbc);
        gbc.gridx = 1;
        frame.add(fromCurrencyComboBox, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel toCurrencyLabel = new JLabel("В валюту:");
        toCurrencyLabel.setFont(defaultFont);
        frame.add(toCurrencyLabel, gbc);
        gbc.gridx = 1;
        frame.add(toCurrencyComboBox, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel amountLabel = new JLabel("Сумма:");
        amountLabel.setFont(defaultFont);
        frame.add(amountLabel, gbc);
        gbc.gridx = 1;
        frame.add(amountTextField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel resultLabel = new JLabel("Результат:");
        resultLabel.setFont(defaultFont);
        frame.add(resultLabel, gbc);
        gbc.gridx = 1;
        frame.add(resultTextField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(convertButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}