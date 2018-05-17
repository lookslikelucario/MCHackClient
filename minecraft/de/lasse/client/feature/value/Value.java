package de.lasse.client.feature.value;

public class Value {

    private final ValueType valueType;
    private final String valueName;

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
