package io.teamdev.javaclasses.impl.abstractfactory;

public interface FSMFactory {

    enum TypeFSM {
        NUMBER,
        EXPRESSION,
        EXPRESSION_WITH_BRACKETS,
        FUNCTION,
        CALCULABLE,
        BOOLEAN_EXPRESSION,
        INITIALIZATION,
        STATEMENT
    }
    Acceptor create(TypeFSM typeFSM);
}
