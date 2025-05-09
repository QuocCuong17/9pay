package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;

/* loaded from: classes4.dex */
public class KeyFieldFilter extends FieldFilter {
    private final DocumentKey key;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyFieldFilter(FieldPath fieldPath, FieldFilter.Operator operator, Value value) {
        super(fieldPath, operator, value);
        Assert.hardAssert(Values.isReferenceValue(value), "KeyFieldFilter expects a ReferenceValue", new Object[0]);
        this.key = DocumentKey.fromName(getValue().getReferenceValue());
    }

    @Override // com.google.firebase.firestore.core.FieldFilter, com.google.firebase.firestore.core.Filter
    public boolean matches(Document document) {
        return matchesComparison(document.getKey().compareTo(this.key));
    }
}
