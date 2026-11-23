package io.github.fkalkan.circuitflow.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    @Test
    void keepsItsId() {
        Node node = new Node("vin");
        assertEquals("vin", node.id());
    }

    @Test
    void nodesWithSameIdAreEqual() {
        Node node1 = new Node("vin");
        Node node2 = new Node("vin");
        assertEquals(node1, node2);
    }

    @Test
    void rejectsBlankId() {
        assertThrows(IllegalArgumentException.class, () -> new Node("   "));
    }

    @Test
    void rejectsNullId() {
        assertThrows(IllegalArgumentException.class, () -> new Node(null));
    }

    @Test
    void nodesWithDifferentIdsAreNotEqual() {
        Node node1 = new Node("vin");
        Node node2 = new Node("vout");
        assertNotEquals(node1, node2);
    }
}
