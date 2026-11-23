package io.github.fkalkan.circuitflow.model;

public record VoltageSource(String id, Node positive, Node negative, double volts) implements Component {
    public VoltageSource {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("VoltageSource id must not be null or blank");
        }
        if (positive == null) {
            throw new IllegalArgumentException("VoltageSource " + id + ": positive must not be null");
        }
        if (negative == null) {
            throw new IllegalArgumentException("VoltageSource " + id + ": negative must not be null");
        }
        if (positive.equals(negative)) {
            throw new IllegalArgumentException(
                    "VoltageSource " + id + ": positive and negative must be different nodes");
        }
        if (!Double.isFinite(volts)) {
            throw new IllegalArgumentException(
                    "VoltageSource " + id + ": voltage must be a finite number, got " + volts);
        }
    }

    @Override
    public Node nodeA() {
        return positive;
    }

    @Override
    public Node nodeB() {
        return negative;
    }
}
