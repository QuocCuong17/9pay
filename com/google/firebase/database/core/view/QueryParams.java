package com.google.firebase.database.core.view;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.filter.IndexedFilter;
import com.google.firebase.database.core.view.filter.LimitedFilter;
import com.google.firebase.database.core.view.filter.NodeFilter;
import com.google.firebase.database.core.view.filter.RangedFilter;
import com.google.firebase.database.snapshot.BooleanNode;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.DoubleNode;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.LongNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import com.google.firebase.database.snapshot.PriorityIndex;
import com.google.firebase.database.snapshot.PriorityUtilities;
import com.google.firebase.database.snapshot.StringNode;
import com.google.firebase.database.util.JsonMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: classes4.dex */
public final class QueryParams {
    public static final QueryParams DEFAULT_PARAMS = new QueryParams();
    private static final String INDEX = "i";
    private static final String INDEX_END_NAME = "en";
    private static final String INDEX_END_VALUE = "ep";
    private static final String INDEX_START_NAME = "sn";
    private static final String INDEX_START_VALUE = "sp";
    private static final String LIMIT = "l";
    private static final String VIEW_FROM = "vf";
    private Integer limit;
    private ViewFrom viewFrom;
    private Node indexStartValue = null;
    private ChildKey indexStartName = null;
    private Node indexEndValue = null;
    private ChildKey indexEndName = null;
    private Index index = PriorityIndex.getInstance();
    private String jsonSerialization = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum ViewFrom {
        LEFT,
        RIGHT
    }

    public boolean hasStart() {
        return this.indexStartValue != null;
    }

    public Node getIndexStartValue() {
        if (!hasStart()) {
            throw new IllegalArgumentException("Cannot get index start value if start has not been set");
        }
        return this.indexStartValue;
    }

    public ChildKey getIndexStartName() {
        if (!hasStart()) {
            throw new IllegalArgumentException("Cannot get index start name if start has not been set");
        }
        ChildKey childKey = this.indexStartName;
        return childKey != null ? childKey : ChildKey.getMinName();
    }

    public boolean hasEnd() {
        return this.indexEndValue != null;
    }

    public Node getIndexEndValue() {
        if (!hasEnd()) {
            throw new IllegalArgumentException("Cannot get index end value if start has not been set");
        }
        return this.indexEndValue;
    }

    public ChildKey getIndexEndName() {
        if (!hasEnd()) {
            throw new IllegalArgumentException("Cannot get index end name if start has not been set");
        }
        ChildKey childKey = this.indexEndName;
        return childKey != null ? childKey : ChildKey.getMaxName();
    }

    public boolean hasLimit() {
        return this.limit != null;
    }

    public boolean hasAnchoredLimit() {
        return hasLimit() && this.viewFrom != null;
    }

    public int getLimit() {
        if (!hasLimit()) {
            throw new IllegalArgumentException("Cannot get limit if limit has not been set");
        }
        return this.limit.intValue();
    }

    public Index getIndex() {
        return this.index;
    }

    private QueryParams copy() {
        QueryParams queryParams = new QueryParams();
        queryParams.limit = this.limit;
        queryParams.indexStartValue = this.indexStartValue;
        queryParams.indexStartName = this.indexStartName;
        queryParams.indexEndValue = this.indexEndValue;
        queryParams.indexEndName = this.indexEndName;
        queryParams.viewFrom = this.viewFrom;
        queryParams.index = this.index;
        return queryParams;
    }

    public QueryParams limitToFirst(int i) {
        QueryParams copy = copy();
        copy.limit = Integer.valueOf(i);
        copy.viewFrom = ViewFrom.LEFT;
        return copy;
    }

    public QueryParams limitToLast(int i) {
        QueryParams copy = copy();
        copy.limit = Integer.valueOf(i);
        copy.viewFrom = ViewFrom.RIGHT;
        return copy;
    }

    public QueryParams startAt(Node node, ChildKey childKey) {
        Utilities.hardAssert(node.isLeafNode() || node.isEmpty());
        Utilities.hardAssert(!(node instanceof LongNode));
        QueryParams copy = copy();
        copy.indexStartValue = node;
        copy.indexStartName = childKey;
        return copy;
    }

    public QueryParams endAt(Node node, ChildKey childKey) {
        Utilities.hardAssert(node.isLeafNode() || node.isEmpty());
        Utilities.hardAssert(!(node instanceof LongNode));
        QueryParams copy = copy();
        copy.indexEndValue = node;
        copy.indexEndName = childKey;
        return copy;
    }

    public QueryParams orderBy(Index index) {
        QueryParams copy = copy();
        copy.index = index;
        return copy;
    }

    public boolean isViewFromLeft() {
        ViewFrom viewFrom = this.viewFrom;
        return viewFrom != null ? viewFrom == ViewFrom.LEFT : hasStart();
    }

