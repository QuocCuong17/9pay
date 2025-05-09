package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.FieldTransform;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.SetMutation;
import com.google.firebase.firestore.model.mutation.TransformOperation;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public class UserData {

    /* loaded from: classes4.dex */
    public enum Source {
        Set,
        MergeSet,
        Update,
        Argument,
        ArrayArgument
    }

    private UserData() {
    }

    /* loaded from: classes4.dex */
    public static class ParseAccumulator {
        private final Source dataSource;
        private final Set<FieldPath> fieldMask = new HashSet();
        private final ArrayList<FieldTransform> fieldTransforms = new ArrayList<>();

        public ParseAccumulator(Source source) {
            this.dataSource = source;
        }

        public Source getDataSource() {
            return this.dataSource;
        }

        public List<FieldTransform> getFieldTransforms() {
            return this.fieldTransforms;
        }

        public ParseContext rootContext() {
            return new ParseContext(this, FieldPath.EMPTY_PATH, false, null);
        }

        public boolean contains(FieldPath fieldPath) {
            Iterator<FieldPath> it = this.fieldMask.iterator();
            while (it.hasNext()) {
                if (fieldPath.isPrefixOf(it.next())) {
                    return true;
                }
            }
            Iterator<FieldTransform> it2 = this.fieldTransforms.iterator();
            while (it2.hasNext()) {
                if (fieldPath.isPrefixOf(it2.next().getFieldPath())) {
                    return true;
                }
            }
            return false;
        }

        void addToFieldMask(FieldPath fieldPath) {
            this.fieldMask.add(fieldPath);
        }

        void addToFieldTransforms(FieldPath fieldPath, TransformOperation transformOperation) {
            this.fieldTransforms.add(new FieldTransform(fieldPath, transformOperation));
        }

        public ParsedSetData toMergeData(ObjectValue objectValue) {
            return new ParsedSetData(objectValue, FieldMask.fromSet(this.fieldMask), Collections.unmodifiableList(this.fieldTransforms));
        }

        public ParsedSetData toMergeData(ObjectValue objectValue, FieldMask fieldMask) {
            ArrayList arrayList = new ArrayList();
            Iterator<FieldTransform> it = this.fieldTransforms.iterator();
            while (it.hasNext()) {
                FieldTransform next = it.next();
                if (fieldMask.covers(next.getFieldPath())) {
                    arrayList.add(next);
                }
            }
            return new ParsedSetData(objectValue, fieldMask, Collections.unmodifiableList(arrayList));
        }

        public ParsedSetData toSetData(ObjectValue objectValue) {
            return new ParsedSetData(objectValue, null, Collections.unmodifiableList(this.fieldTransforms));
        }

        public ParsedUpdateData toUpdateData(ObjectValue objectValue) {
            return new ParsedUpdateData(objectValue, FieldMask.fromSet(this.fieldMask), Collections.unmodifiableList(this.fieldTransforms));
        }
    }

    /* loaded from: classes4.dex */
    public static class ParseContext {
        private static final String RESERVED_FIELD_DESIGNATOR = "__";
        private final ParseAccumulator accumulator;
        private final boolean arrayElement;
        private final FieldPath path;

        /* synthetic */ ParseContext(ParseAccumulator parseAccumulator, FieldPath fieldPath, boolean z, AnonymousClass1 anonymousClass1) {
            this(parseAccumulator, fieldPath, z);
        }

        private ParseContext(ParseAccumulator parseAccumulator, FieldPath fieldPath, boolean z) {
            this.accumulator = parseAccumulator;
            this.path = fieldPath;
            this.arrayElement = z;
        }

        public boolean isArrayElement() {
            return this.arrayElement;
        }

        public Source getDataSource() {
            return this.accumulator.dataSource;
        }

        public FieldPath getPath() {
            return this.path;
        }

        public boolean isWrite() {
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$UserData$Source[this.accumulator.dataSource.ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                return true;
            }
            if (i == 4 || i == 5) {
                return false;
            }
            throw Assert.fail("Unexpected case for UserDataSource: %s", this.accumulator.dataSource.name());
        }

        public ParseContext childContext(String str) {
            FieldPath fieldPath = this.path;
            ParseContext parseContext = new ParseContext(this.accumulator, fieldPath == null ? null : fieldPath.append(str), false);
            parseContext.validatePathSegment(str);
            return parseContext;
        }

        public ParseContext childContext(FieldPath fieldPath) {
            FieldPath fieldPath2 = this.path;
            ParseContext parseContext = new ParseContext(this.accumulator, fieldPath2 == null ? null : fieldPath2.append(fieldPath), false);
            parseContext.validatePath();
            return parseContext;
        }

        public ParseContext childContext(int i) {
            return new ParseContext(this.accumulator, null, true);
        }

        public void addToFieldMask(FieldPath fieldPath) {
            this.accumulator.addToFieldMask(fieldPath);
        }

        public void addToFieldTransforms(FieldPath fieldPath, TransformOperation transformOperation) {
            this.accumulator.addToFieldTransforms(fieldPath, transformOperation);
        }

        public RuntimeException createError(String str) {
            String str2;
            FieldPath fieldPath = this.path;
            if (fieldPath == null || fieldPath.isEmpty()) {
                str2 = "";
            } else {
                str2 = " (found in field " + this.path.toString() + ")";
            }
            return new IllegalArgumentException("Invalid data. " + str + str2);
        }

        private void validatePath() {
            if (this.path == null) {
                return;
            }
            for (int i = 0; i < this.path.length(); i++) {
                validatePathSegment(this.path.getSegment(i));
            }
        }

        private void validatePathSegment(String str) {
            if (str.isEmpty()) {
                throw createError("Document fields must not be empty");
            }
            if (isWrite() && str.startsWith(RESERVED_FIELD_DESIGNATOR) && str.endsWith(RESERVED_FIELD_DESIGNATOR)) {
                throw createError("Document fields cannot begin and end with \"__\"");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.firestore.core.UserData$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$UserData$Source;

        static {
            int[] iArr = new int[Source.values().length];
            $SwitchMap$com$google$firebase$firestore$core$UserData$Source = iArr;
            try {
                iArr[Source.Set.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source[Source.MergeSet.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source[Source.Update.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source[Source.Argument.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source[Source.ArrayArgument.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class ParsedSetData {
        private final ObjectValue data;
        private final FieldMask fieldMask;
        private final List<FieldTransform> fieldTransforms;

        ParsedSetData(ObjectValue objectValue, FieldMask fieldMask, List<FieldTransform> list) {
            this.data = objectValue;
            this.fieldMask = fieldMask;
            this.fieldTransforms = list;
        }

        public ObjectValue getData() {
            return this.data;
        }

        public FieldMask getFieldMask() {
            return this.fieldMask;
        }

        public List<FieldTransform> getFieldTransforms() {
            return this.fieldTransforms;
        }

        public Mutation toMutation(DocumentKey documentKey, Precondition precondition) {
            FieldMask fieldMask = this.fieldMask;
            if (fieldMask != null) {
                return new PatchMutation(documentKey, this.data, fieldMask, precondition, this.fieldTransforms);
            }
            return new SetMutation(documentKey, this.data, precondition, this.fieldTransforms);
        }
    }

    /* loaded from: classes4.dex */
    public static class ParsedUpdateData {
        private final ObjectValue data;
        private final FieldMask fieldMask;
        private final List<FieldTransform> fieldTransforms;

        ParsedUpdateData(ObjectValue objectValue, FieldMask fieldMask, List<FieldTransform> list) {
            this.data = objectValue;
            this.fieldMask = fieldMask;
            this.fieldTransforms = list;
        }

        public ObjectValue getData() {
            return this.data;
        }

        public FieldMask getFieldMask() {
            return this.fieldMask;
        }

        public List<FieldTransform> getFieldTransforms() {
            return this.fieldTransforms;
        }

        public Mutation toMutation(DocumentKey documentKey, Precondition precondition) {
            return new PatchMutation(documentKey, this.data, this.fieldMask, precondition, this.fieldTransforms);
        }
    }
}
