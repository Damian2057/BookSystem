package org.example.model.Client.types;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class Type {
    private final double reduction = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        return new EqualsBuilder().append(reduction, type.reduction).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(reduction).toHashCode();
    }

    public double getReduction() {
        return reduction;
    }
}
