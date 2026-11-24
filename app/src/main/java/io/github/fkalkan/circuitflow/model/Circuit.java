package io.github.fkalkan.circuitflow.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Circuit {
    private final Map<String, Node> nodes = new LinkedHashMap<>();
    private final Map<String, Component> components = new LinkedHashMap<>();
    private Node ground;

    public void addNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node must not be null");
        }

        if (nodes.containsKey(node.id())) {
            throw new IllegalArgumentException("Circuit already contains a node with id " + node.id());
        }

        nodes.put(node.id(), node);
    }

    public List<Node> getNodes() {
        return List.copyOf(nodes.values());
    }

    public void addComponent(Component component) {
        if (component == null) {
            throw new IllegalArgumentException("Component must not be null");
        }

        if (components.containsKey(component.id())) {
            throw new IllegalArgumentException("Circuit already contains a component with id " + component.id());
        }

        if (!nodes.containsKey(component.nodeA().id())) {
            throw new IllegalArgumentException(
                    "Component " + component.id() + " is connected to unknown node " + component.nodeA().id());
        }

        if (!nodes.containsKey(component.nodeB().id())) {
            throw new IllegalArgumentException(
                    "Component " + component.id() + " is connected to unknown node " + component.nodeB().id());
        }

        components.put(component.id(), component);
    }

    public List<Component> getComponents() {
        return List.copyOf(components.values());
    }

    public void removeComponent(String componentId) {
        if (!components.containsKey(componentId)) {
            throw new IllegalArgumentException("Circuit has no component with id " + componentId);
        }

        components.remove(componentId);
    }

    public void setGround(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Ground must not be null");
        }

        if (!nodes.containsKey(node.id())) {
            throw new IllegalArgumentException("Circuit has no node with id " + node.id());
        }

        ground = node;
    }

    public Node getGround() {
        return ground;
    }
}
