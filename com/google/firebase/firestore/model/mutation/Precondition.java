package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;

/* loaded from: classes4.dex */
public final class Precondition {
    public static final Precondition NONE = new Precondition(null, null);
    private final Boolean exists;
    private final SnapshotVersion updateTime;

    private Precondition(SnapshotVersion snapshotVersion, Boolean bool) {
        Assert.hardAssert(snapshotVersion == null || bool == null, "Precondition can specify \"exists\" or \"updateTime\" but not both", new Object[0]);
        this.updateTime = snapshotVersion;
        this.exists = bool;
    }

    public static Precondition exists(boolean z) {
        return new Precondition(null, Boolean.valueOf(z));
    }

    public static Precondition updateTime(SnapshotVersion snapshotVersion) {
        return new Precondition(snapshotVersion, null);
    }

    public boolean isNone() {
        return this.updateTime == null && this.exists == null;
    }

    public SnapshotVersion getUpdateTime() {
        return this.updateTime;
    }

    public Boolean getExists() {
        return this.exists;
    }

    public boolean isValidFor(MutableDocument mutableDocument) {
        if (this.updateTime != null) {
            return mutableDocument.isFoundDocument() && mutableDocument.getVersion().equals(this.updateTime);
        }
        Boolean bool = this.exists;
        if (bool != null) {
            return bool.booleanValue() == mutableDocument.isFoundDocument();
        }
        Assert.hardAssert(isNone(), "Precondition should be empty", new Object[0]);
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Precondition precondition = (Precondition) obj;
        SnapshotVersion snapshotVersion = this.updateTime;
        if (snapshotVersion == null ? precondition.updateTime != null : !snapshotVersion.equals(precondition.updateTime)) {
            return false;
        }
        Boolean bool = this.exists;
        Boolean bool2 = precondition.exists;
        return bool != null ? bool.equals(bool2) : bool2 == null;
    }

    public int hashCode() {
        SnapshotVersion snapshotVersion = this.updateTime;
        int hashCode = (snapshotVersion != null ? snapshotVersion.hashCode() : 0) * 31;
        Boolean bool = this.exists;
        return hashCode + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        if (isNone()) {
            return "Precondition{<none>}";
        }
        if (this.updateTime != null) {
            return "Precondition{updateTime=" + this.updateTime + "}";
        }
        if (this.exists != null) {
            return "Precondition{exists=" + this.exists + "}";
        }
        throw Assert.fail("Invalid Precondition", new Object[0]);
    }
}
