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

    private static final List<String> ALLOWED_CURRENCIES = Arrays.asList("USD", "KZT", "EUR", "INR", "JPY", "GBP", "AUD", "CAD", "RUB", "MXN", "GEL");

    private final ConvertorApi converterApi;

    public ConvertorGui(String apiUrl, String apiKey) {
        converterApi = new ConvertorApi(apiUrl, apiKey);
        createUIComponents();
        setupUI();
    }

    private void createUIComponents() {
        frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        Font defaultFont = new Font("Arial", Font.PLAIN, 24);

        fromCurrencyComboBox = new JComboBox<>(ALLOWED_CURRENCIES.toArray(new String[0]));
        fromCurrencyComboBox.setFont(defaultFont);
        toCurrencyComboBox = new JComboBox<>(ALLOWED_CURRENCIES.toArray(new String[0]));
        toCurrencyComboBox.setFont(defaultFont);
        amountTextField = new JTextField();
        amountTextField.setFont(defaultFont);
        convertButton = new JButton("Convert");
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
                JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a number.");
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
        frame.setLayout(new GridLayout(5, 2, 10, 10));
        frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(new JLabel("From Currency:"));
        frame.add(fromCurrencyComboBox);
        frame.add(new JLabel("To Currency:"));
        frame.add(toCurrencyComboBox);
        frame.add(new JLabel("Amount:"));
        frame.add(amountTextField);
        frame.add(new JLabel("Result:"));
        frame.add(resultTextField);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(convertButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}