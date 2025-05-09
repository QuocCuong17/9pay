package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class ArrayTransformOperation implements TransformOperation {
    private final List<Value> elements;

    protected abstract Value apply(Value value);

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value computeBaseValue(Value value) {
        return null;
    }

    ArrayTransformOperation(List<Value> list) {
        this.elements = Collections.unmodifiableList(list);
    }

    public List<Value> getElements() {
        return this.elements;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToLocalView(Value value, Timestamp timestamp) {
        return apply(value);
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToRemoteDocument(Value value, Value value2) {
        return apply(value);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.elements.equals(((ArrayTransformOperation) obj).elements);
    }

    public int hashCode() {
        return (getClass().hashCode() * 31) + this.elements.hashCode();
    }

    static ArrayValue.Builder coercedFieldValuesArray(Value value) {
        if (Values.isArray(value)) {
            return value.getArrayValue().toBuilder();
        }
        return ArrayValue.newBuilder();
    }

    /* loaded from: classes4.dex */
    public static class Union extends ArrayTransformOperation {
        public Union(List<Value> list) {
            super(list);
        }

        @Override // com.google.firebase.firestore.model.mutation.ArrayTransformOperation
        protected Value apply(Value value) {
            ArrayValue.Builder coercedFieldValuesArray = coercedFieldValuesArray(value);
            for (Value value2 : getElements()) {
                if (!Values.contains(coercedFieldValuesArray, value2)) {
                    coercedFieldValuesArray.addValues(value2);
                }
            }
            return Value.newBuilder().setArrayValue(coercedFieldValuesArray).build();
        }
    }

    /* loaded from: classes4.dex */
    public static class Remove extends ArrayTransformOperation {
        public Remove(List<Value> list) {
            super(list);
        }

        @Override // com.google.firebase.firestore.model.mutation.ArrayTransformOperation
        protected Value apply(Value value) {
            ArrayValue.Builder coercedFieldValuesArray = coercedFieldValuesArray(value);
            for (Value value2 : getElements()) {
                int i = 0;
                while (i < coercedFieldValuesArray.getValuesCount()) {
                    if (Values.equals(coercedFieldValuesArray.getValues(i), value2)) {
                        coercedFieldValuesArray.removeValues(i);
                    } else {
                        i++;
                    }
                }
            }
            return Value.newBuilder().setArrayValue(coercedFieldValuesArray).build();
        }
    }
}
