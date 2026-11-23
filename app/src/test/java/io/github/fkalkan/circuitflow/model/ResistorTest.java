package io.github.fkalkan.circuitflow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ResistorTest {
    private final Node a = new Node("a");
    private final Node b = new Node("b");

    @Test
    void keepsItsValues() {
        Resistor resistor = new Resistor("R1", a, b, 75);
        assertEquals("R1", resistor.id());
        assertEquals(a, resistor.nodeA());
        assertEquals(b, resistor.nodeB());
        assertEquals(75.0, resistor.ohms());
    }

    @Test
    void canBeUsedAsComponent() {
        Component c = new Resistor("R1", a, b, 75);
        assertEquals("R1", c.id());
        assertEquals(a, c.nodeA());
        assertEquals(b, c.nodeB());
    }

    @Test
    void rejectsNullId() {
        assertThrows(IllegalArgumentException.class, () -> new Resistor(null, a, b, 75));
    }

    @Test
    void rejectsBlankId() {
        assertThrows(IllegalArgumentException.class, () -> new Resistor("   ", a, b, 75));
    }

    @Test
    void rejectsNullNodeA() {
        assertThrows(IllegalArgumentException.class, () -> new Resistor("R1", null, b, 75));
    }

    @Test
    void rejectsNullNodeB() {
        assertThrows(IllegalArgumentException.class, () -> new Resistor("R1", a, null, 75));
    }

    @Test
    void rejectsSameNodeOnBothEnds() {
        assertThrows(IllegalArgumentException.class,
                () -> new Resistor("R1", new Node("a"), new Node("a"), 75));
    }

    @Test
    void rejectsZeroResistance() {
        assertThrows(IllegalArgumentException.class, () -> new Resistor("R1", a, b, 0));
    }

    @Test
    void rejectsNegativeResistance() {
        assertThrows(IllegalArgumentException.class, () -> new Resistor("R1", a, b, -75));
    }

    @Test
    void rejectsNaNResistance() {
        assertThrows(IllegalArgumentException.class, () -> new Resistor("R1", a, b, Double.NaN));
    }

    @Test
    void rejectsInfiniteResistance() {
        assertThrows(IllegalArgumentException.class,
                () -> new Resistor("R1", a, b, Double.POSITIVE_INFINITY));
    }
}
