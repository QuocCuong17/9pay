package com.google.firebase.firestore;

import android.util.SparseArray;
import com.google.firebase.FirebaseException;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;

/* loaded from: classes4.dex */
public class FirebaseFirestoreException extends FirebaseException {
    private final Code code;

    /* loaded from: classes4.dex */
    public enum Code {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);

        private static final SparseArray<Code> STATUS_LIST = buildStatusList();
        private final int value;

        Code(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        private static SparseArray<Code> buildStatusList() {
            SparseArray<Code> sparseArray = new SparseArray<>();
            for (Code code : values()) {
                Code code2 = sparseArray.get(code.value());
                if (code2 != null) {
                    throw new IllegalStateException("Code value duplication between " + code2 + "&" + code.name());
                }
                sparseArray.put(code.value(), code);
            }
            return sparseArray;
        }

        public static Code fromValue(int i) {
            return STATUS_LIST.get(i, UNKNOWN);
        }
    }

    public FirebaseFirestoreException(String str, Code code) {
        super(str);
        Preconditions.checkNotNull(str, "Provided message must not be null.");
        Assert.hardAssert(code != Code.OK, "A FirebaseFirestoreException should never be thrown for OK", new Object[0]);
        this.code = (Code) Preconditions.checkNotNull(code, "Provided code must not be null.");
    }

    public FirebaseFirestoreException(String str, Code code, Throwable th) {
        super(str, th);
        Preconditions.checkNotNull(str, "Provided message must not be null.");
        Assert.hardAssert(code != Code.OK, "A FirebaseFirestoreException should never be thrown for OK", new Object[0]);
        this.code = (Code) Preconditions.checkNotNull(code, "Provided code must not be null.");
    }

    public Code getCode() {
        return this.code;
    }
}
