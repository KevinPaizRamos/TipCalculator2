package com.example.tipcalculator2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class HelloController {// formatters for currency and percentages
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    // Initialize method
    public void initialize() {
        // Set rounding mode for currency
        currency.setRoundingMode(RoundingMode.HALF_UP);

        // Bind the tip percentage label to the slider's value
        tipPercentageLabel.textProperty().bind(
                Bindings.createStringBinding(() ->
                                percent.format(tipPercentageSlider.getValue() / 100),
                        tipPercentageSlider.valueProperty()));

        // Add listener to the amount text field
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> calculate());

        // Add listener to the tip percentage slider
        tipPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            tipPercentage = BigDecimal.valueOf(newValue.doubleValue() / 100.0);
            calculate();
        });

        // Initialize calculation
        calculate();
    }

    // Method to calculate tip and total
    private void calculate() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            tipTextField.clear();
            totalTextField.clear();
        }
    }
}