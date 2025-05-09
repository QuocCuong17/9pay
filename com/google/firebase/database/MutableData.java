package com.google.firebase.database;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.SnapshotHolder;
import com.google.firebase.database.core.ValidationPath;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.database.core.utilities.encoding.CustomClassMapper;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import com.google.firebase.database.snapshot.PriorityUtilities;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public class MutableData {
    private final SnapshotHolder holder;
    private final Path prefixPath;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MutableData(Node node) {
        this(new SnapshotHolder(node), new Path(""));
    }

    private MutableData(SnapshotHolder snapshotHolder, Path path) {
        this.holder = snapshotHolder;
        this.prefixPath = path;
        ValidationPath.validateWithObject(path, getValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Node getNode() {
        return this.holder.getNode(this.prefixPath);
    }

    public boolean hasChildren() {
        Node node = getNode();
        return (node.isLeafNode() || node.isEmpty()) ? false : true;
    }

    public boolean hasChild(String str) {
        return !getNode().getChild(new Path(str)).isEmpty();
    }

    public MutableData child(String str) {
        Validation.validatePathString(str);
        return new MutableData(this.holder, this.prefixPath.child(new Path(str)));
    }

    public long getChildrenCount() {
        return getNode().getChildCount();
    }

    public Iterable<MutableData> getChildren() {
        Node node = getNode();
        if (node.isEmpty() || node.isLeafNode()) {
            return new Iterable<MutableData>() { // from class: com.google.firebase.database.MutableData.1
                @Override // java.lang.Iterable
                public Iterator<MutableData> iterator() {
                    return new Iterator<MutableData>() { // from class: com.google.firebase.database.MutableData.1.1
                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Iterator
                        public MutableData next() {
                            throw new NoSuchElementException();
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            throw new UnsupportedOperationException("remove called on immutable collection");
                        }
                    };
                }
            };
        }
        final Iterator<NamedNode> it = IndexedNode.from(node).iterator();
        return new Iterable<MutableData>() { // from class: com.google.firebase.database.MutableData.2
            @Override // java.lang.Iterable
            public Iterator<MutableData> iterator() {
                return new Iterator<MutableData>() { // from class: com.google.firebase.database.MutableData.2.1
                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public MutableData next() {
                        return new MutableData(MutableData.this.holder, MutableData.this.prefixPath.child(((NamedNode) it.next()).getName()));
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException("remove called on immutable collection");
                    }
                };
            }
        };
    }

    public String getKey() {
        if (this.prefixPath.getBack() != null) {
            return this.prefixPath.getBack().asString();
        }
        return null;
    }

    public Object getValue() {
        return getNode().getValue();
    }

    public <T> T getValue(Class<T> cls) {
        return (T) CustomClassMapper.convertToCustomClass(getNode().getValue(), cls);
    }

    public <T> T getValue(GenericTypeIndicator<T> genericTypeIndicator) {
        return (T) CustomClassMapper.convertToCustomClass(getNode().getValue(), genericTypeIndicator);
    }

    public void setValue(Object obj) throws DatabaseException {
        ValidationPath.validateWithObject(this.prefixPath, obj);
        Object convertToPlainJavaTypes = CustomClassMapper.convertToPlainJavaTypes(obj);
        Validation.validateWritableObject(convertToPlainJavaTypes);
        this.holder.update(this.prefixPath, NodeUtilities.NodeFromJSON(convertToPlainJavaTypes));
    }

    public void setPriority(Object obj) {
        this.holder.update(this.prefixPath, getNode().updatePriority(PriorityUtilities.parsePriority(this.prefixPath, obj)));
    }

    public Object getPriority() {
        return getNode().getPriority().getValue();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MutableData) {
            MutableData mutableData = (MutableData) obj;
            if (this.holder.equals(mutableData.holder) && this.prefixPath.equals(mutableData.prefixPath)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        ChildKey front = this.prefixPath.getFront();
        StringBuilder sb = new StringBuilder();
        sb.append("MutableData { key = ");
        sb.append(front != null ? front.asString() : "<none>");
        sb.append(", value = ");
        sb.append(this.holder.getRootNode().getValue(true));
        sb.append(" }");
        return sb.toString();
    }
}
