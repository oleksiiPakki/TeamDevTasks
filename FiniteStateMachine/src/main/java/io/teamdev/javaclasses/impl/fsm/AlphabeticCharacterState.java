package io.teamdev.javaclasses.impl.fsm;

<<<<<<< HEAD
import io.teamdev.javaclasses.impl.abstracts.State;
=======
>>>>>>> 1357042... Implement simple calculator with basic arithmetical operations.
import org.apache.log4j.Logger;

import java.text.CharacterIterator;

/**
 * Representing the alphabetic character in the name of functions
 */
public class AlphabeticCharacterState extends State<StringBuilder> {
    private static final Logger logger = Logger.getLogger(AlphabeticCharacterState.class);

    private final boolean mayBeFinish;
    private final boolean isLexeme;

<<<<<<< HEAD
    AlphabeticCharacterState() {
        this.mayBeFinish = true;
        this.isLexeme = false;
    }

    /**
=======
    AlphabeticCharacterState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    /**
     *
>>>>>>> 1357042... Implement simple calculator with basic arithmetical operations.
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

<<<<<<< HEAD
    /**
     * Appending symbol to stringBuilder if current symbol is letter or return false.
     * or return false if it may not
     *
     * @param inputSequence  String, contains math expression
=======
    /**Appending symbol to stringBuilder if current symbol is letter or return false.
     * or return false if it may not
     *
     * @param inputSequence String, contains math expression
>>>>>>> 1357042... Implement simple calculator with basic arithmetical operations.
     * @param outputSequence StringBuilder, we appending characters of name of function
     * @return true if appending is successful or false if current symbol is not a letter .
     */
    @Override
    public boolean accept(CharacterIterator inputSequence, StringBuilder outputSequence) {

        char currentCharacter = inputSequence.current();

        if (Character.isLetter(currentCharacter)) {

            if (logger.isInfoEnabled()) {
                logger.info("Fsm in a DigitState: " + inputSequence.current() + "\n");
            }

            outputSequence.append(currentCharacter);

            inputSequence.next();

            return true;
        }

        return false;
    }
}

