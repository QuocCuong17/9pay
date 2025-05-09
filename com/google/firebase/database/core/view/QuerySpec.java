package com.google.firebase.database.core.view;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.Index;
import java.util.Map;

/* loaded from: classes4.dex */
public final class QuerySpec {
    private final QueryParams params;
    private final Path path;

    public static QuerySpec defaultQueryAtPath(Path path) {
        return new QuerySpec(path, QueryParams.DEFAULT_PARAMS);
    }

    public QuerySpec(Path path, QueryParams queryParams) {
        this.path = path;
        this.params = queryParams;
    }

    public Path getPath() {
        return this.path;
    }

    public QueryParams getParams() {
        return this.params;
    }

    public static QuerySpec fromPathAndQueryObject(Path path, Map<String, Object> map) {
        return new QuerySpec(path, QueryParams.fromQueryObject(map));
    }

    public Index getIndex() {
        return this.params.getIndex();
    }

    public boolean isDefault() {
        return this.params.isDefault();
    }

    public boolean loadsAllData() {
        return this.params.loadsAllData();
    }

    public String toString() {
        return this.path + ":" + this.params;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QuerySpec querySpec = (QuerySpec) obj;
        return this.path.equals(querySpec.path) && this.params.equals(querySpec.params);
    }

    public int hashCode() {
        return (this.path.hashCode() * 31) + this.params.hashCode();
    }
}
