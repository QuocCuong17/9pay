package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class LocalViewChanges {
    private final ImmutableSortedSet<DocumentKey> added;
    private final boolean fromCache;
    private final ImmutableSortedSet<DocumentKey> removed;
    private final int targetId;

    public static LocalViewChanges fromViewSnapshot(int i, ViewSnapshot viewSnapshot) {
        ImmutableSortedSet immutableSortedSet = new ImmutableSortedSet(new ArrayList(), DocumentKey.comparator());
        ImmutableSortedSet immutableSortedSet2 = new ImmutableSortedSet(new ArrayList(), DocumentKey.comparator());
        for (DocumentViewChange documentViewChange : viewSnapshot.getChanges()) {
            int i2 = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[documentViewChange.getType().ordinal()];
            if (i2 == 1) {
                immutableSortedSet = immutableSortedSet.insert(documentViewChange.getDocument().getKey());
            } else if (i2 == 2) {
                immutableSortedSet2 = immutableSortedSet2.insert(documentViewChange.getDocument().getKey());
            }
        }
        return new LocalViewChanges(i, viewSnapshot.isFromCache(), immutableSortedSet, immutableSortedSet2);
    }

    /* renamed from: com.google.firebase.firestore.local.LocalViewChanges$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type;

        static {
            int[] iArr = new int[DocumentViewChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = iArr;
            try {
                iArr[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.REMOVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public LocalViewChanges(int i, boolean z, ImmutableSortedSet<DocumentKey> immutableSortedSet, ImmutableSortedSet<DocumentKey> immutableSortedSet2) {
        this.targetId = i;
        this.fromCache = z;
        this.added = immutableSortedSet;
        this.removed = immutableSortedSet2;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public boolean isFromCache() {
        return this.fromCache;
    }

    public ImmutableSortedSet<DocumentKey> getAdded() {
        return this.added;
    }

    public ImmutableSortedSet<DocumentKey> getRemoved() {
        return this.removed;
    }
}