    public Map<String, Object> getWireProtocolParams() {
        HashMap hashMap = new HashMap();
        if (hasStart()) {
            hashMap.put(INDEX_START_VALUE, this.indexStartValue.getValue());
            ChildKey childKey = this.indexStartName;
            if (childKey != null) {
                hashMap.put(INDEX_START_NAME, childKey.asString());
            }
        }
        if (hasEnd()) {
            hashMap.put(INDEX_END_VALUE, this.indexEndValue.getValue());
            ChildKey childKey2 = this.indexEndName;
            if (childKey2 != null) {
                hashMap.put(INDEX_END_NAME, childKey2.asString());
            }
        }
        Integer num = this.limit;
        if (num != null) {
            hashMap.put("l", num);
            ViewFrom viewFrom = this.viewFrom;
            if (viewFrom == null) {
                if (hasStart()) {
                    viewFrom = ViewFrom.LEFT;
                } else {
                    viewFrom = ViewFrom.RIGHT;
                }
            }
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$database$core$view$QueryParams$ViewFrom[viewFrom.ordinal()];
            if (i == 1) {
                hashMap.put(VIEW_FROM, "l");
            } else if (i == 2) {
                hashMap.put(VIEW_FROM, PDPageLabelRange.STYLE_ROMAN_LOWER);
            }
        }
        if (!this.index.equals(PriorityIndex.getInstance())) {
            hashMap.put("i", this.index.getQueryDefinition());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.database.core.view.QueryParams$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$core$view$QueryParams$ViewFrom;

        static {
            int[] iArr = new int[ViewFrom.values().length];
            $SwitchMap$com$google$firebase$database$core$view$QueryParams$ViewFrom = iArr;
            try {
                iArr[ViewFrom.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$view$QueryParams$ViewFrom[ViewFrom.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public boolean loadsAllData() {
        return (hasStart() || hasEnd() || hasLimit()) ? false : true;
    }

    public boolean isDefault() {
        return loadsAllData() && this.index.equals(PriorityIndex.getInstance());
    }

    public boolean isValid() {
        return (hasStart() && hasEnd() && hasLimit() && !hasAnchoredLimit()) ? false : true;
    }

    public String toJSON() {
        if (this.jsonSerialization == null) {
            try {
                this.jsonSerialization = JsonMapper.serializeJson(getWireProtocolParams());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.jsonSerialization;
    }

    public static QueryParams fromQueryObject(Map<String, Object> map) {
        QueryParams queryParams = new QueryParams();
        queryParams.limit = (Integer) map.get("l");
        if (map.containsKey(INDEX_START_VALUE)) {
            queryParams.indexStartValue = normalizeValue(NodeUtilities.NodeFromJSON(map.get(INDEX_START_VALUE)));
            String str = (String) map.get(INDEX_START_NAME);
            if (str != null) {
                queryParams.indexStartName = ChildKey.fromString(str);
            }
        }
        if (map.containsKey(INDEX_END_VALUE)) {
            queryParams.indexEndValue = normalizeValue(NodeUtilities.NodeFromJSON(map.get(INDEX_END_VALUE)));
            String str2 = (String) map.get(INDEX_END_NAME);
            if (str2 != null) {
                queryParams.indexEndName = ChildKey.fromString(str2);
            }
        }
        String str3 = (String) map.get(VIEW_FROM);
        if (str3 != null) {
            queryParams.viewFrom = str3.equals("l") ? ViewFrom.LEFT : ViewFrom.RIGHT;
        }
        String str4 = (String) map.get("i");
        if (str4 != null) {
            queryParams.index = Index.fromQueryDefinition(str4);
        }
        return queryParams;
    }

    public NodeFilter getNodeFilter() {
        if (loadsAllData()) {
            return new IndexedFilter(getIndex());
        }
        if (hasLimit()) {
            return new LimitedFilter(this);
        }
        return new RangedFilter(this);
    }

    public String toString() {
        return getWireProtocolParams().toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QueryParams queryParams = (QueryParams) obj;
        Integer num = this.limit;
        if (num == null ? queryParams.limit != null : !num.equals(queryParams.limit)) {
            return false;
        }
        Index index = this.index;
        if (index == null ? queryParams.index != null : !index.equals(queryParams.index)) {
            return false;
        }
        ChildKey childKey = this.indexEndName;
        if (childKey == null ? queryParams.indexEndName != null : !childKey.equals(queryParams.indexEndName)) {
            return false;
        }
        Node node = this.indexEndValue;
        if (node == null ? queryParams.indexEndValue != null : !node.equals(queryParams.indexEndValue)) {
            return false;
        }
        ChildKey childKey2 = this.indexStartName;
        if (childKey2 == null ? queryParams.indexStartName != null : !childKey2.equals(queryParams.indexStartName)) {
            return false;
        }
        Node node2 = this.indexStartValue;
        if (node2 == null ? queryParams.indexStartValue == null : node2.equals(queryParams.indexStartValue)) {
            return isViewFromLeft() == queryParams.isViewFromLeft();
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.limit;
        int intValue = (((num != null ? num.intValue() : 0) * 31) + (isViewFromLeft() ? 1231 : 1237)) * 31;
        Node node = this.indexStartValue;
        int hashCode = (intValue + (node != null ? node.hashCode() : 0)) * 31;
        ChildKey childKey = this.indexStartName;
        int hashCode2 = (hashCode + (childKey != null ? childKey.hashCode() : 0)) * 31;
        Node node2 = this.indexEndValue;
        int hashCode3 = (hashCode2 + (node2 != null ? node2.hashCode() : 0)) * 31;
        ChildKey childKey2 = this.indexEndName;
        int hashCode4 = (hashCode3 + (childKey2 != null ? childKey2.hashCode() : 0)) * 31;
        Index index = this.index;
        return hashCode4 + (index != null ? index.hashCode() : 0);
    }

    private static Node normalizeValue(Node node) {
        if ((node instanceof StringNode) || (node instanceof BooleanNode) || (node instanceof DoubleNode) || (node instanceof EmptyNode)) {
            return node;
        }
        if (node instanceof LongNode) {
            return new DoubleNode(Double.valueOf(((Long) node.getValue()).doubleValue()), PriorityUtilities.NullPriority());
        }
        throw new IllegalStateException("Unexpected value passed to normalizeValue: " + node.getValue());
    }
}
