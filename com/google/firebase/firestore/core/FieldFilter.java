package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class FieldFilter extends Filter {
    private final FieldPath field;
    private final Operator operator;
    private final Value value;

    /* loaded from: classes4.dex */
    public enum Operator {
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL("<="),
        EQUAL("=="),
        NOT_EQUAL("!="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        ARRAY_CONTAINS("array_contains"),
        ARRAY_CONTAINS_ANY("array_contains_any"),
        IN("in"),
        NOT_IN("not_in");

        private final String text;

        Operator(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FieldFilter(FieldPath fieldPath, Operator operator, Value value) {
        this.field = fieldPath;
        this.operator = operator;
        this.value = value;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public FieldPath getField() {
        return this.field;
    }

    public Value getValue() {
        return this.value;
    }

    public static FieldFilter create(FieldPath fieldPath, Operator operator, Value value) {
        if (fieldPath.isKeyField()) {
            if (operator == Operator.IN) {
                return new KeyFieldInFilter(fieldPath, value);
            }
            if (operator == Operator.NOT_IN) {
                return new KeyFieldNotInFilter(fieldPath, value);
            }
            Assert.hardAssert((operator == Operator.ARRAY_CONTAINS || operator == Operator.ARRAY_CONTAINS_ANY) ? false : true, operator.toString() + "queries don't make sense on document keys", new Object[0]);
            return new KeyFieldFilter(fieldPath, operator, value);
        }
        if (operator == Operator.ARRAY_CONTAINS) {
            return new ArrayContainsFilter(fieldPath, value);
        }
        if (operator == Operator.IN) {
            return new InFilter(fieldPath, value);
        }
        if (operator == Operator.ARRAY_CONTAINS_ANY) {
            return new ArrayContainsAnyFilter(fieldPath, value);
        }
        if (operator == Operator.NOT_IN) {
            return new NotInFilter(fieldPath, value);
        }
        return new FieldFilter(fieldPath, operator, value);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public boolean matches(Document document) {
        Value field = document.getField(this.field);
        return this.operator == Operator.NOT_EQUAL ? field != null && matchesComparison(Values.compare(field, this.value)) : field != null && Values.typeOrder(field) == Values.typeOrder(this.value) && matchesComparison(Values.compare(field, this.value));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.firestore.core.FieldFilter$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator;

        static {
            int[] iArr = new int[Operator.values().length];
            $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator = iArr;
            try {
                iArr[Operator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.LESS_THAN_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.NOT_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.GREATER_THAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean matchesComparison(int i) {
        switch (AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[this.operator.ordinal()]) {
            case 1:
                return i < 0;
            case 2:
                return i <= 0;
            case 3:
                return i == 0;
            case 4:
                return i != 0;
            case 5:
                return i > 0;
            case 6:
                return i >= 0;
            default:
                throw Assert.fail("Unknown FieldFilter operator: %s", this.operator);
        }
    }

    public boolean isInequality() {
        return Arrays.asList(Operator.LESS_THAN, Operator.LESS_THAN_OR_EQUAL, Operator.GREATER_THAN, Operator.GREATER_THAN_OR_EQUAL, Operator.NOT_EQUAL, Operator.NOT_IN).contains(this.operator);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public String getCanonicalId() {
        return getField().canonicalString() + getOperator().toString() + Values.canonicalId(getValue());
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<FieldFilter> getFlattenedFilters() {
        return Collections.singletonList(this);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<Filter> getFilters() {
        return Collections.singletonList(this);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public FieldPath getFirstInequalityField() {
        if (isInequality()) {
            return getField();
        }
        return null;
    }

    public String toString() {
        return getCanonicalId();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FieldFilter)) {
            return false;
        }
        FieldFilter fieldFilter = (FieldFilter) obj;
        return this.operator == fieldFilter.operator && this.field.equals(fieldFilter.field) && this.value.equals(fieldFilter.value);
    }

    public int hashCode() {
        return ((((1147 + this.operator.hashCode()) * 31) + this.field.hashCode()) * 31) + this.value.hashCode();
    }
}
