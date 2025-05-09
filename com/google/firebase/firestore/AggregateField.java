package com.google.firebase.firestore;

import java.util.Objects;

/* loaded from: classes4.dex */
public abstract class AggregateField {
    private final String alias;
    private final FieldPath fieldPath;
    private final String operator;

    private AggregateField(FieldPath fieldPath, String str) {
        String str2;
        this.fieldPath = fieldPath;
        this.operator = str;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (fieldPath == null) {
            str2 = "";
        } else {
            str2 = "_" + fieldPath;
        }
        sb.append(str2);
        this.alias = sb.toString();
    }

    public String getFieldPath() {
        FieldPath fieldPath = this.fieldPath;
        return fieldPath == null ? "" : fieldPath.toString();
    }

    public String getAlias() {
        return this.alias;
    }

    public String getOperator() {
        return this.operator;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AggregateField)) {
            return false;
        }
        AggregateField aggregateField = (AggregateField) obj;
        FieldPath fieldPath = this.fieldPath;
        return (fieldPath == null || aggregateField.fieldPath == null) ? fieldPath == null && aggregateField.fieldPath == null : this.operator.equals(aggregateField.getOperator()) && getFieldPath().equals(aggregateField.getFieldPath());
    }

    public int hashCode() {
        return Objects.hash(getOperator(), getFieldPath());
    }

    public static CountAggregateField count() {
        return new CountAggregateField();
    }

    public static SumAggregateField sum(String str) {
        return new SumAggregateField(FieldPath.fromDotSeparatedPath(str));
    }

    public static SumAggregateField sum(FieldPath fieldPath) {
        return new SumAggregateField(fieldPath);
    }

    public static AverageAggregateField average(String str) {
        return new AverageAggregateField(FieldPath.fromDotSeparatedPath(str));
    }

    public static AverageAggregateField average(FieldPath fieldPath) {
        return new AverageAggregateField(fieldPath);
    }

    /* loaded from: classes4.dex */
    public static class CountAggregateField extends AggregateField {
        /* JADX WARN: Multi-variable type inference failed */
        private CountAggregateField() {
            super(null, "count");
        }
    }

    /* loaded from: classes4.dex */
    public static class SumAggregateField extends AggregateField {
        private SumAggregateField(FieldPath fieldPath) {
            super(fieldPath, "sum");
        }
    }

    /* loaded from: classes4.dex */
    public static class AverageAggregateField extends AggregateField {
        private AverageAggregateField(FieldPath fieldPath) {
            super(fieldPath, "average");
        }
    }
}
