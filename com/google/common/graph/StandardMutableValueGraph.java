package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class StandardMutableValueGraph<N, V> extends StandardValueGraph<N, V> implements MutableValueGraph<N, V> {
    private final ElementOrder<N> incidentEdgeOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StandardMutableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        super(abstractGraphBuilder);
        this.incidentEdgeOrder = (ElementOrder<N>) abstractGraphBuilder.incidentEdgeOrder.cast();
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return this.incidentEdgeOrder;
    }

    @Override // com.google.common.graph.MutableValueGraph
    public boolean addNode(N node) {
        Preconditions.checkNotNull(node, "node");
        if (containsNode(node)) {
            return false;
        }
        addNodeInternal(node);
        return true;
    }

    private GraphConnections<N, V> addNodeInternal(N node) {
        GraphConnections<N, V> newConnections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(node, newConnections) == null);
        return newConnections;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CheckForNull
    public V putEdgeValue(N nodeU, N nodeV, V value) {
        Preconditions.checkNotNull(nodeU, "nodeU");
        Preconditions.checkNotNull(nodeV, "nodeV");
        Preconditions.checkNotNull(value, "value");
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!nodeU.equals(nodeV), "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", nodeU);
        }
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(nodeU);
        if (graphConnections == null) {
            graphConnections = addNodeInternal(nodeU);
        }
        V addSuccessor = graphConnections.addSuccessor(nodeV, value);
        GraphConnections<N, V> graphConnections2 = this.nodeConnections.get(nodeV);
        if (graphConnections2 == null) {
            graphConnections2 = addNodeInternal(nodeV);
        }
        graphConnections2.addPredecessor(nodeU, value);
        if (addSuccessor == null) {
            long j = this.edgeCount + 1;
            this.edgeCount = j;
            Graphs.checkPositive(j);
        }
        return addSuccessor;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CheckForNull
    public V putEdgeValue(EndpointPair<N> endpoints, V value) {
        validateEndpoints(endpoints);
        return putEdgeValue(endpoints.nodeU(), endpoints.nodeV(), value);
    }

    @Override // com.google.common.graph.MutableValueGraph
    public boolean removeNode(N n) {
        Preconditions.checkNotNull(n, "node");
        GraphConnections graphConnections = (GraphConnections<N, V>) this.nodeConnections.get(n);
        if (graphConnections == null) {
            return false;
        }
        if (allowsSelfLoops() && graphConnections.removeSuccessor(n) != null) {
            graphConnections.removePredecessor(n);
            this.edgeCount--;
        }
        UnmodifiableIterator it = ImmutableList.copyOf((Collection) graphConnections.successors()).iterator();
        while (it.hasNext()) {
            E next = it.next();
            GraphConnections<N, V> withoutCaching = this.nodeConnections.getWithoutCaching(next);
            Objects.requireNonNull(withoutCaching);
            withoutCaching.removePredecessor(n);
            Objects.requireNonNull(graphConnections.removeSuccessor(next));
            this.edgeCount--;
        }
        if (isDirected()) {
            UnmodifiableIterator it2 = ImmutableList.copyOf((Collection) graphConnections.predecessors()).iterator();
            while (it2.hasNext()) {
                E next2 = it2.next();
                GraphConnections<N, V> withoutCaching2 = this.nodeConnections.getWithoutCaching(next2);
                Objects.requireNonNull(withoutCaching2);
                Preconditions.checkState(withoutCaching2.removeSuccessor(n) != null);
                graphConnections.removePredecessor(next2);
                this.edgeCount--;
            }
        }
        this.nodeConnections.remove(n);
        Graphs.checkNonNegative(this.edgeCount);
        return true;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CheckForNull
    public V removeEdge(N nodeU, N nodeV) {
        Preconditions.checkNotNull(nodeU, "nodeU");
        Preconditions.checkNotNull(nodeV, "nodeV");
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(nodeU);
        GraphConnections<N, V> graphConnections2 = this.nodeConnections.get(nodeV);
        if (graphConnections == null || graphConnections2 == null) {
            return null;
        }
        V removeSuccessor = graphConnections.removeSuccessor(nodeV);
        if (removeSuccessor != null) {
            graphConnections2.removePredecessor(nodeU);
            long j = this.edgeCount - 1;
            this.edgeCount = j;
            Graphs.checkNonNegative(j);
        }
        return removeSuccessor;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CheckForNull
    public V removeEdge(EndpointPair<N> endpoints) {
        validateEndpoints(endpoints);
        return removeEdge(endpoints.nodeU(), endpoints.nodeV());
    }

    private GraphConnections<N, V> newConnections() {
        if (isDirected()) {
            return DirectedGraphConnections.of(this.incidentEdgeOrder);
        }
        return UndirectedGraphConnections.of(this.incidentEdgeOrder);
    }
}
