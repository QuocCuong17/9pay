package com.google.firebase.database.collection;

import com.google.firebase.database.collection.LLRBNode;
import java.util.Comparator;

/* loaded from: classes4.dex */
public class LLRBEmptyNode<K, V> implements LLRBNode<K, V> {
    private static final LLRBEmptyNode INSTANCE = new LLRBEmptyNode();

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> copy(K k, V v, LLRBNode.Color color, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        return this;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public K getKey() {
        return null;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> getLeft() {
        return this;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> getMax() {
        return this;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> getMin() {
        return this;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> getRight() {
        return this;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public V getValue() {
        return null;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> nodeVisitor) {
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean isEmpty() {
        return true;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean isRed() {
        return false;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> remove(K k, Comparator<K> comparator) {
        return this;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean shortCircuitingInOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor) {
        return true;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean shortCircuitingReverseOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor) {
        return true;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public int size() {
        return 0;
    }

    public static <K, V> LLRBEmptyNode<K, V> getInstance() {
        return INSTANCE;
    }

    private LLRBEmptyNode() {
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public LLRBNode<K, V> insert(K k, V v, Comparator<K> comparator) {
        return new LLRBRedValueNode(k, v);
    }
}
