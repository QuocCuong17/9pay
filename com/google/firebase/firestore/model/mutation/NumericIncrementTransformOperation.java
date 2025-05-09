package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;

/* loaded from: classes4.dex */
public class NumericIncrementTransformOperation implements TransformOperation {
    private Value operand;

    private long safeIncrement(long j, long j2) {
        long j3 = j + j2;
        return ((j ^ j3) & (j2 ^ j3)) >= 0 ? j3 : j3 >= 0 ? Long.MIN_VALUE : Long.MAX_VALUE;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToRemoteDocument(Value value, Value value2) {
        return value2;
    }

    public NumericIncrementTransformOperation(Value value) {
        Assert.hardAssert(Values.isNumber(value), "NumericIncrementTransformOperation expects a NumberValue operand", new Object[0]);
        this.operand = value;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToLocalView(Value value, Timestamp timestamp) {
        Value computeBaseValue = computeBaseValue(value);
        if (Values.isInteger(computeBaseValue) && Values.isInteger(this.operand)) {
            return Value.newBuilder().setIntegerValue(safeIncrement(computeBaseValue.getIntegerValue(), operandAsLong())).build();
        }
        if (Values.isInteger(computeBaseValue)) {
            return Value.newBuilder().setDoubleValue(computeBaseValue.getIntegerValue() + operandAsDouble()).build();
        }
        Assert.hardAssert(Values.isDouble(computeBaseValue), "Expected NumberValue to be of type DoubleValue, but was ", value.getClass().getCanonicalName());
        return Value.newBuilder().setDoubleValue(computeBaseValue.getDoubleValue() + operandAsDouble()).build();
    }

    public Value getOperand() {
        return this.operand;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value computeBaseValue(Value value) {
        return Values.isNumber(value) ? value : Value.newBuilder().setIntegerValue(0L).build();
    }

    private double operandAsDouble() {
        if (Values.isDouble(this.operand)) {
            return this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return this.operand.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + this.operand.getClass().getCanonicalName(), new Object[0]);
    }

    private long operandAsLong() {
        if (Values.isDouble(this.operand)) {
            return (long) this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return this.operand.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + this.operand.getClass().getCanonicalName(), new Object[0]);
    }
}
