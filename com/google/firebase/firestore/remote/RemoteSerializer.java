package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.FieldTransform;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.model.mutation.NumericIncrementTransformOperation;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.ServerTimestampOperation;
import com.google.firebase.firestore.model.mutation.SetMutation;
import com.google.firebase.firestore.model.mutation.TransformOperation;
import com.google.firebase.firestore.model.mutation.VerifyMutation;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.Cursor;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.DocumentChange;
import com.google.firestore.v1.DocumentDelete;
import com.google.firestore.v1.DocumentMask;
import com.google.firestore.v1.DocumentRemove;
import com.google.firestore.v1.DocumentTransform;
import com.google.firestore.v1.ListenResponse;
import com.google.firestore.v1.Precondition;
import com.google.firestore.v1.StructuredAggregationQuery;
import com.google.firestore.v1.StructuredQuery;
import com.google.firestore.v1.Target;
import com.google.firestore.v1.TargetChange;
import com.google.firestore.v1.Write;
import com.google.firestore.v1.WriteResult;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class RemoteSerializer {
    private final DatabaseId databaseId;
    private final String databaseName;

    public RemoteSerializer(DatabaseId databaseId) {
        this.databaseId = databaseId;
        this.databaseName = encodedDatabaseId(databaseId).canonicalString();
    }

    public Timestamp encodeTimestamp(com.google.firebase.Timestamp timestamp) {
        Timestamp.Builder newBuilder = Timestamp.newBuilder();
        newBuilder.setSeconds(timestamp.getSeconds());
        newBuilder.setNanos(timestamp.getNanoseconds());
        return newBuilder.build();
    }

    public com.google.firebase.Timestamp decodeTimestamp(Timestamp timestamp) {
        return new com.google.firebase.Timestamp(timestamp.getSeconds(), timestamp.getNanos());
    }

    public Timestamp encodeVersion(SnapshotVersion snapshotVersion) {
        return encodeTimestamp(snapshotVersion.getTimestamp());
    }

    public SnapshotVersion decodeVersion(Timestamp timestamp) {
        if (timestamp.getSeconds() == 0 && timestamp.getNanos() == 0) {
            return SnapshotVersion.NONE;
        }
        return new SnapshotVersion(decodeTimestamp(timestamp));
    }

    public String encodeKey(DocumentKey documentKey) {
        return encodeResourceName(this.databaseId, documentKey.getPath());
    }

    public DocumentKey decodeKey(String str) {
        ResourcePath decodeResourceName = decodeResourceName(str);
        Assert.hardAssert(decodeResourceName.getSegment(1).equals(this.databaseId.getProjectId()), "Tried to deserialize key from different project.", new Object[0]);
        Assert.hardAssert(decodeResourceName.getSegment(3).equals(this.databaseId.getDatabaseId()), "Tried to deserialize key from different database.", new Object[0]);
        return DocumentKey.fromPath(extractLocalPathFromResourceName(decodeResourceName));
    }

    private String encodeQueryPath(ResourcePath resourcePath) {
        return encodeResourceName(this.databaseId, resourcePath);
    }

    private ResourcePath decodeQueryPath(String str) {
        ResourcePath decodeResourceName = decodeResourceName(str);
        if (decodeResourceName.length() == 4) {
            return ResourcePath.EMPTY;
        }
        return extractLocalPathFromResourceName(decodeResourceName);
    }

    private String encodeResourceName(DatabaseId databaseId, ResourcePath resourcePath) {
        return encodedDatabaseId(databaseId).append("documents").append(resourcePath).canonicalString();
    }

    private ResourcePath decodeResourceName(String str) {
        ResourcePath fromString = ResourcePath.fromString(str);
        Assert.hardAssert(isValidResourceName(fromString), "Tried to deserialize invalid key %s", fromString);
        return fromString;
    }

    private static ResourcePath encodedDatabaseId(DatabaseId databaseId) {
        return ResourcePath.fromSegments(Arrays.asList("projects", databaseId.getProjectId(), "databases", databaseId.getDatabaseId()));
    }

    private static ResourcePath extractLocalPathFromResourceName(ResourcePath resourcePath) {
        Assert.hardAssert(resourcePath.length() > 4 && resourcePath.getSegment(4).equals("documents"), "Tried to deserialize invalid key %s", resourcePath);
        return resourcePath.popFirst(5);
    }

    private static boolean isValidResourceName(ResourcePath resourcePath) {
        return resourcePath.length() >= 4 && resourcePath.getSegment(0).equals("projects") && resourcePath.getSegment(2).equals("databases");
    }

    public boolean isLocalResourceName(ResourcePath resourcePath) {
        return isValidResourceName(resourcePath) && resourcePath.getSegment(1).equals(this.databaseId.getProjectId()) && resourcePath.getSegment(3).equals(this.databaseId.getDatabaseId());
    }

    public String databaseName() {
        return this.databaseName;
    }

    public Document encodeDocument(DocumentKey documentKey, ObjectValue objectValue) {
        Document.Builder newBuilder = Document.newBuilder();
        newBuilder.setName(encodeKey(documentKey));
        newBuilder.putAllFields(objectValue.getFieldsMap());
        return newBuilder.build();
    }

    public MutableDocument decodeMaybeDocument(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        if (batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND)) {
            return decodeFoundDocument(batchGetDocumentsResponse);
        }
        if (batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING)) {
            return decodeMissingDocument(batchGetDocumentsResponse);
        }
        throw new IllegalArgumentException("Unknown result case: " + batchGetDocumentsResponse.getResultCase());
    }

    private MutableDocument decodeFoundDocument(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        Assert.hardAssert(batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND), "Tried to deserialize a found document from a missing document.", new Object[0]);
        DocumentKey decodeKey = decodeKey(batchGetDocumentsResponse.getFound().getName());
        ObjectValue fromMap = ObjectValue.fromMap(batchGetDocumentsResponse.getFound().getFieldsMap());
        SnapshotVersion decodeVersion = decodeVersion(batchGetDocumentsResponse.getFound().getUpdateTime());
        Assert.hardAssert(!decodeVersion.equals(SnapshotVersion.NONE), "Got a document response with no snapshot version", new Object[0]);
        return MutableDocument.newFoundDocument(decodeKey, decodeVersion, fromMap);
    }

    private MutableDocument decodeMissingDocument(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        Assert.hardAssert(batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING), "Tried to deserialize a missing document from a found document.", new Object[0]);
        DocumentKey decodeKey = decodeKey(batchGetDocumentsResponse.getMissing());
        SnapshotVersion decodeVersion = decodeVersion(batchGetDocumentsResponse.getReadTime());
        Assert.hardAssert(!decodeVersion.equals(SnapshotVersion.NONE), "Got a no document response with no snapshot version", new Object[0]);
        return MutableDocument.newNoDocument(decodeKey, decodeVersion);
    }

    public Write encodeMutation(Mutation mutation) {
        Write.Builder newBuilder = Write.newBuilder();
        if (mutation instanceof SetMutation) {
            newBuilder.setUpdate(encodeDocument(mutation.getKey(), ((SetMutation) mutation).getValue()));
        } else if (mutation instanceof PatchMutation) {
            newBuilder.setUpdate(encodeDocument(mutation.getKey(), ((PatchMutation) mutation).getValue()));
            newBuilder.setUpdateMask(encodeDocumentMask(mutation.getFieldMask()));
        } else if (mutation instanceof DeleteMutation) {
            newBuilder.setDelete(encodeKey(mutation.getKey()));
        } else {
            if (!(mutation instanceof VerifyMutation)) {
                throw Assert.fail("unknown mutation type %s", mutation.getClass());
            }
            newBuilder.setVerify(encodeKey(mutation.getKey()));
        }
        Iterator<FieldTransform> it = mutation.getFieldTransforms().iterator();
        while (it.hasNext()) {
            newBuilder.addUpdateTransforms(encodeFieldTransform(it.next()));
        }
        if (!mutation.getPrecondition().isNone()) {
            newBuilder.setCurrentDocument(encodePrecondition(mutation.getPrecondition()));
        }
        return newBuilder.build();
    }

    public Mutation decodeMutation(Write write) {
        Precondition precondition;
        if (write.hasCurrentDocument()) {
            precondition = decodePrecondition(write.getCurrentDocument());
        } else {
            precondition = Precondition.NONE;
        }
        Precondition precondition2 = precondition;
        ArrayList arrayList = new ArrayList();
        Iterator<DocumentTransform.FieldTransform> it = write.getUpdateTransformsList().iterator();
        while (it.hasNext()) {
            arrayList.add(decodeFieldTransform(it.next()));
        }
        int i = AnonymousClass1.$SwitchMap$com$google$firestore$v1$Write$OperationCase[write.getOperationCase().ordinal()];
        if (i == 1) {
            if (write.hasUpdateMask()) {
                return new PatchMutation(decodeKey(write.getUpdate().getName()), ObjectValue.fromMap(write.getUpdate().getFieldsMap()), decodeDocumentMask(write.getUpdateMask()), precondition2, arrayList);
            }
            return new SetMutation(decodeKey(write.getUpdate().getName()), ObjectValue.fromMap(write.getUpdate().getFieldsMap()), precondition2, arrayList);
        }
        if (i == 2) {
            return new DeleteMutation(decodeKey(write.getDelete()), precondition2);
        }
        if (i == 3) {
            return new VerifyMutation(decodeKey(write.getVerify()), precondition2);
        }
        throw Assert.fail("Unknown mutation operation: %d", write.getOperationCase());
    }

    private com.google.firestore.v1.Precondition encodePrecondition(Precondition precondition) {
        Assert.hardAssert(!precondition.isNone(), "Can't serialize an empty precondition", new Object[0]);
        Precondition.Builder newBuilder = com.google.firestore.v1.Precondition.newBuilder();
        if (precondition.getUpdateTime() != null) {
            return newBuilder.setUpdateTime(encodeVersion(precondition.getUpdateTime())).build();
        }
        if (precondition.getExists() != null) {
            return newBuilder.setExists(precondition.getExists().booleanValue()).build();
        }
        throw Assert.fail("Unknown Precondition", new Object[0]);
    }

    private com.google.firebase.firestore.model.mutation.Precondition decodePrecondition(com.google.firestore.v1.Precondition precondition) {
        int i = AnonymousClass1.$SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase[precondition.getConditionTypeCase().ordinal()];
        if (i == 1) {
            return com.google.firebase.firestore.model.mutation.Precondition.updateTime(decodeVersion(precondition.getUpdateTime()));
        }
        if (i == 2) {
            return com.google.firebase.firestore.model.mutation.Precondition.exists(precondition.getExists());
        }
        if (i == 3) {
            return com.google.firebase.firestore.model.mutation.Precondition.NONE;
        }
        throw Assert.fail("Unknown precondition", new Object[0]);
    }

    private DocumentMask encodeDocumentMask(FieldMask fieldMask) {
        DocumentMask.Builder newBuilder = DocumentMask.newBuilder();
        Iterator<FieldPath> it = fieldMask.getMask().iterator();
        while (it.hasNext()) {
            newBuilder.addFieldPaths(it.next().canonicalString());
        }
        return newBuilder.build();
    }

    private FieldMask decodeDocumentMask(DocumentMask documentMask) {
        int fieldPathsCount = documentMask.getFieldPathsCount();
        HashSet hashSet = new HashSet(fieldPathsCount);
        for (int i = 0; i < fieldPathsCount; i++) {
            hashSet.add(FieldPath.fromServerFormat(documentMask.getFieldPaths(i)));
        }
        return FieldMask.fromSet(hashSet);
    }

    private DocumentTransform.FieldTransform encodeFieldTransform(FieldTransform fieldTransform) {
        TransformOperation operation = fieldTransform.getOperation();
        if (operation instanceof ServerTimestampOperation) {
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setSetToServerValue(DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME).build();
        }
        if (operation instanceof ArrayTransformOperation.Union) {
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setAppendMissingElements(ArrayValue.newBuilder().addAllValues(((ArrayTransformOperation.Union) operation).getElements())).build();
        }
        if (operation instanceof ArrayTransformOperation.Remove) {
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setRemoveAllFromArray(ArrayValue.newBuilder().addAllValues(((ArrayTransformOperation.Remove) operation).getElements())).build();
        }
        if (operation instanceof NumericIncrementTransformOperation) {
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setIncrement(((NumericIncrementTransformOperation) operation).getOperand()).build();
        }
        throw Assert.fail("Unknown transform: %s", operation);
    }

    private FieldTransform decodeFieldTransform(DocumentTransform.FieldTransform fieldTransform) {
        int i = AnonymousClass1.$SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase[fieldTransform.getTransformTypeCase().ordinal()];
        if (i == 1) {
            Assert.hardAssert(fieldTransform.getSetToServerValue() == DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME, "Unknown transform setToServerValue: %s", fieldTransform.getSetToServerValue());
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), ServerTimestampOperation.getInstance());
        }
        if (i == 2) {
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Union(fieldTransform.getAppendMissingElements().getValuesList()));
        }
        if (i == 3) {
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Remove(fieldTransform.getRemoveAllFromArray().getValuesList()));
        }
        if (i == 4) {
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new NumericIncrementTransformOperation(fieldTransform.getIncrement()));
        }
        throw Assert.fail("Unknown FieldTransform proto: %s", fieldTransform);
    }

    public MutationResult decodeMutationResult(WriteResult writeResult, SnapshotVersion snapshotVersion) {
        SnapshotVersion decodeVersion = decodeVersion(writeResult.getUpdateTime());
        if (!SnapshotVersion.NONE.equals(decodeVersion)) {
            snapshotVersion = decodeVersion;
        }
        int transformResultsCount = writeResult.getTransformResultsCount();
        ArrayList arrayList = new ArrayList(transformResultsCount);
        for (int i = 0; i < transformResultsCount; i++) {
            arrayList.add(writeResult.getTransformResults(i));
        }
        return new MutationResult(snapshotVersion, arrayList);
    }

    public Map<String, String> encodeListenRequestLabels(TargetData targetData) {
        String encodeLabel = encodeLabel(targetData.getPurpose());
        if (encodeLabel == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("goog-listen-tags", encodeLabel);
        return hashMap;
    }

    private String encodeLabel(QueryPurpose queryPurpose) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$local$QueryPurpose[queryPurpose.ordinal()];
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            return "existence-filter-mismatch";
        }
        if (i == 3) {
            return "existence-filter-mismatch-bloom";
        }
        if (i == 4) {
            return "limbo-document";
        }
        throw Assert.fail("Unrecognized query purpose: %s", queryPurpose);
    }

    public Target encodeTarget(TargetData targetData) {
        Target.Builder newBuilder = Target.newBuilder();
        com.google.firebase.firestore.core.Target target = targetData.getTarget();
        if (target.isDocumentQuery()) {
            newBuilder.setDocuments(encodeDocumentsTarget(target));
        } else {
            newBuilder.setQuery(encodeQueryTarget(target));
        }
        newBuilder.setTargetId(targetData.getTargetId());
        if (targetData.getResumeToken().isEmpty() && targetData.getSnapshotVersion().compareTo(SnapshotVersion.NONE) > 0) {
            newBuilder.setReadTime(encodeTimestamp(targetData.getSnapshotVersion().getTimestamp()));
        } else {
            newBuilder.setResumeToken(targetData.getResumeToken());
        }
        if (targetData.getExpectedCount() != null && (!targetData.getResumeToken().isEmpty() || targetData.getSnapshotVersion().compareTo(SnapshotVersion.NONE) > 0)) {
            newBuilder.setExpectedCount(Int32Value.newBuilder().setValue(targetData.getExpectedCount().intValue()));
        }
        return newBuilder.build();
    }

    public Target.DocumentsTarget encodeDocumentsTarget(com.google.firebase.firestore.core.Target target) {
        Target.DocumentsTarget.Builder newBuilder = Target.DocumentsTarget.newBuilder();
        newBuilder.addDocuments(encodeQueryPath(target.getPath()));
        return newBuilder.build();
    }

    public com.google.firebase.firestore.core.Target decodeDocumentsTarget(Target.DocumentsTarget documentsTarget) {
        int documentsCount = documentsTarget.getDocumentsCount();
        Assert.hardAssert(documentsCount == 1, "DocumentsTarget contained other than 1 document %d", Integer.valueOf(documentsCount));
        return Query.atPath(decodeQueryPath(documentsTarget.getDocuments(0))).toTarget();
    }

    public Target.QueryTarget encodeQueryTarget(com.google.firebase.firestore.core.Target target) {
        Target.QueryTarget.Builder newBuilder = Target.QueryTarget.newBuilder();
        StructuredQuery.Builder newBuilder2 = StructuredQuery.newBuilder();
        ResourcePath path = target.getPath();
        if (target.getCollectionGroup() != null) {
            Assert.hardAssert(path.length() % 2 == 0, "Collection Group queries should be within a document path or root.", new Object[0]);
            newBuilder.setParent(encodeQueryPath(path));
            StructuredQuery.CollectionSelector.Builder newBuilder3 = StructuredQuery.CollectionSelector.newBuilder();
            newBuilder3.setCollectionId(target.getCollectionGroup());
            newBuilder3.setAllDescendants(true);
            newBuilder2.addFrom(newBuilder3);
        } else {
            Assert.hardAssert(path.length() % 2 != 0, "Document queries with filters are not supported.", new Object[0]);
            newBuilder.setParent(encodeQueryPath(path.popLast()));
            StructuredQuery.CollectionSelector.Builder newBuilder4 = StructuredQuery.CollectionSelector.newBuilder();
            newBuilder4.setCollectionId(path.getLastSegment());
            newBuilder2.addFrom(newBuilder4);
        }
        if (target.getFilters().size() > 0) {
            newBuilder2.setWhere(encodeFilters(target.getFilters()));
        }
        Iterator<OrderBy> it = target.getOrderBy().iterator();
        while (it.hasNext()) {
            newBuilder2.addOrderBy(encodeOrderBy(it.next()));
        }
        if (target.hasLimit()) {
            newBuilder2.setLimit(Int32Value.newBuilder().setValue((int) target.getLimit()));
        }
        if (target.getStartAt() != null) {
            Cursor.Builder newBuilder5 = Cursor.newBuilder();
            newBuilder5.addAllValues(target.getStartAt().getPosition());
            newBuilder5.setBefore(target.getStartAt().isInclusive());
            newBuilder2.setStartAt(newBuilder5);
        }
        if (target.getEndAt() != null) {
            Cursor.Builder newBuilder6 = Cursor.newBuilder();
            newBuilder6.addAllValues(target.getEndAt().getPosition());
            newBuilder6.setBefore(!target.getEndAt().isInclusive());
            newBuilder2.setEndAt(newBuilder6);
        }
        newBuilder.setStructuredQuery(newBuilder2);
        return newBuilder.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public com.google.firebase.firestore.core.Target decodeQueryTarget(String str, StructuredQuery structuredQuery) {
        ResourcePath resourcePath;
        String str2;
        List<Filter> emptyList;
        int orderByCount;
        List emptyList2;
        ResourcePath decodeQueryPath = decodeQueryPath(str);
        int fromCount = structuredQuery.getFromCount();
        if (fromCount > 0) {
            Assert.hardAssert(fromCount == 1, "StructuredQuery.from with more than one collection is not supported.", new Object[0]);
            StructuredQuery.CollectionSelector from = structuredQuery.getFrom(0);
            if (from.getAllDescendants()) {
                resourcePath = decodeQueryPath;
                str2 = from.getCollectionId();
                if (!structuredQuery.hasWhere()) {
                    emptyList = decodeFilters(structuredQuery.getWhere());
                } else {
                    emptyList = Collections.emptyList();
                }
                List<Filter> list = emptyList;
                orderByCount = structuredQuery.getOrderByCount();
                if (orderByCount <= 0) {
                    ArrayList arrayList = new ArrayList(orderByCount);
                    for (int i = 0; i < orderByCount; i++) {
                        arrayList.add(decodeOrderBy(structuredQuery.getOrderBy(i)));
                    }
                    emptyList2 = arrayList;
                } else {
                    emptyList2 = Collections.emptyList();
                }
                return new com.google.firebase.firestore.core.Target(resourcePath, str2, list, emptyList2, structuredQuery.hasLimit() ? structuredQuery.getLimit().getValue() : -1L, !structuredQuery.hasStartAt() ? new Bound(structuredQuery.getStartAt().getValuesList(), structuredQuery.getStartAt().getBefore()) : null, structuredQuery.hasEndAt() ? new Bound(structuredQuery.getEndAt().getValuesList(), !structuredQuery.getEndAt().getBefore()) : null);
            }
            decodeQueryPath = decodeQueryPath.append(from.getCollectionId());
        }
        resourcePath = decodeQueryPath;
        str2 = null;
        if (!structuredQuery.hasWhere()) {
        }
        List<Filter> list2 = emptyList;
        orderByCount = structuredQuery.getOrderByCount();
        if (orderByCount <= 0) {
        }
        return new com.google.firebase.firestore.core.Target(resourcePath, str2, list2, emptyList2, structuredQuery.hasLimit() ? structuredQuery.getLimit().getValue() : -1L, !structuredQuery.hasStartAt() ? new Bound(structuredQuery.getStartAt().getValuesList(), structuredQuery.getStartAt().getBefore()) : null, structuredQuery.hasEndAt() ? new Bound(structuredQuery.getEndAt().getValuesList(), !structuredQuery.getEndAt().getBefore()) : null);
    }

    public com.google.firebase.firestore.core.Target decodeQueryTarget(Target.QueryTarget queryTarget) {
        return decodeQueryTarget(queryTarget.getParent(), queryTarget.getStructuredQuery());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StructuredAggregationQuery encodeStructuredAggregationQuery(Target.QueryTarget queryTarget, List<AggregateField> list, HashMap<String, String> hashMap) {
        StructuredAggregationQuery.Builder newBuilder = StructuredAggregationQuery.newBuilder();
        newBuilder.setStructuredQuery(queryTarget.getStructuredQuery());
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        int i = 1;
        for (AggregateField aggregateField : list) {
            if (!hashSet.contains(aggregateField.getAlias())) {
                hashSet.add(aggregateField.getAlias());
                StringBuilder sb = new StringBuilder();
                sb.append("aggregate_");
                int i2 = i + 1;
                sb.append(i);
                String sb2 = sb.toString();
                hashMap.put(sb2, aggregateField.getAlias());
                StructuredAggregationQuery.Aggregation.Builder newBuilder2 = StructuredAggregationQuery.Aggregation.newBuilder();
                StructuredQuery.FieldReference build = StructuredQuery.FieldReference.newBuilder().setFieldPath(aggregateField.getFieldPath()).build();
                if (aggregateField instanceof AggregateField.CountAggregateField) {
                    newBuilder2.setCount(StructuredAggregationQuery.Aggregation.Count.getDefaultInstance());
                } else if (aggregateField instanceof AggregateField.SumAggregateField) {
                    newBuilder2.setSum(StructuredAggregationQuery.Aggregation.Sum.newBuilder().setField(build).build());
                } else if (aggregateField instanceof AggregateField.AverageAggregateField) {
                    newBuilder2.setAvg(StructuredAggregationQuery.Aggregation.Avg.newBuilder().setField(build).build());
                } else {
                    throw new RuntimeException("Unsupported aggregation");
                }
                newBuilder2.setAlias(sb2);
                arrayList.add(newBuilder2.build());
                i = i2;
            }
        }
        newBuilder.addAllAggregations(arrayList);
        return (StructuredAggregationQuery) newBuilder.build();
    }

    private StructuredQuery.Filter encodeFilters(List<Filter> list) {
        return encodeFilter(new CompositeFilter(list, CompositeFilter.Operator.AND));
    }

    private List<Filter> decodeFilters(StructuredQuery.Filter filter) {
        Filter decodeFilter = decodeFilter(filter);
        if (decodeFilter instanceof CompositeFilter) {
            CompositeFilter compositeFilter = (CompositeFilter) decodeFilter;
            if (compositeFilter.isFlatConjunction()) {
                return compositeFilter.getFilters();
            }
        }
        return Collections.singletonList(decodeFilter);
    }

    StructuredQuery.Filter encodeFilter(Filter filter) {
        if (filter instanceof FieldFilter) {
            return encodeUnaryOrFieldFilter((FieldFilter) filter);
        }
        if (filter instanceof CompositeFilter) {
            return encodeCompositeFilter((CompositeFilter) filter);
        }
        throw Assert.fail("Unrecognized filter type %s", filter.toString());
    }

    StructuredQuery.Filter encodeUnaryOrFieldFilter(FieldFilter fieldFilter) {
        StructuredQuery.UnaryFilter.Operator operator;
        StructuredQuery.UnaryFilter.Operator operator2;
        if (fieldFilter.getOperator() == FieldFilter.Operator.EQUAL || fieldFilter.getOperator() == FieldFilter.Operator.NOT_EQUAL) {
            StructuredQuery.UnaryFilter.Builder newBuilder = StructuredQuery.UnaryFilter.newBuilder();
            newBuilder.setField(encodeFieldPath(fieldFilter.getField()));
            if (Values.isNanValue(fieldFilter.getValue())) {
                if (fieldFilter.getOperator() == FieldFilter.Operator.EQUAL) {
                    operator2 = StructuredQuery.UnaryFilter.Operator.IS_NAN;
                } else {
                    operator2 = StructuredQuery.UnaryFilter.Operator.IS_NOT_NAN;
                }
                newBuilder.setOp(operator2);
                return StructuredQuery.Filter.newBuilder().setUnaryFilter(newBuilder).build();
            }
            if (Values.isNullValue(fieldFilter.getValue())) {
                if (fieldFilter.getOperator() == FieldFilter.Operator.EQUAL) {
                    operator = StructuredQuery.UnaryFilter.Operator.IS_NULL;
                } else {
                    operator = StructuredQuery.UnaryFilter.Operator.IS_NOT_NULL;
                }
                newBuilder.setOp(operator);
                return StructuredQuery.Filter.newBuilder().setUnaryFilter(newBuilder).build();
            }
        }
        StructuredQuery.FieldFilter.Builder newBuilder2 = StructuredQuery.FieldFilter.newBuilder();
        newBuilder2.setField(encodeFieldPath(fieldFilter.getField()));
        newBuilder2.setOp(encodeFieldFilterOperator(fieldFilter.getOperator()));
        newBuilder2.setValue(fieldFilter.getValue());
        return StructuredQuery.Filter.newBuilder().setFieldFilter(newBuilder2).build();
    }

    StructuredQuery.CompositeFilter.Operator encodeCompositeFilterOperator(CompositeFilter.Operator operator) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$CompositeFilter$Operator[operator.ordinal()];
        if (i == 1) {
            return StructuredQuery.CompositeFilter.Operator.AND;
        }
        if (i == 2) {
            return StructuredQuery.CompositeFilter.Operator.OR;
        }
        throw Assert.fail("Unrecognized composite filter type.", new Object[0]);
    }

    CompositeFilter.Operator decodeCompositeFilterOperator(StructuredQuery.CompositeFilter.Operator operator) {
        int i = AnonymousClass1.$SwitchMap$com$google$firestore$v1$StructuredQuery$CompositeFilter$Operator[operator.ordinal()];
        if (i == 1) {
            return CompositeFilter.Operator.AND;
        }
        if (i == 2) {
            return CompositeFilter.Operator.OR;
        }
        throw Assert.fail("Only AND and OR composite filter types are supported.", new Object[0]);
    }

    StructuredQuery.Filter encodeCompositeFilter(CompositeFilter compositeFilter) {
        ArrayList arrayList = new ArrayList(compositeFilter.getFilters().size());
        Iterator<Filter> it = compositeFilter.getFilters().iterator();
        while (it.hasNext()) {
            arrayList.add(encodeFilter(it.next()));
        }
        if (arrayList.size() == 1) {
            return (StructuredQuery.Filter) arrayList.get(0);
        }
        StructuredQuery.CompositeFilter.Builder newBuilder = StructuredQuery.CompositeFilter.newBuilder();
        newBuilder.setOp(encodeCompositeFilterOperator(compositeFilter.getOperator()));
        newBuilder.addAllFilters(arrayList);
        return StructuredQuery.Filter.newBuilder().setCompositeFilter(newBuilder).build();
    }

    Filter decodeFilter(StructuredQuery.Filter filter) {
        int i = AnonymousClass1.$SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase[filter.getFilterTypeCase().ordinal()];
        if (i == 1) {
            return decodeCompositeFilter(filter.getCompositeFilter());
        }
        if (i == 2) {
            return decodeFieldFilter(filter.getFieldFilter());
        }
        if (i == 3) {
            return decodeUnaryFilter(filter.getUnaryFilter());
        }
        throw Assert.fail("Unrecognized Filter.filterType %d", filter.getFilterTypeCase());
    }

    FieldFilter decodeFieldFilter(StructuredQuery.FieldFilter fieldFilter) {
        return FieldFilter.create(FieldPath.fromServerFormat(fieldFilter.getField().getFieldPath()), decodeFieldFilterOperator(fieldFilter.getOp()), fieldFilter.getValue());
    }

    private Filter decodeUnaryFilter(StructuredQuery.UnaryFilter unaryFilter) {
        FieldPath fromServerFormat = FieldPath.fromServerFormat(unaryFilter.getField().getFieldPath());
        int i = AnonymousClass1.$SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator[unaryFilter.getOp().ordinal()];
        if (i == 1) {
            return FieldFilter.create(fromServerFormat, FieldFilter.Operator.EQUAL, Values.NAN_VALUE);
        }
        if (i == 2) {
            return FieldFilter.create(fromServerFormat, FieldFilter.Operator.EQUAL, Values.NULL_VALUE);
        }
        if (i == 3) {
            return FieldFilter.create(fromServerFormat, FieldFilter.Operator.NOT_EQUAL, Values.NAN_VALUE);
        }
        if (i == 4) {
            return FieldFilter.create(fromServerFormat, FieldFilter.Operator.NOT_EQUAL, Values.NULL_VALUE);
        }
        throw Assert.fail("Unrecognized UnaryFilter.operator %d", unaryFilter.getOp());
    }

    CompositeFilter decodeCompositeFilter(StructuredQuery.CompositeFilter compositeFilter) {
        ArrayList arrayList = new ArrayList();
        Iterator<StructuredQuery.Filter> it = compositeFilter.getFiltersList().iterator();
        while (it.hasNext()) {
            arrayList.add(decodeFilter(it.next()));
        }
        return new CompositeFilter(arrayList, decodeCompositeFilterOperator(compositeFilter.getOp()));
    }

    private StructuredQuery.FieldReference encodeFieldPath(FieldPath fieldPath) {
        return StructuredQuery.FieldReference.newBuilder().setFieldPath(fieldPath.canonicalString()).build();
    }

    private StructuredQuery.FieldFilter.Operator encodeFieldFilterOperator(FieldFilter.Operator operator) {
        switch (AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[operator.ordinal()]) {
            case 1:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN;
            case 2:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN_OR_EQUAL;
            case 3:
                return StructuredQuery.FieldFilter.Operator.EQUAL;
            case 4:
                return StructuredQuery.FieldFilter.Operator.NOT_EQUAL;
            case 5:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN;
            case 6:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN_OR_EQUAL;
            case 7:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS;
            case 8:
                return StructuredQuery.FieldFilter.Operator.IN;
            case 9:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS_ANY;
            case 10:
                return StructuredQuery.FieldFilter.Operator.NOT_IN;
            default:
                throw Assert.fail("Unknown operator %d", operator);
        }
    }

    private FieldFilter.Operator decodeFieldFilterOperator(StructuredQuery.FieldFilter.Operator operator) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[operator.ordinal()]) {
            case 1:
                return FieldFilter.Operator.LESS_THAN;
            case 2:
                return FieldFilter.Operator.LESS_THAN_OR_EQUAL;
            case 3:
                return FieldFilter.Operator.EQUAL;
            case 4:
                return FieldFilter.Operator.NOT_EQUAL;
            case 5:
                return FieldFilter.Operator.GREATER_THAN_OR_EQUAL;
            case 6:
                return FieldFilter.Operator.GREATER_THAN;
            case 7:
                return FieldFilter.Operator.ARRAY_CONTAINS;
            case 8:
                return FieldFilter.Operator.IN;
            case 9:
                return FieldFilter.Operator.ARRAY_CONTAINS_ANY;
            case 10:
                return FieldFilter.Operator.NOT_IN;
            default:
                throw Assert.fail("Unhandled FieldFilter.operator %d", operator);
        }
    }

    private StructuredQuery.Order encodeOrderBy(OrderBy orderBy) {
        StructuredQuery.Order.Builder newBuilder = StructuredQuery.Order.newBuilder();
        if (orderBy.getDirection().equals(OrderBy.Direction.ASCENDING)) {
            newBuilder.setDirection(StructuredQuery.Direction.ASCENDING);
        } else {
            newBuilder.setDirection(StructuredQuery.Direction.DESCENDING);
        }
        newBuilder.setField(encodeFieldPath(orderBy.getField()));
        return newBuilder.build();
    }

    private OrderBy decodeOrderBy(StructuredQuery.Order order) {
        OrderBy.Direction direction;
        FieldPath fromServerFormat = FieldPath.fromServerFormat(order.getField().getFieldPath());
        int i = AnonymousClass1.$SwitchMap$com$google$firestore$v1$StructuredQuery$Direction[order.getDirection().ordinal()];
        if (i == 1) {
            direction = OrderBy.Direction.ASCENDING;
        } else {
            if (i != 2) {
                throw Assert.fail("Unrecognized direction %d", order.getDirection());
            }
            direction = OrderBy.Direction.DESCENDING;
        }
        return OrderBy.getInstance(direction, fromServerFormat);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.firestore.remote.RemoteSerializer$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$CompositeFilter$Operator;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$local$QueryPurpose;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$CompositeFilter$Operator;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Write$OperationCase;

        static {
            int[] iArr = new int[ListenResponse.ResponseTypeCase.values().length];
            $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase = iArr;
            try {
                iArr[ListenResponse.ResponseTypeCase.TARGET_CHANGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.DOCUMENT_CHANGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.DOCUMENT_DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.DOCUMENT_REMOVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.FILTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[ListenResponse.ResponseTypeCase.RESPONSETYPE_NOT_SET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[TargetChange.TargetChangeType.values().length];
            $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType = iArr2;
            try {
                iArr2[TargetChange.TargetChangeType.NO_CHANGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.REMOVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.CURRENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.RESET.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.UNRECOGNIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr3 = new int[StructuredQuery.Direction.values().length];
            $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction = iArr3;
            try {
                iArr3[StructuredQuery.Direction.ASCENDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction[StructuredQuery.Direction.DESCENDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            int[] iArr4 = new int[StructuredQuery.FieldFilter.Operator.values().length];
            $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator = iArr4;
            try {
                iArr4[StructuredQuery.FieldFilter.Operator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.LESS_THAN_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.NOT_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.GREATER_THAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS.ordinal()] = 7;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.IN.ordinal()] = 8;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS_ANY.ordinal()] = 9;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator[StructuredQuery.FieldFilter.Operator.NOT_IN.ordinal()] = 10;
            } catch (NoSuchFieldError unused24) {
            }
            int[] iArr5 = new int[FieldFilter.Operator.values().length];
            $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator = iArr5;
            try {
                iArr5[FieldFilter.Operator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.LESS_THAN_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.NOT_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.GREATER_THAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.ARRAY_CONTAINS.ordinal()] = 7;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.IN.ordinal()] = 8;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.ARRAY_CONTAINS_ANY.ordinal()] = 9;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.NOT_IN.ordinal()] = 10;
            } catch (NoSuchFieldError unused34) {
            }
            int[] iArr6 = new int[StructuredQuery.UnaryFilter.Operator.values().length];
            $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator = iArr6;
            try {
                iArr6[StructuredQuery.UnaryFilter.Operator.IS_NAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator[StructuredQuery.UnaryFilter.Operator.IS_NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator[StructuredQuery.UnaryFilter.Operator.IS_NOT_NAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator[StructuredQuery.UnaryFilter.Operator.IS_NOT_NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused38) {
            }
            int[] iArr7 = new int[StructuredQuery.Filter.FilterTypeCase.values().length];
            $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase = iArr7;
            try {
                iArr7[StructuredQuery.Filter.FilterTypeCase.COMPOSITE_FILTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase[StructuredQuery.Filter.FilterTypeCase.FIELD_FILTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase[StructuredQuery.Filter.FilterTypeCase.UNARY_FILTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused41) {
            }
            int[] iArr8 = new int[StructuredQuery.CompositeFilter.Operator.values().length];
            $SwitchMap$com$google$firestore$v1$StructuredQuery$CompositeFilter$Operator = iArr8;
            try {
                iArr8[StructuredQuery.CompositeFilter.Operator.AND.ordinal()] = 1;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$CompositeFilter$Operator[StructuredQuery.CompositeFilter.Operator.OR.ordinal()] = 2;
            } catch (NoSuchFieldError unused43) {
            }
            int[] iArr9 = new int[CompositeFilter.Operator.values().length];
            $SwitchMap$com$google$firebase$firestore$core$CompositeFilter$Operator = iArr9;
            try {
                iArr9[CompositeFilter.Operator.AND.ordinal()] = 1;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$CompositeFilter$Operator[CompositeFilter.Operator.OR.ordinal()] = 2;
            } catch (NoSuchFieldError unused45) {
            }
            int[] iArr10 = new int[QueryPurpose.values().length];
            $SwitchMap$com$google$firebase$firestore$local$QueryPurpose = iArr10;
            try {
                iArr10[QueryPurpose.LISTEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$local$QueryPurpose[QueryPurpose.EXISTENCE_FILTER_MISMATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$local$QueryPurpose[QueryPurpose.EXISTENCE_FILTER_MISMATCH_BLOOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$local$QueryPurpose[QueryPurpose.LIMBO_RESOLUTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused49) {
            }
            int[] iArr11 = new int[DocumentTransform.FieldTransform.TransformTypeCase.values().length];
            $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase = iArr11;
            try {
                iArr11[DocumentTransform.FieldTransform.TransformTypeCase.SET_TO_SERVER_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase[DocumentTransform.FieldTransform.TransformTypeCase.APPEND_MISSING_ELEMENTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase[DocumentTransform.FieldTransform.TransformTypeCase.REMOVE_ALL_FROM_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase[DocumentTransform.FieldTransform.TransformTypeCase.INCREMENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused53) {
            }
            int[] iArr12 = new int[Precondition.ConditionTypeCase.values().length];
            $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase = iArr12;
            try {
                iArr12[Precondition.ConditionTypeCase.UPDATE_TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase[Precondition.ConditionTypeCase.EXISTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase[Precondition.ConditionTypeCase.CONDITIONTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused56) {
            }
            int[] iArr13 = new int[Write.OperationCase.values().length];
            $SwitchMap$com$google$firestore$v1$Write$OperationCase = iArr13;
            try {
                iArr13[Write.OperationCase.UPDATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Write$OperationCase[Write.OperationCase.DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Write$OperationCase[Write.OperationCase.VERIFY.ordinal()] = 3;
            } catch (NoSuchFieldError unused59) {
            }
        }
    }

    public WatchChange decodeWatchChange(ListenResponse listenResponse) {
        WatchChange.WatchTargetChangeType watchTargetChangeType;
        WatchChange watchTargetChange;
        int i = AnonymousClass1.$SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase[listenResponse.getResponseTypeCase().ordinal()];
        Status status = null;
        if (i == 1) {
            com.google.firestore.v1.TargetChange targetChange = listenResponse.getTargetChange();
            int i2 = AnonymousClass1.$SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[targetChange.getTargetChangeType().ordinal()];
            if (i2 == 1) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.NoChange;
            } else if (i2 == 2) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Added;
            } else if (i2 == 3) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Removed;
                status = fromStatus(targetChange.getCause());
            } else if (i2 == 4) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Current;
            } else if (i2 == 5) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Reset;
            } else {
                throw new IllegalArgumentException("Unknown target change type");
            }
            watchTargetChange = new WatchChange.WatchTargetChange(watchTargetChangeType, targetChange.getTargetIdsList(), targetChange.getResumeToken(), status);
        } else if (i == 2) {
            DocumentChange documentChange = listenResponse.getDocumentChange();
            List<Integer> targetIdsList = documentChange.getTargetIdsList();
            List<Integer> removedTargetIdsList = documentChange.getRemovedTargetIdsList();
            DocumentKey decodeKey = decodeKey(documentChange.getDocument().getName());
            SnapshotVersion decodeVersion = decodeVersion(documentChange.getDocument().getUpdateTime());
            Assert.hardAssert(!decodeVersion.equals(SnapshotVersion.NONE), "Got a document change without an update time", new Object[0]);
            MutableDocument newFoundDocument = MutableDocument.newFoundDocument(decodeKey, decodeVersion, ObjectValue.fromMap(documentChange.getDocument().getFieldsMap()));
            watchTargetChange = new WatchChange.DocumentChange(targetIdsList, removedTargetIdsList, newFoundDocument.getKey(), newFoundDocument);
        } else {
            if (i == 3) {
                DocumentDelete documentDelete = listenResponse.getDocumentDelete();
                List<Integer> removedTargetIdsList2 = documentDelete.getRemovedTargetIdsList();
                MutableDocument newNoDocument = MutableDocument.newNoDocument(decodeKey(documentDelete.getDocument()), decodeVersion(documentDelete.getReadTime()));
                return new WatchChange.DocumentChange(Collections.emptyList(), removedTargetIdsList2, newNoDocument.getKey(), newNoDocument);
            }
            if (i != 4) {
                if (i == 5) {
                    com.google.firestore.v1.ExistenceFilter filter = listenResponse.getFilter();
                    return new WatchChange.ExistenceFilterWatchChange(filter.getTargetId(), new ExistenceFilter(filter.getCount(), filter.getUnchangedNames()));
                }
                throw new IllegalArgumentException("Unknown change type set");
            }
            DocumentRemove documentRemove = listenResponse.getDocumentRemove();
            watchTargetChange = new WatchChange.DocumentChange(Collections.emptyList(), documentRemove.getRemovedTargetIdsList(), decodeKey(documentRemove.getDocument()), null);
        }
        return watchTargetChange;
    }

    public SnapshotVersion decodeVersionFromListenResponse(ListenResponse listenResponse) {
        if (listenResponse.getResponseTypeCase() != ListenResponse.ResponseTypeCase.TARGET_CHANGE) {
            return SnapshotVersion.NONE;
        }
        if (listenResponse.getTargetChange().getTargetIdsCount() != 0) {
            return SnapshotVersion.NONE;
        }
        return decodeVersion(listenResponse.getTargetChange().getReadTime());
    }

    private Status fromStatus(com.google.rpc.Status status) {
        return Status.fromCodeValue(status.getCode()).withDescription(status.getMessage());
    }
}
