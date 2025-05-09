package com.google.firebase.firestore.core;

/* loaded from: classes4.dex */
final class QueryView {
    private final Query query;
    private final int targetId;
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueryView(Query query, int i, View view) {
        this.query = query;
        this.targetId = i;
        this.view = view;
    }

    public Query getQuery() {
        return this.query;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public View getView() {
        return this.view;
    }
}
