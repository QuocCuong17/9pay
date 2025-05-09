package com.google.firebase.database.core.persistence;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.core.utilities.Predicate;
import com.google.firebase.database.snapshot.ChildKey;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public class PruneForest {
    private final ImmutableTree<Boolean> pruneForest;
    private static final Predicate<Boolean> KEEP_PREDICATE = new Predicate<Boolean>() { // from class: com.google.firebase.database.core.persistence.PruneForest.1
        @Override // com.google.firebase.database.core.utilities.Predicate
        public boolean evaluate(Boolean bool) {
            return !bool.booleanValue();
        }
    };
    private static final Predicate<Boolean> PRUNE_PREDICATE = new Predicate<Boolean>() { // from class: com.google.firebase.database.core.persistence.PruneForest.2
        @Override // com.google.firebase.database.core.utilities.Predicate
        public boolean evaluate(Boolean bool) {
            return bool.booleanValue();
        }
    };
    private static final ImmutableTree<Boolean> PRUNE_TREE = new ImmutableTree<>(true);
    private static final ImmutableTree<Boolean> KEEP_TREE = new ImmutableTree<>(false);

    public PruneForest() {
        this.pruneForest = ImmutableTree.emptyInstance();
    }

    private PruneForest(ImmutableTree<Boolean> immutableTree) {
        this.pruneForest = immutableTree;
    }

    public boolean prunesAnything() {
        return this.pruneForest.containsMatchingValue(PRUNE_PREDICATE);
    }

    public boolean shouldPruneUnkeptDescendants(Path path) {
        Boolean leafMostValue = this.pruneForest.leafMostValue(path);
        return leafMostValue != null && leafMostValue.booleanValue();
    }

    public boolean shouldKeep(Path path) {
        Boolean leafMostValue = this.pruneForest.leafMostValue(path);
        return (leafMostValue == null || leafMostValue.booleanValue()) ? false : true;
    }

    public boolean affectsPath(Path path) {
        return (this.pruneForest.rootMostValue(path) == null && this.pruneForest.subtree(path).isEmpty()) ? false : true;
    }

    public PruneForest child(ChildKey childKey) {
        ImmutableTree<Boolean> child = this.pruneForest.getChild(childKey);
        if (child == null) {
            child = new ImmutableTree<>(this.pruneForest.getValue());
        } else if (child.getValue() == null && this.pruneForest.getValue() != null) {
            child = child.set(Path.getEmptyPath(), this.pruneForest.getValue());
        }
        return new PruneForest(child);
    }

    public PruneForest child(Path path) {
        return path.isEmpty() ? this : child(path.getFront()).child(path.popFront());
    }

    public <T> T foldKeptNodes(T t, final ImmutableTree.TreeVisitor<Void, T> treeVisitor) {
        return (T) this.pruneForest.fold(t, new ImmutableTree.TreeVisitor<Boolean, T>() { // from class: com.google.firebase.database.core.persistence.PruneForest.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.firebase.database.core.utilities.ImmutableTree.TreeVisitor
            public /* bridge */ /* synthetic */ Object onNodeValue(Path path, Boolean bool, Object obj) {
                return onNodeValue2(path, bool, (Boolean) obj);
            }

            /* renamed from: onNodeValue, reason: avoid collision after fix types in other method */
            public T onNodeValue2(Path path, Boolean bool, T t2) {
                return !bool.booleanValue() ? (T) treeVisitor.onNodeValue(path, null, t2) : t2;
            }
        });
    }

    public PruneForest prune(Path path) {
        if (this.pruneForest.rootMostValueMatching(path, KEEP_PREDICATE) == null) {
            return this.pruneForest.rootMostValueMatching(path, PRUNE_PREDICATE) != null ? this : new PruneForest(this.pruneForest.setTree(path, PRUNE_TREE));
        }
        throw new IllegalArgumentException("Can't prune path that was kept previously!");
    }

    public PruneForest keep(Path path) {
        return this.pruneForest.rootMostValueMatching(path, KEEP_PREDICATE) != null ? this : new PruneForest(this.pruneForest.setTree(path, KEEP_TREE));
    }

    public PruneForest keepAll(Path path, Set<ChildKey> set) {
        return this.pruneForest.rootMostValueMatching(path, KEEP_PREDICATE) != null ? this : doAll(path, set, KEEP_TREE);
    }

    public PruneForest pruneAll(Path path, Set<ChildKey> set) {
        if (this.pruneForest.rootMostValueMatching(path, KEEP_PREDICATE) == null) {
            return this.pruneForest.rootMostValueMatching(path, PRUNE_PREDICATE) != null ? this : doAll(path, set, PRUNE_TREE);
        }
        throw new IllegalArgumentException("Can't prune path that was kept previously!");
    }

    private PruneForest doAll(Path path, Set<ChildKey> set, ImmutableTree<Boolean> immutableTree) {
        ImmutableTree<Boolean> subtree = this.pruneForest.subtree(path);
        ImmutableSortedMap<ChildKey, ImmutableTree<Boolean>> children = subtree.getChildren();
        Iterator<ChildKey> it = set.iterator();
        while (it.hasNext()) {
            children = children.insert(it.next(), immutableTree);
        }
        return new PruneForest(this.pruneForest.setTree(path, new ImmutableTree<>(subtree.getValue(), children)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PruneForest) && this.pruneForest.equals(((PruneForest) obj).pruneForest);
    }

    public int hashCode() {
        return this.pruneForest.hashCode();
    }

    public String toString() {
        return "{PruneForest:" + this.pruneForest.toString() + "}";
    }
}
