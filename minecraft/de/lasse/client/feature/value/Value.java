package de.lasse.client.feature.value;

public class Value {

    private ValueType valueType;
    private String valueName;

    protected Value(String name, ValueType valueType) {
        this.valueType = valueType;
        this.valueName = name;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public String getValueName() {
        return valueName;
    }
}
