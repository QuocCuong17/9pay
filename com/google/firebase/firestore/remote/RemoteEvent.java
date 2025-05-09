package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class RemoteEvent {
    private final Map<DocumentKey, MutableDocument> documentUpdates;
    private final Set<DocumentKey> resolvedLimboDocuments;
    private final SnapshotVersion snapshotVersion;
    private final Map<Integer, TargetChange> targetChanges;
    private final Map<Integer, QueryPurpose> targetMismatches;

    public RemoteEvent(SnapshotVersion snapshotVersion, Map<Integer, TargetChange> map, Map<Integer, QueryPurpose> map2, Map<DocumentKey, MutableDocument> map3, Set<DocumentKey> set) {
        this.snapshotVersion = snapshotVersion;
        this.targetChanges = map;
        this.targetMismatches = map2;
        this.documentUpdates = map3;
        this.resolvedLimboDocuments = set;
    }

    public SnapshotVersion getSnapshotVersion() {
        return this.snapshotVersion;
    }

    public Map<Integer, TargetChange> getTargetChanges() {
        return this.targetChanges;
    }

    public Map<Integer, QueryPurpose> getTargetMismatches() {
        return this.targetMismatches;
    }

    public Map<DocumentKey, MutableDocument> getDocumentUpdates() {
        return this.documentUpdates;
    }

    public Set<DocumentKey> getResolvedLimboDocuments() {
        return this.resolvedLimboDocuments;
    }

    public String toString() {
        return "RemoteEvent{snapshotVersion=" + this.snapshotVersion + ", targetChanges=" + this.targetChanges + ", targetMismatches=" + this.targetMismatches + ", documentUpdates=" + this.documentUpdates + ", resolvedLimboDocuments=" + this.resolvedLimboDocuments + '}';
    }
}
