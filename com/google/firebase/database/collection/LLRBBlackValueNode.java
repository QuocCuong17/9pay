package com.google.firebase.database.collection;

import com.google.firebase.database.collection.LLRBNode;

/* loaded from: classes4.dex */
public class LLRBBlackValueNode<K, V> extends LLRBValueNode<K, V> {
    private int size;

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean isRed() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LLRBBlackValueNode(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        super(k, v, lLRBNode, lLRBNode2);
        this.size = -1;
    }

    @Override // com.google.firebase.database.collection.LLRBValueNode
    protected LLRBNode.Color getColor() {
        return LLRBNode.Color.BLACK;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public int size() {
        if (this.size == -1) {
            this.size = getLeft().size() + 1 + getRight().size();
        }
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.database.collection.LLRBValueNode
    public void setLeft(LLRBNode<K, V> lLRBNode) {
        if (this.size != -1) {
            throw new IllegalStateException("Can't set left after using size");
        }
        super.setLeft(lLRBNode);
    }

    @Override // com.google.firebase.database.collection.LLRBValueNode
    protected LLRBValueNode<K, V> copy(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        if (k == null) {
            k = getKey();
        }
        if (v == null) {
            v = getValue();
        }
        if (lLRBNode == null) {
            lLRBNode = getLeft();
        }
        if (lLRBNode2 == null) {
            lLRBNode2 = getRight();
        }
        return new LLRBBlackValueNode(k, v, lLRBNode, lLRBNode2);
    }
}
