package io.github.fkalkan.circuitflow.model;

public record Resistor(String id, Node nodeA, Node nodeB, double ohms) implements Component {
    public Resistor {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Resistor id must not be null or blank");
        }
        if (nodeA == null) {
            throw new IllegalArgumentException("Resistor " + id + ": nodeA must not be null");
        }
        if (nodeB == null) {
            throw new IllegalArgumentException("Resistor " + id + ": nodeB must not be null");
        }
        if (nodeA.equals(nodeB)) {
            throw new IllegalArgumentException("Resistor " + id + ": nodeA and nodeB must be different nodes");
        }
        if (!Double.isFinite(ohms) || ohms <= 0) {
            throw new IllegalArgumentException(
                    "Resistor " + id + ": resistance must be a positive number, got " + ohms);
        }
    }
}
