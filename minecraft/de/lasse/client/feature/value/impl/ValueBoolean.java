package de.lasse.client.feature.value.impl;

import de.lasse.client.feature.value.Value;
import de.lasse.client.feature.value.ValueType;

class ValueBoolean extends Value {

    private boolean isEnabled;

    public ValueBoolean(String name, boolean isEnabled) {
        super(name, ValueType.BOOLEAN);
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
