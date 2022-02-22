package com.thealgorithms.dynamicprogramming;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.thealgorithms.dynamicprogramming.LongestCommonSubsequence.getLCS;
import static org.junit.jupiter.api.Assertions.*;
import com.thealgorithms.dynamicprogramming.LongestCommonSubsequence;

public class LongestCommonSubsequenceTest {

    @BeforeEach
    public void initLCS() {

    }

    @Test
    public void TestingNullStrings() {
        String text1 = null;
        String text2 = null;
        String text3 = "text3";

        assertNull(getLCS(text1,text2));
        assertNull(getLCS(text1,text3));
        assertNull(getLCS(text2,text3));
    }

    @Test
    public void TestingEmptyStrings() {
        String text1 = "";
        String text2 = "";
        String text3 = "text3";

        assertEquals(getLCS(text1,text2), "");
        assertEquals(getLCS(text1,text3), "");
        assertEquals(getLCS(text2,text3), "");
    }

    @Test
    public void TestingValidStrings() {
        String text1 = "ABCDEFGHIJKJKJKPG";
        String text2 = "AABCDIAJKSZNMV";
        String text3 = "XXXXXXXPGXXXXXXX";
        assertEquals(getLCS(text1,text2), "ABCDIJK");
        assertEquals(getLCS(text1,text3), "PG");
        assertEquals(getLCS(text2,text3), "");
    }



    @AfterAll
    public static void calculateCoverage() {
        double BranchesTaken = 0;
        for (int i = 1; i < LongestCommonSubsequence.branchCoverage.length; i++) {
            if (LongestCommonSubsequence.branchCoverage[i]!= 0) {
                BranchesTaken++;
                System.out.println("index: " + i);
            }
        }
        double percentage = BranchesTaken/(LongestCommonSubsequence.branchCoverage.length-1);
        System.out.println("Branches Taken = " + BranchesTaken);
        System.out.format("Proportion of branches taken: %.2f%%\n", percentage*100);
    }
}

