package com.google.firebase.firestore;

import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Preconditions;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class SetOptions {
    private final FieldMask fieldMask;
    private final boolean merge;
    static final SetOptions OVERWRITE = new SetOptions(false, null);
    private static final SetOptions MERGE_ALL_FIELDS = new SetOptions(true, null);

    private SetOptions(boolean z, FieldMask fieldMask) {
        Preconditions.checkArgument(fieldMask == null || z, "Cannot specify a fieldMask for non-merge sets()", new Object[0]);
        this.merge = z;
        this.fieldMask = fieldMask;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isMerge() {
        return this.merge;
    }

    public FieldMask getFieldMask() {
        return this.fieldMask;
    }

    public static SetOptions merge() {
        return MERGE_ALL_FIELDS;
    }

    public static SetOptions mergeFields(List<String> list) {
        HashSet hashSet = new HashSet();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(FieldPath.fromDotSeparatedPath(it.next()).getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(hashSet));
    }

    public static SetOptions mergeFields(String... strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(FieldPath.fromDotSeparatedPath(str).getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(hashSet));
    }

    public static SetOptions mergeFieldPaths(List<FieldPath> list) {
        HashSet hashSet = new HashSet();
        Iterator<FieldPath> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(hashSet));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SetOptions setOptions = (SetOptions) obj;
        if (this.merge != setOptions.merge) {
            return false;
        }
        FieldMask fieldMask = this.fieldMask;
        FieldMask fieldMask2 = setOptions.fieldMask;
        return fieldMask != null ? fieldMask.equals(fieldMask2) : fieldMask2 == null;
    }

    public int hashCode() {
        int i = (this.merge ? 1 : 0) * 31;
        FieldMask fieldMask = this.fieldMask;
        return i + (fieldMask != null ? fieldMask.hashCode() : 0);
    }
}
