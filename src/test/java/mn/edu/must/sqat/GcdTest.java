package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Lab 10, Part A: all-DU-pairs coverage for gcd().
 * Line numbers refer to DataFlowTargets.java (13 = def x,y; 15 = while; 16, 17, 18; 20 = return).
 * Pair IDs (P1..P13) are defined in the README table.
 */
class GcdTest {

    @Test
    @DisplayName("g1: gcd(7,0)=7, no loop. Covers x:13->20 (P2), y:13->15 F (P5)")
    void g1_gcd7_0_noLoop() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("g2: gcd(12,8)=4, two iterations. Covers P1,P3,P4,P6,P7,P8,P9,P10,P11,P12,P13")
    void g2_gcd12_8_twoIterations() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("g3: gcd(0,5)=5, one iteration (x=0 is used at 16). Covers P1,P4,P6,P7,P8,P9,P13")
    void g3_gcd0_5_oneIteration() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }
}
