package io.github.fkalkan.circuitflow.model;

public interface Component {
    String id();

    Node nodeA();

    Node nodeB();
}
