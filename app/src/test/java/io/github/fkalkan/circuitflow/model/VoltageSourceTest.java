package io.github.fkalkan.circuitflow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VoltageSourceTest {
    private final Node vin = new Node("vin");
    private final Node gnd = new Node("gnd");

    @Test
    void keepsItsValues() {
        VoltageSource source = new VoltageSource("V1", vin, gnd, 10);
        assertEquals("V1", source.id());
        assertEquals(vin, source.positive());
        assertEquals(gnd, source.negative());
        assertEquals(10.0, source.volts());
    }

    @Test
    void canBeUsedAsComponent() {
        Component c = new VoltageSource("V1", vin, gnd, 10);
        assertEquals("V1", c.id());
        assertEquals(vin, c.nodeA());
        assertEquals(gnd, c.nodeB());
    }

    @Test
    void acceptsNegativeVoltage() {
        VoltageSource source = new VoltageSource("V1", vin, gnd, -5);
        assertEquals(-5.0, source.volts());
    }

    @Test
    void acceptsZeroVoltage() {
        VoltageSource source = new VoltageSource("V1", vin, gnd, 0);
        assertEquals(0.0, source.volts());
    }

    @Test
    void rejectsNullId() {
        assertThrows(IllegalArgumentException.class, () -> new VoltageSource(null, vin, gnd, 10));
    }

    @Test
    void rejectsBlankId() {
        assertThrows(IllegalArgumentException.class, () -> new VoltageSource("   ", vin, gnd, 10));
    }

    @Test
    void rejectsNullPositive() {
        assertThrows(IllegalArgumentException.class, () -> new VoltageSource("V1", null, gnd, 10));
    }

    @Test
    void rejectsNullNegative() {
        assertThrows(IllegalArgumentException.class, () -> new VoltageSource("V1", vin, null, 10));
    }

    @Test
    void rejectsSameNodeOnBothEnds() {
        assertThrows(IllegalArgumentException.class,
                () -> new VoltageSource("V1", new Node("vin"), new Node("vin"), 10));
    }

    @Test
    void rejectsNaNVoltage() {
        assertThrows(IllegalArgumentException.class, () -> new VoltageSource("V1", vin, gnd, Double.NaN));
    }

    @Test
    void rejectsInfiniteVoltage() {
        assertThrows(IllegalArgumentException.class,
                () -> new VoltageSource("V1", vin, gnd, Double.POSITIVE_INFINITY));
    }
}
