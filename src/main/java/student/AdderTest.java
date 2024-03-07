package student;

import com.spertus.jacquard.common.Visibility;
import com.spertus.jacquard.junittester.GradedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdderTest {
    private final Adder adder = new Adder();

    @Test
    @GradedTest(name = "testAddZero()", points = 1.0, visibility = Visibility.VISIBLE)
    public void testAddZero() {
        assertEquals(0, adder.add(0, 0));
        assertEquals(5, adder.add(0, 5));
        assertEquals(5, adder.add(5, 0));
    }

    @Test
    @GradedTest
    public void testAddEqualNumbers() {
        assertEquals(2, adder.add(1, 1));
        assertEquals(40, adder.add(20, 20));
    }

    @Test
    @GradedTest
    public void testAddBigNumbers() {
        assertEquals(1_000_000, adder.add(500_001, 499_999));
    }

    @Test
    @GradedTest
    public void testAddNegativeNumbers() {
        assertEquals(-3, adder.add(-1, -2));
    }
}
