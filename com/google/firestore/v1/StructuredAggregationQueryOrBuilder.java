package com.google.firestore.v1;

import com.google.firestore.v1.StructuredAggregationQuery;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface StructuredAggregationQueryOrBuilder extends MessageLiteOrBuilder {
    StructuredAggregationQuery.Aggregation getAggregations(int i);

    int getAggregationsCount();

    List<StructuredAggregationQuery.Aggregation> getAggregationsList();

    StructuredAggregationQuery.QueryTypeCase getQueryTypeCase();

    StructuredQuery getStructuredQuery();

    boolean hasStructuredQuery();
}
