package com.google.firebase.database.snapshot;

/* loaded from: classes4.dex */
public final class NamedNode {
    private final ChildKey name;
    private final Node node;
    private static final NamedNode MIN_NODE = new NamedNode(ChildKey.getMinName(), EmptyNode.Empty());
    private static final NamedNode MAX_NODE = new NamedNode(ChildKey.getMaxName(), Node.MAX_NODE);

    public static NamedNode getMinNode() {
        return MIN_NODE;
    }

    public static NamedNode getMaxNode() {
        return MAX_NODE;
    }

    public NamedNode(ChildKey childKey, Node node) {
        this.name = childKey;
        this.node = node;
    }

    public ChildKey getName() {
        return this.name;
    }

    public Node getNode() {
        return this.node;
    }

    public String toString() {
        return "NamedNode{name=" + this.name + ", node=" + this.node + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NamedNode namedNode = (NamedNode) obj;
        return this.name.equals(namedNode.name) && this.node.equals(namedNode.node);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.node.hashCode();
    }
}
