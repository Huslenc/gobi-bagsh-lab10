package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Lab 10, Part B: stage 1, ALL-DEFS (2 tests).
 * Line numbers refer to DataFlowTargets.java. Pair IDs (Q1..Q33) are defined in the README DU table.
 */
class BonusTest {

    @Test
    @DisplayName("b01: streak of 5, a break, then 1 more; threshold 3 -> 8. Covers Q1,Q3,Q4,Q5,Q8,Q9,Q12,Q14,Q15,Q16,Q17,Q18,Q19,Q20,Q21,Q23,Q24,Q26,Q27,Q28,Q31,Q33")
    void b01_longStreakBreak() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 0, 1}, 3));
    }

    @Test
    @DisplayName("b02: present 1 < threshold 5 -> score:49 reaches return(56) -> 0. Covers Q1,Q3,Q4,Q5,Q7,Q9,Q13,Q15,Q18,Q19,Q21,Q25,Q27,Q28,Q30")
    void b02_belowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {1, 0}, 5));
    }
}
