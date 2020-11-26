package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.State;

import java.text.CharacterIterator;

/**
 * Representing the alphabetic character in the name of functions
 */
public class AlphabeticCharacterState extends State<StringBuilder> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    AlphabeticCharacterState() {
        this.mayBeFinish = true;
        this.isLexeme = false;
    }

    /**
     * @return whether alphabetic character may be finish of name of function or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    /**
     * Appending symbol to stringBuilder if current symbol is letter or return false.
     * or return false if it may not
     *
     * @param inputSequence  String, contains math expression
     * @param outputSequence StringBuilder, we appending characters of name of function
     * @return true if appending is successful or false if current symbol is not a letter .
     */
    @Override
    public boolean accept(CharacterIterator inputSequence, StringBuilder outputSequence) {

        char currentCharacter = inputSequence.current();

        if (Character.isLetter(currentCharacter)) {



            outputSequence.append(currentCharacter);

            inputSequence.next();

            return true;
        }

        return false;
    }
}

