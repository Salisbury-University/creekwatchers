package com.dylanlarrabee.watersurveyapp;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMinMax implements InputFilter {
    private double minValue;
    private double maxValue;

    public InputFilterMinMax(double min, double max) {
        this.minValue = min;
        this.maxValue = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            double input = Double.parseDouble(dest.toString() + source.toString());
            if (isInRange(minValue, maxValue, input)) {
                return null; // Accept the input
            }
        } catch (NumberFormatException ignored) {
        }
        return ""; // Reject the input
    }

    private boolean isInRange(double a, double b, double c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
