package io.github.fkalkan.circuitflow.model;

public record Node(String id) {
    public Node {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Node id must not be null or blank");
        }
    }
}
