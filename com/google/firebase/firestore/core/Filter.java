package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class Filter {
    public abstract String getCanonicalId();

    public abstract List<Filter> getFilters();

    public abstract FieldPath getFirstInequalityField();

    public abstract List<FieldFilter> getFlattenedFilters();

    public abstract boolean matches(Document document);
}
