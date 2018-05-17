package de.lasse.client.feature.value.impl;

import de.lasse.client.feature.value.Value;
import de.lasse.client.feature.value.ValueType;

class ValueNumber extends Value {

    // TODO: Allow floating values
    private int currentValue;
    private int minValue;
    private int maxValue;
    private int increment;

    public ValueNumber(String name, int startValue, int minValue, int maxValue, int increment) {
        super(name, ValueType.NUMBER);
        currentValue = startValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.increment = increment;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
