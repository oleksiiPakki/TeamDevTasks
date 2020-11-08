package io.teamdev.javaclasses.impl.runtime;

public interface ValueHolder<T> {
    T value();

    void accept(ValueHolderVisitor visitor);
}
