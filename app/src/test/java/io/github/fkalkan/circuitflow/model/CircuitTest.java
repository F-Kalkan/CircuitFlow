package io.github.fkalkan.circuitflow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class CircuitTest {
    @Test
    void newCircuitHasNoNodes() {
        Circuit circuit = new Circuit();
        assertTrue(circuit.getNodes().isEmpty());
    }

    @Test
    void containsAddedNode() {
        Circuit circuit = new Circuit();
        Node n1 = new Node("N1");
        circuit.addNode(n1);
        assertTrue(circuit.getNodes().contains(n1));
    }

    @Test
    void rejectsDuplicateNodeId() {
        Circuit circuit = new Circuit();
        Node n1 = new Node("N1");
        Node n2 = new Node("N1");
        circuit.addNode(n1);
        assertThrows(IllegalArgumentException.class,
                () -> circuit.addNode(n1));
        assertThrows(IllegalArgumentException.class,
                () -> circuit.addNode(n2));
        assertEquals(List.of(n1), circuit.getNodes());
    }

    @Test
    void rejectsNullNode() {
        Circuit circuit = new Circuit();
        assertThrows(IllegalArgumentException.class,
                () -> circuit.addNode(null));
    }

    @Test
    void nodeListIsReadOnly() {
        Circuit circuit = new Circuit();
        Node n1 = new Node("N1");
        assertThrows(UnsupportedOperationException.class,
                () -> (circuit.getNodes()).add(n1));
    }

    @Test
    void newCircuitHasNoComponents() {
        Circuit circuit = new Circuit();
        assertTrue(circuit.getComponents().isEmpty());
    }

    @Test
    void containsAddedComponent() {
        Circuit circuit = new Circuit();
        Node n1 = new Node("N1");
        circuit.addNode(n1);
        Node n2 = new Node("N2");
        circuit.addNode(n2);
        Resistor r1 = new Resistor("R1", n1, n2, 20);
        circuit.addComponent(r1);
        assertTrue(circuit.getComponents().contains(r1));
    }

    @Test
    void rejectsDuplicateComponentId() {
        Circuit circuit = new Circuit();
        Node n1 = new Node("N1");
        circuit.addNode(n1);
        Node n2 = new Node("N2");
        circuit.addNode(n2);
        Resistor r1 = new Resistor("R1", n1, n2, 20);
        Resistor r2 = new Resistor("R1", n1, n2, 20);
        circuit.addComponent(r1);
        assertThrows(IllegalArgumentException.class,
                () -> circuit.addComponent(r2));
    }

    @Test
    void rejectsComponentWithUnknownNode() {
        Circuit circuit = new Circuit();
        Node n1 = new Node("N1");
        circuit.addNode(n1);
        Node n2 = new Node("N2");
        Resistor r1 = new Resistor("R1", n1, n2, 20);
        assertThrows(IllegalArgumentException.class,
                () -> circuit.addComponent(r1));
    }

    @Test
    void rejectsNullComponent() {
        Circuit circuit = new Circuit();
        assertThrows(IllegalArgumentException.class,
                () -> circuit.addComponent(null));
    }

    @Test
    void removesComponent() {
        Circuit circuit = new Circuit();
        Node n1 = new Node("N1");
        circuit.addNode(n1);
        Node n2 = new Node("N2");
        circuit.addNode(n2);
        Resistor r1 = new Resistor("R1", n1, n2, 20);
        circuit.addComponent(r1);
        circuit.removeComponent("R1");
        assertTrue(circuit.getComponents().isEmpty());
        assertEquals(List.of(n1, n2), circuit.getNodes());
    }

    @Test
    void rejectsRemovingUnknownComponent() {
        Circuit circuit = new Circuit();
        assertThrows(IllegalArgumentException.class,
                () -> circuit.removeComponent("R1"));
    }

    @Test
    void newCircuitHasNoGround() {
        Circuit circuit = new Circuit();
        assertNull(circuit.getGround());
    }

    @Test
    void setsGround() {
        Circuit circuit = new Circuit();
        Node n1 = new Node("N1");
        circuit.addNode(n1);
        circuit.setGround(n1);
        assertEquals(n1, circuit.getGround());
    }

    @Test
    void rejectsUnknownGround() {
        Circuit circuit = new Circuit();
        Node n1 = new Node("N1");
        assertThrows(IllegalArgumentException.class,
                () -> circuit.setGround(n1));
    }

    @Test
    void rejectsNullGround() {
        Circuit circuit = new Circuit();
        assertThrows(IllegalArgumentException.class,
                () -> circuit.setGround(null));
    }
}
