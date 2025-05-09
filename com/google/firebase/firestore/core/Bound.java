package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.List;

/* loaded from: classes4.dex */
public final class Bound {
    private final boolean inclusive;
    private final List<Value> position;

    public Bound(List<Value> list, boolean z) {
        this.position = list;
        this.inclusive = z;
    }

    public List<Value> getPosition() {
        return this.position;
    }

    public boolean isInclusive() {
        return this.inclusive;
    }

    public String positionString() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Value value : this.position) {
            if (!z) {
                sb.append(",");
            }
            z = false;
            sb.append(Values.canonicalId(value));
        }
        return sb.toString();
    }

    public boolean sortsBeforeDocument(List<OrderBy> list, Document document) {
        int compareToDocument = compareToDocument(list, document);
        if (this.inclusive) {
            if (compareToDocument <= 0) {
                return true;
            }
        } else if (compareToDocument < 0) {
            return true;
        }
        return false;
    }

    public boolean sortsAfterDocument(List<OrderBy> list, Document document) {
        int compareToDocument = compareToDocument(list, document);
        if (this.inclusive) {
            if (compareToDocument >= 0) {
                return true;
            }
        } else if (compareToDocument > 0) {
            return true;
        }
        return false;
    }

    private int compareToDocument(List<OrderBy> list, Document document) {
        int compare;
        Assert.hardAssert(this.position.size() <= list.size(), "Bound has more components than query's orderBy", new Object[0]);
        int i = 0;
        for (int i2 = 0; i2 < this.position.size(); i2++) {
            OrderBy orderBy = list.get(i2);
            Value value = this.position.get(i2);
            if (orderBy.field.equals(FieldPath.KEY_PATH)) {
                Assert.hardAssert(Values.isReferenceValue(value), "Bound has a non-key value where the key path is being used %s", value);
                compare = DocumentKey.fromName(value.getReferenceValue()).compareTo(document.getKey());
            } else {
                Value field = document.getField(orderBy.getField());
                Assert.hardAssert(field != null, "Field should exist since document matched the orderBy already.", new Object[0]);
                compare = Values.compare(value, field);
            }
            if (orderBy.getDirection().equals(OrderBy.Direction.DESCENDING)) {
                compare *= -1;
            }
            i = compare;
            if (i != 0) {
                break;
            }
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Bound bound = (Bound) obj;
        return this.inclusive == bound.inclusive && this.position.equals(bound.position);
    }

    public int hashCode() {
        return ((this.inclusive ? 1 : 0) * 31) + this.position.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bound(inclusive=");
        sb.append(this.inclusive);
        sb.append(", position=");
        for (int i = 0; i < this.position.size(); i++) {
            if (i > 0) {
                sb.append(" and ");
            }
            sb.append(Values.canonicalId(this.position.get(i)));
        }
        sb.append(")");
        return sb.toString();
    }
}
