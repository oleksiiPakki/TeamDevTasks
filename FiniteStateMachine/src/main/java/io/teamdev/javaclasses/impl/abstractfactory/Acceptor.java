package io.teamdev.javaclasses.impl.abstractfactory;

import io.teamdev.javaclasses.impl.fsm.Command;
import io.teamdev.javaclasses.impl.fsm.IncorrectFormatOfExpressionException;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

public interface Acceptor {
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws
                                                                            IncorrectFormatOfExpressionException;
}
