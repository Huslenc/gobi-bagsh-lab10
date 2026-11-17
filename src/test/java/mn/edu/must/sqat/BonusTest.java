package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Lab 10, Part B: final: all-DU-pairs plus the b15 test that kills mutant M2.
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

    @Test
    @DisplayName("b03: null array -> IllegalArgumentException (attendance p-use at 32 = T). Covers Q2")
    void b03_nullArray() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 1));
    }

    @Test
    @DisplayName("b04: threshold < 0 -> IllegalArgumentException (threshold p-use at 32 = T). Covers Q1,Q6")
    void b04_negativeThreshold() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[] {1}, -1));
    }

    @Test
    @DisplayName("b05: empty array, threshold 0 -> present 0 >= 0 -> 5. Covers Q1,Q3,Q5,Q8,Q11,Q22,Q32")
    void b05_emptyThresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {}, 0));
    }

    @Test
    @DisplayName("b06: empty array, threshold 1 -> 0. Covers Q1,Q3,Q5,Q7,Q10,Q22,Q30")
    void b06_emptyThresholdOne() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {}, 1));
    }

    @Test
    @DisplayName("b07: all absent -> loop never enters the 40-43 branch -> 0. Covers Q1,Q3,Q4,Q5,Q7,Q10,Q22,Q27,Q30")
    void b07_allAbsent() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {0, 0, 0}, 1));
    }

    @Test
    @DisplayName("b08: present == threshold (boundary) -> 5. Covers Q1,Q3,Q4,Q5,Q8,Q9,Q12,Q14,Q15,Q16,Q18,Q19,Q21,Q24,Q25,Q27,Q28,Q32")
    void b08_exactlyThreshold() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 0}, 2));
    }

    @Test
    @DisplayName("b09: present = threshold - 1 -> 0. Covers Q1,Q3,Q4,Q5,Q7,Q9,Q13,Q15,Q18,Q19,Q21,Q25,Q28,Q30")
    void b09_oneBelowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {1}, 2));
    }

    @Test
    @DisplayName("b10: streak 4 (< 5) -> no streak bonus -> 5. Covers Q1,Q3,Q4,Q5,Q8,Q9,Q12,Q14,Q15,Q16,Q18,Q19,Q21,Q24,Q25,Q28,Q32")
    void b10_streakOfFour() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 1, 1}, 3));
    }

    @Test
    @DisplayName("b11: after the break the streak (1) is not > best (2) -> 5. Covers Q1,Q3,Q4,Q5,Q8,Q9,Q12,Q14,Q15,Q16,Q17,Q18,Q19,Q20,Q21,Q23,Q24,Q25,Q27,Q28,Q32")
    void b11_breakThenShorter() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 0, 1}, 1));
    }

    @Test
    @DisplayName("b12: streak longer than 5 -> 8. Covers Q1,Q3,Q4,Q5,Q8,Q9,Q12,Q14,Q15,Q16,Q18,Q19,Q21,Q24,Q26,Q28,Q31,Q33")
    void b12_streakOfSix() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 1}, 3));
    }

    @Test
    @DisplayName("b13: present 5 < threshold 6 but streak 5 -> only the +3 bonus (score:49 -> 54). Covers Q1,Q3,Q4,Q5,Q7,Q9,Q12,Q13,Q15,Q16,Q18,Q19,Q21,Q24,Q26,Q28,Q29,Q33")
    void b13_streakFiveBelowThreshold() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 6));
    }

    @Test
    @DisplayName("b14: absence first, then presence (streak:46 -> 41) -> 5. Covers Q1,Q3,Q4,Q5,Q8,Q9,Q14,Q18,Q19,Q20,Q21,Q25,Q27,Q28,Q32")
    void b14_leadingAbsence() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {0, 1}, 1));
    }

    @Test
    @DisplayName("b15: present = 5 but the longest streak is only 3 -> 5 (no streak bonus); kills mutant M2 (best = present).")
    void b15_m2Killer_presentNotStreak() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 1, 0, 1, 1}, 1));
    }
}
