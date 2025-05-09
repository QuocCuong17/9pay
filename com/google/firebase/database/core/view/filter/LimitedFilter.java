package com.google.firebase.database.core.view.filter;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.Change;
import com.google.firebase.database.core.view.QueryParams;
import com.google.firebase.database.core.view.filter.NodeFilter;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.PriorityUtilities;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class LimitedFilter implements NodeFilter {
    private final Index index;
    private final int limit;
    private final RangedFilter rangedFilter;
    private final boolean reverse;

    @Override // com.google.firebase.database.core.view.filter.NodeFilter
    public boolean filtersNodes() {
        return true;
    }

    @Override // com.google.firebase.database.core.view.filter.NodeFilter
    public IndexedNode updatePriority(IndexedNode indexedNode, Node node) {
        return indexedNode;
    }

    public LimitedFilter(QueryParams queryParams) {
        this.rangedFilter = new RangedFilter(queryParams);
        this.index = queryParams.getIndex();
        this.limit = queryParams.getLimit();
        this.reverse = !queryParams.isViewFromLeft();
    }

    @Override // com.google.firebase.database.core.view.filter.NodeFilter
    public IndexedNode updateChild(IndexedNode indexedNode, ChildKey childKey, Node node, Path path, NodeFilter.CompleteChildSource completeChildSource, ChildChangeAccumulator childChangeAccumulator) {
        if (!this.rangedFilter.matches(new NamedNode(childKey, node))) {
            node = EmptyNode.Empty();
        }
        Node node2 = node;
        if (indexedNode.getNode().getImmediateChild(childKey).equals(node2)) {
            return indexedNode;
        }
        if (indexedNode.getNode().getChildCount() < this.limit) {
            return this.rangedFilter.getIndexedFilter().updateChild(indexedNode, childKey, node2, path, completeChildSource, childChangeAccumulator);
        }
        return fullLimitUpdateChild(indexedNode, childKey, node2, completeChildSource, childChangeAccumulator);
    }

    private IndexedNode fullLimitUpdateChild(IndexedNode indexedNode, ChildKey childKey, Node node, NodeFilter.CompleteChildSource completeChildSource, ChildChangeAccumulator childChangeAccumulator) {
        boolean z = false;
        Utilities.hardAssert(indexedNode.getNode().getChildCount() == this.limit);
        NamedNode namedNode = new NamedNode(childKey, node);
        NamedNode firstChild = this.reverse ? indexedNode.getFirstChild() : indexedNode.getLastChild();
        boolean matches = this.rangedFilter.matches(namedNode);
        if (indexedNode.getNode().hasChild(childKey)) {
            Node immediateChild = indexedNode.getNode().getImmediateChild(childKey);
            NamedNode childAfterChild = completeChildSource.getChildAfterChild(this.index, firstChild, this.reverse);
            while (childAfterChild != null && (childAfterChild.getName().equals(childKey) || indexedNode.getNode().hasChild(childAfterChild.getName()))) {
                childAfterChild = completeChildSource.getChildAfterChild(this.index, childAfterChild, this.reverse);
            }
            if (matches && !node.isEmpty() && (childAfterChild == null ? 1 : this.index.compare(childAfterChild, namedNode, this.reverse)) >= 0) {
                if (childChangeAccumulator != null) {
                    childChangeAccumulator.trackChildChange(Change.childChangedChange(childKey, node, immediateChild));
                }
                return indexedNode.updateChild(childKey, node);
            }
            if (childChangeAccumulator != null) {
                childChangeAccumulator.trackChildChange(Change.childRemovedChange(childKey, immediateChild));
            }
            IndexedNode updateChild = indexedNode.updateChild(childKey, EmptyNode.Empty());
            if (childAfterChild != null && this.rangedFilter.matches(childAfterChild)) {
                z = true;
            }
            if (!z) {
                return updateChild;
            }
            if (childChangeAccumulator != null) {
                childChangeAccumulator.trackChildChange(Change.childAddedChange(childAfterChild.getName(), childAfterChild.getNode()));
            }
            return updateChild.updateChild(childAfterChild.getName(), childAfterChild.getNode());
        }
        if (node.isEmpty() || !matches || this.index.compare(firstChild, namedNode, this.reverse) < 0) {
            return indexedNode;
        }
        if (childChangeAccumulator != null) {
            childChangeAccumulator.trackChildChange(Change.childRemovedChange(firstChild.getName(), firstChild.getNode()));
            childChangeAccumulator.trackChildChange(Change.childAddedChange(childKey, node));
        }
        return indexedNode.updateChild(childKey, node).updateChild(firstChild.getName(), EmptyNode.Empty());
    }

    @Override // com.google.firebase.database.core.view.filter.NodeFilter
    public IndexedNode updateFullNode(IndexedNode indexedNode, IndexedNode indexedNode2, ChildChangeAccumulator childChangeAccumulator) {
        IndexedNode from;
        Iterator<NamedNode> it;
        NamedNode startPost;
        NamedNode endPost;
        int i;
        if (indexedNode2.getNode().isLeafNode() || indexedNode2.getNode().isEmpty()) {
            from = IndexedNode.from(EmptyNode.Empty(), this.index);
        } else {
            from = indexedNode2.updatePriority(PriorityUtilities.NullPriority());
            if (this.reverse) {
                it = indexedNode2.reverseIterator();
                startPost = this.rangedFilter.getEndPost();
                endPost = this.rangedFilter.getStartPost();
                i = -1;
            } else {
                it = indexedNode2.iterator();
                startPost = this.rangedFilter.getStartPost();
                endPost = this.rangedFilter.getEndPost();
                i = 1;
            }
            boolean z = false;
            int i2 = 0;
            while (it.hasNext()) {
                NamedNode next = it.next();
                if (!z && this.index.compare(startPost, next) * i <= 0) {
                    z = true;
                }
                if (z && i2 < this.limit && this.index.compare(next, endPost) * i <= 0) {
                    i2++;
                } else {
                    from = from.updateChild(next.getName(), EmptyNode.Empty());
                }
            }
        }
        return this.rangedFilter.getIndexedFilter().updateFullNode(indexedNode, from, childChangeAccumulator);
    }

    @Override // com.google.firebase.database.core.view.filter.NodeFilter
    public NodeFilter getIndexedFilter() {
        return this.rangedFilter.getIndexedFilter();
    }

    @Override // com.google.firebase.database.core.view.filter.NodeFilter
    public Index getIndex() {
        return this.index;
    }
}
