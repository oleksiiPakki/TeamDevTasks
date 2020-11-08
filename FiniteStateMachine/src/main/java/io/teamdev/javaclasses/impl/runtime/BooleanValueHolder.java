package io.teamdev.javaclasses.impl.runtime;

public class BooleanValueHolder implements ValueHolder<Boolean>  {
    private final boolean value;

    public BooleanValueHolder(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean value() {
        return value;
    }

    @Override
    public void accept(ValueHolderVisitor visitor) {
        visitor.visit(this);
    }
}
