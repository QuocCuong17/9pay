package com.google.firebase.database.snapshot;

import com.google.firebase.database.snapshot.LeafNode;
import com.google.firebase.database.snapshot.Node;

/* loaded from: classes4.dex */
public class BooleanNode extends LeafNode<BooleanNode> {
    private final boolean value;

    public BooleanNode(Boolean bool, Node node) {
        super(node);
        this.value = bool.booleanValue();
    }

    @Override // com.google.firebase.database.snapshot.Node
    public Object getValue() {
        return Boolean.valueOf(this.value);
    }

    @Override // com.google.firebase.database.snapshot.Node
    public String getHashRepresentation(Node.HashVersion hashVersion) {
        return getPriorityHash(hashVersion) + "boolean:" + this.value;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public BooleanNode updatePriority(Node node) {
        return new BooleanNode(Boolean.valueOf(this.value), node);
    }

    @Override // com.google.firebase.database.snapshot.LeafNode
    protected LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.Boolean;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.firebase.database.snapshot.LeafNode
    public int compareLeafValues(BooleanNode booleanNode) {
        boolean z = this.value;
        if (z == booleanNode.value) {
            return 0;
        }
        return z ? 1 : -1;
    }

    @Override // com.google.firebase.database.snapshot.LeafNode
    public boolean equals(Object obj) {
        if (!(obj instanceof BooleanNode)) {
            return false;
        }
        BooleanNode booleanNode = (BooleanNode) obj;
        return this.value == booleanNode.value && this.priority.equals(booleanNode.priority);
    }

    @Override // com.google.firebase.database.snapshot.LeafNode
    public int hashCode() {
        boolean z = this.value;
        return (z ? 1 : 0) + this.priority.hashCode();
    }
}
