package com.thealgorithms.others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CRCAlgorithmTest {
    @Test
    void length8CRCNoErrorsTest() {
        String p = "10101010"; // length 9 bitString for the 8th degree polynomial
        int size = 32; // each random message is 32 bits long
        double bitErrorRate = 0;
        CRCAlgorithm c = new CRCAlgorithm(p, size, bitErrorRate);
        c.generateRandomMess();
        c.divideMessageWithP(false); // sender, append divider to end of message
        c.changeMess(); // should not alter any bits, since error rate is 0
        c.divideMessageWithP(true); // receiver, check that the algorithm leaves residual divider 0
        assertTrue(c.getCorrectMess() == 1); // since error rate is 0, will be correct
    }

    @Test
    void length8CRCOnlyErrorsTest() {
        String p = "101010101"; // length 9 bitString for the 8th degree polynomial
        int size = 34; // each random message is 34 bits long
        double bitErrorRate = 1;
        CRCAlgorithm c = new CRCAlgorithm(p, size, bitErrorRate);
        c.generateRandomMess();
        c.divideMessageWithP(false); // sender, append divider to end of message
        c.changeMess(); // should alter all bits, since error rate is 1
        c.divideMessageWithP(true); // receiver, should detect the residual divider contains ones
        assertTrue(c.getCorrectMess() == 0);
        assertTrue(c.getWrongMess() == 1);
    }

    /**
     * Optimally we should test for the case message was changed, but it was not detected by the
     * algorithm. This is hard since we do not have an interface for setting message input manually.
     * If we could then it is probably possible to have a string which when completely flipped, is
     * still valid according to the check algorithm, although the content is actually erroneous.
     */
}
