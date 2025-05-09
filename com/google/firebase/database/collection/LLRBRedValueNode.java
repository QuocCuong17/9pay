package com.google.firebase.database.collection;

import com.google.firebase.database.collection.LLRBNode;

/* loaded from: classes4.dex */
public class LLRBRedValueNode<K, V> extends LLRBValueNode<K, V> {
    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean isRed() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LLRBRedValueNode(K k, V v) {
        super(k, v, LLRBEmptyNode.getInstance(), LLRBEmptyNode.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LLRBRedValueNode(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        super(k, v, lLRBNode, lLRBNode2);
    }

    @Override // com.google.firebase.database.collection.LLRBValueNode
    protected LLRBNode.Color getColor() {
        return LLRBNode.Color.RED;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public int size() {
        return getLeft().size() + 1 + getRight().size();
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
        return new LLRBRedValueNode(k, v, lLRBNode, lLRBNode2);
    }
}
